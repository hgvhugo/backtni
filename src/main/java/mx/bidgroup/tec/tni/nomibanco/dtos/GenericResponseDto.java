package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GenericResponseDto<T> {

    private String code;
    private String message;
    List<T> data;

}
