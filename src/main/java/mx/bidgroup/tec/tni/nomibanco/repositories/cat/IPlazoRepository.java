package mx.bidgroup.tec.tni.nomibanco.repositories.cat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.cat.PlazoEntity;
// import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;
// import mx.bidgroup.tec.tni.nomibanco.entities.cat.ServiceEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;

@Repository
public interface IPlazoRepository extends JpaRepository<PlazoEntity, Long> {
    /*
     * Muestra todos los plazos sin filtro
     */
    List<PlazoEntity> findAll();
    /**
     * Trae solo el objeto (1 servicio por id)  
     */
    // ProveedorDto getProveedorById(Long id);
}
