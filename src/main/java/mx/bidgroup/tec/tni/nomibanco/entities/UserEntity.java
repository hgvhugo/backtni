package mx.bidgroup.tec.tni.nomibanco.entities;

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
@Table(name = "usuarios")
public class UserEntity implements UserDetails {
 
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "rfc")
    private String rfc;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
 
    @Column(name = "estatus")
    private Boolean estatus;

    @Column(name = "fecha_alta")
    @CreationTimestamp
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_baja")    
    private LocalDateTime fechaBaja;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDateTime fechaModificacion; 

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles"
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