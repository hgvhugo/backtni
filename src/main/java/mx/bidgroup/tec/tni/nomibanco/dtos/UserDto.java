package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.time.LocalDateTime;
import java.util.Set;
 
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    @Schema(accessMode = AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El RFC es requerido")
    @NotNull(message = "El RFC es requerido")
    @Size(min = 12, max = 13, message = "El RFC debe tener entre 12 y 13 caracteres")
    private String rfc;

    @NotBlank(message = "El nombre es requerido")
    @NotNull(message = "El nombre es requerido")
    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    @Email(message = "El email no es valido")
    private String email;

    @Digits(integer = 10, fraction = 0, message = "El telefono debe tener 10 digitos")
    private String telefono;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "El usuario es requerido")
    @NotNull(message = "El usuario es requerido")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "La contraseña es requerida")
    @NotNull(message = "La contraseña es requerida")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "La contraseña debe tener entre 8 y 20 caracteres, contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial")
    private String password;

    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<RolDto> roles;

    private Boolean estatus;
    
    private LocalDateTime fechaAlta;

    private LocalDateTime fechaBaja;
    
    private LocalDateTime fechaModificacion;
    
}
