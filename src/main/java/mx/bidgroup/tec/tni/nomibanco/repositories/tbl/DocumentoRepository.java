package mx.bidgroup.tec.tni.nomibanco.repositories.tbl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.tbl.DocumentoEntity;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Long> {
    // boolean existsById(Long Id);
    // List<DocumentoEntity> findByLowLogicFalse();
    List<DocumentoEntity> findAll();
}