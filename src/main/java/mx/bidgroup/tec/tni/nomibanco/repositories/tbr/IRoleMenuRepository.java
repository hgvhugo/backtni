package mx.bidgroup.tec.tni.nomibanco.repositories.tbr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.tbr.RoleMenuEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.tbr.pks.RoleMenuPK;

@Repository
public interface IRoleMenuRepository extends JpaRepository<RoleMenuEntity, RoleMenuPK> {

    // List<RoleMenuEntity> findByRoleMenu(String roleMenu);
    List<RoleMenuEntity> findByLowLogicFalse();
    List<RoleMenuEntity> findById_RoleIdAndLowLogicFalse(Long idRol);

    
}
