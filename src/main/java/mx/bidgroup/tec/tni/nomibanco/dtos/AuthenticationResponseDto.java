package mx.bidgroup.tec.tni.nomibanco.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthenticationResponseDto {

    private String token;
    private String username;
    
}
