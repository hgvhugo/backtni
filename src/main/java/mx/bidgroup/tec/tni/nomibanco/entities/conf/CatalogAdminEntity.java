package mx.bidgroup.tec.tni.nomibanco.entities.conf;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "conf_admin_catalogos")
public class CatalogAdminEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre_catalogo")
    private String catalogName;

    @Column(name = "Descripcion")
    private String description;
    
    @Column(name = "Fecha_Alta")
    @CreationTimestamp
    private LocalDateTime creationDate;
 
    @Column(name = "Fecha_Modificacion")
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @Column(name = "Baja_Logica")
    private Boolean lowLogic =false;

    @Column(name = "Fecha_Baja")
    private LocalDateTime deletionDate;

    
}