package mx.bidgroup.tec.tni.nomibanco.services.impl;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.PlazoDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.PlazoEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IPlazoRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IPlazoService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class PlazoServiceImpl implements IPlazoService {
    private final IPlazoRepository plazoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PlazoDto> getPlazo() {
         try {
            log.info("Ingresa a getPlazo method de PlazoRepository");

            List<PlazoEntity> ls = plazoRepository.findAll();
            if (ls.isEmpty()) {
                log.error("Error en getPlazo method de IPlazoRepository:  No se encontraron Plazoes");
                throw new ResourceNotFoundException("Plazo");
            }

            return ls.stream().map(plazo -> modelMapper.map(plazo, PlazoDto.class)).toList();
            
         } catch (Exception e) {
            log.error("Error en getPlazo method de IPlazoRepository: {}", e.getMessage());
            throw e;
        }
    }

    // @Override
    // public ServicioDto getServiceById(Long id_servicio) {
    //     log.info("Ingresa a getServicioById method de ServicesServiceImpl");
    //     try {
    //         return modelMapper.map(servicesRepository.findById(id_servicio).orElseThrow(), ServicioDto.class);
    //     }catch(NoSuchElementException e) {
    //         log.error("Error en getServicioById method de ServicioServiceImpl: {}", e.getMessage());
    //         throw new ResourceNotFoundException("Servicio", "id_servicio", id_servicio);
    //     }catch (Exception e) {
    //         log.error("Error en getRolById method de RoleServiceImpl: {}", e.getMessage());
    //         throw e;
    //     }
        
    // }
}
