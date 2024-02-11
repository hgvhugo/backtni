package mx.bidgroup.tec.tni.nomibanco.repositories.cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.ServiceEntity;

@Repository
public interface IServicesRepository extends JpaRepository<ServiceEntity, Long> {
    ServicioDto getServiceById(Long id);
}
