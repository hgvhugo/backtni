package mx.bidgroup.tec.tni.nomibanco.repositories.cat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.cat.RoleEntity;


@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findByRol(String rol);
    List<RoleEntity> findByLowLogicFalse();
}
