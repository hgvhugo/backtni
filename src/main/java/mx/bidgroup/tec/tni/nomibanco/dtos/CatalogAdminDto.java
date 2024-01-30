package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.time.LocalDateTime;
 

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class CatalogAdminDto {
 
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long id;
 
    @NotNull(message = "El nombre del catalogo es requerido"  , groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(message = "El nombre del catalogo no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    private String catalogName;
 
    private String description;
      
    @NotNull(message = "La baja logica es requerida (lowLogic)"  , groups = {OnUpdate.class})
    private Boolean lowLogic ;
 
    @JsonIgnore
    private LocalDateTime creationDate;
    @JsonIgnore
    private LocalDateTime modificationDate;
    @JsonIgnore
    private LocalDateTime deletionDate;

}
