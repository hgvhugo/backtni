package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
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
@Builder
public class RolDto {

    // @Schema(accessMode = AccessMode.READ_ONLY , description = "Identificador del rol")
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long id;

    @NotBlank(message = "El rol no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El rol es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private String rol;
    
    @JsonIgnore
    private LocalDateTime creationDate;
    @JsonIgnore
    private LocalDateTime modificationDate;
    @JsonIgnore
    private LocalDateTime deletionDate;

    @NotNull(message = "La baja logica es requerida (lowLogic)"  , groups = {OnUpdate.class})
    private Boolean lowLogic;


}
