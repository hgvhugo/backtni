package mx.bidgroup.tec.tni.nomibanco.dtos;
 
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationRequestDto {
 
    private String username; 
    private String password;
    
}
