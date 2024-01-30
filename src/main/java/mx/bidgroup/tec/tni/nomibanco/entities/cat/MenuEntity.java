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
@Table(name = "cat_menus")
public class MenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    @Column(name = "Id_Menu")
    private Long id;
    
    // @OneToOne
    // @JoinColumn(name = "Id_Rol")
    // private RoleEntity id_Rol;

    @Column(name = "Menu" , unique = true , nullable = false , length = 250)    
    private String menu;
 
    // @Column(name = "Estatus")
    // private Boolean status;

    @Column(name = "Url")
    private String url;
    
    @Column(name = "Fecha_Alta")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "Fecha_Modificacion")
    @UpdateTimestamp
    private LocalDateTime modificationDate;

    @Column(name = "Fecha_Baja")
    private LocalDateTime deletionDate;

    @Column(name = "Baja_Logica")
    private Boolean lowLogic  =false;

    @Column(name = "Id_Padre")
    private Long idParent;

    @OneToMany(mappedBy = "menu")
    private Set<RoleMenuEntity> rolemenus;

}