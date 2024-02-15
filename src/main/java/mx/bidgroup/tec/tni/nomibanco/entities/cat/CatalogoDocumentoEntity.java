package mx.bidgroup.tec.tni.nomibanco.entities.cat;

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
@Table(name = "cat_documentos")
public class CatalogoDocumentoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat_documento")
    private Long id;

    @Column(name = "documento")
    private String documento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_alta")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @Column(name = "fecha_baja")
    private LocalDateTime deletionDate;

    @Column(name = "baja_logica")
    private Boolean lowLogic;
}