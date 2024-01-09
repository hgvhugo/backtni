package mx.bidgroup.tec.tni.nomibanco.configs.security.jwt;

import java.util.Date;

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

    private Date getExpiration(String token)
    {

        return Date.from(jwtDecoder.decode(token).getExpiresAt());
    }

    
    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
