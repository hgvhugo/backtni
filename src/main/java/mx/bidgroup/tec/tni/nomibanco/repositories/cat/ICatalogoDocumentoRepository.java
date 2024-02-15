package mx.bidgroup.tec.tni.nomibanco.repositories.cat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.cat.CatalogoDocumentoEntity;

@Repository
public interface ICatalogoDocumentoRepository extends JpaRepository<CatalogoDocumentoEntity, Long> {
    /*
     * Muestra todos los plazos sin filtro
     */
    List<CatalogoDocumentoEntity> findAll();
    /**
     * Trae solo el objeto (1 servicio por id)  
     */
    // ProveedorDto getProveedorById(Long id);
}
