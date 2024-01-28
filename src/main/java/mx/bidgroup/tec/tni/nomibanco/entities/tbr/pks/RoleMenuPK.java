package mx.bidgroup.tec.tni.nomibanco.entities.tbr.pks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class RoleMenuPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_rol")
    private Long roleId;

    @Column(name = "id_menu")
    private Long menuId;
    
}