package mx.bidgroup.tec.tni.nomibanco.services.impl;


import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2; 
import mx.bidgroup.tec.tni.nomibanco.configs.security.jwt.JwtTokenProvider;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationRequestDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.ShortRoleDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.RoleEntity;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IRoleRepository;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IUserRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IAuthenticationService;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class AuthenticationServiceImpl implements IAuthenticationService {
    
    private final IUserRepository userRepository;
    private final IRoleRepository   roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {  
        log.info("Ingresa a authenticate service");
        log.info("authenticate: {}", authenticationRequestDto.getUsername());

       try{

        // Se autentica el usuario
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequestDto.getUsername(),
                authenticationRequestDto.getPassword()
            )
        );
        // Se obtiene el usuario
        UserDetails userDetails = userRepository.findByUsername(authenticationRequestDto.getUsername()).orElseThrow();
        
        Set<ShortRoleDto> shortRoleDtos = new java.util.HashSet<ShortRoleDto>();

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            ShortRoleDto shortRoleDto = new ShortRoleDto();
            List<RoleEntity> roleEntity =  roleRepository.findByRol(authority.getAuthority());
            shortRoleDto.setId(roleEntity.get(0).getId());
            shortRoleDto.setRol(roleEntity.get(0).getRol());
            shortRoleDtos.add(shortRoleDto);
        }
        
        //Se obtienen los roles del usuario
        // Set<String> roles = userDetails.getAuthorities().stream()
        //     .map(authority -> authority.getAuthority())
        //     .collect(Collectors.toSet());
 
        log.info("Autenticacion exitosa, authenticate: {}", authenticationRequestDto.getUsername());    

        // Se genera el token
        return AuthenticationResponseDto.builder()
            .token(jwtTokenProvider.generateToken(userDetails))
            .username(userDetails.getUsername())
            .roles(shortRoleDtos)
            .build();

            
       }catch (BadCredentialsException e){
            log.error("Error ocurrido en servicio authenticate: {}, usuario o contraseña incorrectos", authenticationRequestDto.getUsername());    
           throw new BadCredentialsException("Usuario o contraseña incorrectos, "+e.getMessage());
       }catch (Exception e){
            log.error("Error ocurrido en servicio authenticate: {} " +e.getMessage() , authenticationRequestDto.getUsername());    
           throw e;
       }

    }


}
