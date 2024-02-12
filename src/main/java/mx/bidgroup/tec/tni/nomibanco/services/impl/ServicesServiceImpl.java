package mx.bidgroup.tec.tni.nomibanco.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.RolDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.ServiceEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.cat.ServiceEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.cat.MenuEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IServicesRepository;
// import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IMenuRepository;
// import mx.bidgroup.tec.tni.nomibanco.repositories.tbl.QuejasSolicitudesRepository;
// import mx.bidgroup.tec.tni.nomibanco.services.IQuejasSolicitudesService;
import mx.bidgroup.tec.tni.nomibanco.services.IServicesService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class ServicesServiceImpl implements IServicesService {
    private final IServicesRepository servicesRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ServicioDto> getServices() {
         try {
            log.info("Ingresa a getQuejasSolicitudes method de QuejasSolicitudesRepository");

            List<ServiceEntity> ls = servicesRepository.findAll();
            if (ls.isEmpty()) {
                log.error("Error en getServices method de ServicesRepository:  No se encontraron Servicios");
                throw new ResourceNotFoundException("Servicios");
            }

            return ls.stream().map(services -> modelMapper.map(services, ServicioDto.class)).toList();
            
         } catch (Exception e) {
            log.error("Error en getServices method de ServicesRepository: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ServicioDto getServiceById(Long id_servicio) {
        log.info("Ingresa a getServicioById method de ServicesServiceImpl");
        try {
            return modelMapper.map(servicesRepository.findById(id_servicio).orElseThrow(), ServicioDto.class);
        }catch(NoSuchElementException e) {
            log.error("Error en getServicioById method de ServicioServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Servicio", "id_servicio", id_servicio);
        }catch (Exception e) {
            log.error("Error en getRolById method de RoleServiceImpl: {}", e.getMessage());
            throw e;
        }
        
    }
}
