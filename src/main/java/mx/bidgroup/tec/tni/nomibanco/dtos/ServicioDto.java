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
public class ServicioDto {
    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long id;
    
    @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message =  "La fecha es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime creationDate;

    @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message =  "La fecha es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime deletionDate;

    @NotNull(message = "La baja logica es requerida (lowLogic)"  , groups = {OnUpdate.class})
    private Boolean lowLogic;

    @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message =  "La fecha es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime modificationDate;

    @NotBlank(message = "El nombre no puede ser vacía"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El nombre es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private String name;

    // private List<QuejasSolicitudesDto> quejasSolicitudes;
}