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
public class MenuDto {

    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long id;
    @NotBlank(message = "El menu no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El menu es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    private String menu;
    @NotBlank(message = "La url no puede ser vacía"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "La url es requerida"  , groups = {OnCreate.class, OnUpdate.class})
    private String url;

    @JsonIgnore
    private LocalDateTime creationDate;
    @JsonIgnore
    private LocalDateTime modificationDate;
    @JsonIgnore
    private LocalDateTime deletionDate;

    @NotNull(message = "La baja logica es requerida (lowLogic)"  , groups = {OnUpdate.class})
    private Boolean lowLogic; 

    private Long idParent;
}
