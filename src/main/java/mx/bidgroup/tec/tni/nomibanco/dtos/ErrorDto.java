package mx.bidgroup.tec.tni.nomibanco.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDto {

    private String error_code;
    private String error_message;
    
}
