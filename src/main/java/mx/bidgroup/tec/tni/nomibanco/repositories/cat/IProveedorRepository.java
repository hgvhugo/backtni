package mx.bidgroup.tec.tni.nomibanco.repositories.cat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.ProveedorEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.cat.ServiceEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;

@Repository
public interface IProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
    /*
     * Muestra todos los servicios sin filtro
     */
    List<ProveedorEntity> findAll();
    /**
     * Trae solo el objeto (1 servicio por id)  
     */
    // ProveedorDto getProveedorById(Long id);
}
