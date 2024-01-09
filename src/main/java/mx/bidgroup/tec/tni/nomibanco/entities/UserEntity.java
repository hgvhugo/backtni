package mx.bidgroup.tec.tni.nomibanco.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
 
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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
 
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