package mx.bidgroup.tec.tni.nomibanco.repositories.cat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.cat.MenuEntity; 

@Repository
public interface IMenuRepository extends JpaRepository<MenuEntity, Long> {

    
    List<MenuEntity> findByMenu(String menu);
    List<MenuEntity> findByLowLogicFalse();
}
