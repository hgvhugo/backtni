package mx.bidgroup.tec.tni.nomibanco.configs.security.jwt;
 
import java.time.Instant; 
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {

    @Autowired
    private JwtEncoder jwtEncoder;

    
    @Value("${jwt.expiration}")
	public Long expirationtoken;

    public String generateToken(UserDetails userDetails) {
        

        Instant now =   Instant.now();
        // Instant now =   LocalDateTime.now().toInstant(ZoneOffset.UTC);  Se comenta para usar UTC en vez de Local
        System.out.println("now: " + now);
        Instant expirationTime = now.plusSeconds(expirationtoken);
        System.out.println("expirationTime: " + expirationTime);

        String scope = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(","));

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .subject(userDetails.getUsername())
            .claim("scope", scope)
            .issuer("NomiBanco")
            .issuedAt(now)
            .expiresAt(expirationTime)
            .build();    


        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
