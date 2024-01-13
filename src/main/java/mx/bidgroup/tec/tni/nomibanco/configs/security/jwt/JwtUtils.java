package mx.bidgroup.tec.tni.nomibanco.configs.security.jwt;
 
import java.time.Instant;
import java.time.LocalDateTime; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Autowired
    private JwtDecoder jwtDecoder;

        public String getUsername(String token) 
    {   
        
        return jwtDecoder.decode(token).getSubject();
        
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Instant getExpiration(String token)
    {

        return Instant.from(jwtDecoder.decode(token).getExpiresAt());
    }

    
    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).isBefore(Instant.now());
        // return getExpiration(token).before(LocalDateTime.from(Instant.now()));

    }
}
