package mx.bidgroup.tec.tni.nomibanco.services.impl;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mx.bidgroup.tec.tni.nomibanco.configs.security.jwt.JwtTokenProvider;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationRequestDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationResponseDto;
import mx.bidgroup.tec.tni.nomibanco.repositories.IUserRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IAuthenticationService;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements IAuthenticationService {
    
    private final IUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequestDto.getUsername(),
                authenticationRequestDto.getPassword()
            )
        );
        UserDetails userDetails = userRepository.findByUsername(authenticationRequestDto.getUsername()).orElseThrow();
        String token = jwtTokenProvider.generateToken(userDetails);
        String username = userDetails.getUsername();
        
        return AuthenticationResponseDto.builder()
            .token(token)
            .username(username)
            .build();
        
    }


}
