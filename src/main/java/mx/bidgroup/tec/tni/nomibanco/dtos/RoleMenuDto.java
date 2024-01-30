package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mx.bidgroup.tec.tni.nomibanco.dtos.rel.RelRoleMenuDto;
import mx.bidgroup.tec.tni.nomibanco.validations.OnUpdate; 

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleMenuDto {

    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private RelRoleMenuDto id;
    @JsonIgnore
    private LocalDateTime creationDate;
    @JsonIgnore
    private LocalDateTime modificationDate;
    @JsonIgnore
    private LocalDateTime deletionDate;
    @NotNull(message = "La baja logica es requerida (lowLogic)"  , groups = {OnUpdate.class})
    private boolean lowLogic;

    @Schema(accessMode = AccessMode.READ_ONLY , description = "Datos del rol", hidden = true)
    private RolDto role;

    @Schema(accessMode = AccessMode.READ_ONLY , description = "Datos del menu", hidden = true)
    private MenuDto menu;

}
