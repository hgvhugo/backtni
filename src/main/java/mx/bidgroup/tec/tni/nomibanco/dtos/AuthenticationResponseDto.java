package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private Set<String> roles;
}
