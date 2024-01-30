package mx.bidgroup.tec.tni.nomibanco.entities.cat;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
import mx.bidgroup.tec.tni.nomibanco.entities.tbr.RoleMenuEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cat_roles")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id_Rol")
    private Long id;

    @Column(name = "rol" , unique = true , nullable = false , length = 250)
    private String rol;
    
    
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


    @OneToMany(mappedBy = "role")
    private Set<RoleMenuEntity> rolemenus;
 
    

}