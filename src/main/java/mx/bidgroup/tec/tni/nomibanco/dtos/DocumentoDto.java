package mx.bidgroup.tec.tni.nomibanco.dtos;

import java.time.LocalDateTime;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonIgnore;

// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

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
public class DocumentoDto {
    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    @NotNull(message = "El id es requerido" , groups = {OnUpdate.class})
    private Long id;

    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del servicio" )
    // @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El id es requerido" , groups = {OnCreate.class, OnUpdate.class})
    private Long idCatDocumento;

    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    // @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El id es requerido" , groups = {OnCreate.class, OnUpdate.class})
    private Long idSolicitud;

    // @Schema(accessMode = AccessMode.WRITE_ONLY , description = "Identificador del menu" )
    // @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El id es requerido" , groups = {OnCreate.class, OnUpdate.class})
    private Long idUsuario;

    @NotBlank(message = "El nombre de archivo no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El nombre de archivo es requerido (nombreArchivo)"  , groups = {OnCreate.class, OnUpdate.class})
    private String nombreArchivo;

    @NotBlank(message = "El nombre de archivo no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "El nombre de archivo es requerido (nombreArchivo)"  , groups = {OnCreate.class, OnUpdate.class})
    private String rutaArchivo;

    // @NotBlank(message = "El nombre de archivo no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    // @NotNull(message = "El nombre de archivo es requerido (nombreArchivo)"  , groups = {OnCreate.class, OnUpdate.class})
    // private String ArchivoBase64;

    @NotBlank(message = "La baja lógica no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    @NotNull(message = "La baja lógica es requerida (lowLogic)"  , groups = {OnCreate.class, OnUpdate.class})
    private Boolean lowLogic;
    
    // @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    // @NotNull(message =  "La fecha es requerido"  , groups = {OnUpdate.class})
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha_alta;

    // @NotBlank(message = "La fecha no puede ser vacío"  , groups = {OnCreate.class, OnUpdate.class})
    // @NotNull(message =  "La fecha es requerido"  , groups = {OnUpdate.class})
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fecha_modificacion;
    
}