package mx.bidgroup.tec.tni.nomibanco.entities.tbl;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
// import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
// import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;
// import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.tbr.RoleMenuEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_documentos")
public class DocumentoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;

    @Column(name = "id_cat_documento")
    private Long idCatDocumento;

    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @Column(name = "ruta_archivo")
    private String rutaArchivo;

    @Column(name = "estatus")
    private Boolean lowLogic;

    @Column(name = "fecha_alta")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime modificationDate;
}