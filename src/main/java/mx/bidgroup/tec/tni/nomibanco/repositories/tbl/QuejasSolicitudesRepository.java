package mx.bidgroup.tec.tni.nomibanco.repositories.tbl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import mx.bidgroup.tec.tni.nomibanco.entities.cat.MenuEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;

@Repository
public interface QuejasSolicitudesRepository extends JpaRepository<QuejasSolicitudesEntity, Long> {
    // boolean existsById(Long Id);
    // List<QuejasSolicitudesEntity> findByLowLogicFalse();
    List<QuejasSolicitudesEntity> findAll();
}