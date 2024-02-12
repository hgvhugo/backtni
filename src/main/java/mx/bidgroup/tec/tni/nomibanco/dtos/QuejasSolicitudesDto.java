package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.time.LocalDateTime;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mx.bidgroup.tec.tni.nomibanco.validations.OnCreate;
import mx.bidgroup.tec.tni.nomibanco.validations.OnUpdate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuejasSolicitudesDto {
    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long id;

    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long idServicio;

    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long idTni;

    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long idUsuario;

    @NotNull(message = "La baja lógica es requerida (lowLogic)"  , groups = {OnUpdate.class})
    private Long Estatus;
    
    @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message =  "La fecha es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime fecha_alta;

    @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message =  "La fecha es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime fecha_modificacion;

    // @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    // @NotNull(message =  "La fecha es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private ServicioDto servicio;
    
}