package mx.bidgroup.tec.tni.nomibanco.services.impl;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.ProveedorDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.ProveedorEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IProveedorRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IProveedorService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class ProveedorServiceImpl implements IProveedorService {
    private final IProveedorRepository proveedorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ProveedorDto> getProveedor() {
         try {
            log.info("Ingresa a getProveedor method de ProveedorRepository");

            List<ProveedorEntity> ls = proveedorRepository.findAll();
            if (ls.isEmpty()) {
                log.error("Error en getProveedor method de IProveedorRepository:  No se encontraron Proveedores");
                throw new ResourceNotFoundException("Proveedor");
            }

            return ls.stream().map(proveedor -> modelMapper.map(proveedor, ProveedorDto.class)).toList();
            
         } catch (Exception e) {
            log.error("Error en getProveedor method de IProveedorRepository: {}", e.getMessage());
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
