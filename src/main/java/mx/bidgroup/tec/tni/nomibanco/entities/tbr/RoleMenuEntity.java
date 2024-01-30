package mx.bidgroup.tec.tni.nomibanco.entities.tbr;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.MenuEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.RoleEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.tbr.pks.RoleMenuPK;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_rol_menu")
public class RoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoleMenuPK id;

    @Column(name = "fecha_alta")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @Column(name = "fecha_baja")
    private LocalDateTime deletionDate;

    @Column(name = "baja_logica")
    private boolean lowLogic = false;

    @MapsId("id_role")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private RoleEntity role;

    @MapsId("id_menu")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    private MenuEntity menu;
    
}