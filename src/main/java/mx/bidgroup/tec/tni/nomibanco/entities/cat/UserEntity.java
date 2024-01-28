package mx.bidgroup.tec.tni.nomibanco.entities.cat;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cat_usuarios")
public class UserEntity implements UserDetails {
 
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id_Usuario")
    private Long id;

    @Column(name = "RFC")
    private String rfc;
    
    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido1")
    private String apellidoPaterno;

    @Column(name = "Apellido2")
    private String apellidoMaterno;

    @Column(name = "Correo_Electronico")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "Username")
    private String username;

    @Column(name = "Contraseña")
    private String password;
 
    @Column(name = "Estatus")
    private Boolean estatus;

    @Column(name = "Fecha_Alta")
    @CreationTimestamp
    private LocalDateTime fechaAlta;

    @Column(name = "Fecha_Baja")    
    private LocalDateTime fechaBaja;

    @Column(name = "Fecha_Modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion; 

    @Column(name = "Baja_Modificacion")
    private boolean bajaModificacion;

    @ManyToMany(fetch = FetchType.EAGER , cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "tbl_usuarios_roles"
        , joinColumns = @JoinColumn(name = "id_usuario" , referencedColumnName = "id_usuario")
        , inverseJoinColumns = @JoinColumn(name = "id_rol" , referencedColumnName = "id_rol"))
    private Set<RoleEntity> roles;
 
    
     
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public  List<GrantedAuthority> getAuthorities() {
         List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (RoleEntity rol : roles) { 
            grantedAuthorities.add(new SimpleGrantedAuthority(rol.getRol()));
        }

        return grantedAuthorities; 
    }
    @Override
    public boolean isCredentialsNonExpired() {
       
        return true;
    }
    @Override
    public boolean isEnabled() {
        
        return true;
    }
    
}