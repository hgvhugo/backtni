package mx.bidgroup.tec.tni.nomibanco.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.DependencyDto;
import mx.bidgroup.tec.tni.nomibanco.entities.tbl.DependencyEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.tbl.DependencyRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IDependencyService;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class DependencyServiceImpl implements IDependencyService {

    private final DependencyRepository dependencyRepository;
    private final ModelMapper modelMapper;

    @Override
    public DependencyDto createDependency(DependencyDto dependencyDto) {
        log.info("Ingresa a createDependency service");
        try {
            if(dependencyRepository.existsByRfc(dependencyDto.getRfc())) {
                throw new ConflictException( "Ya existe una dependencia con RFC indicado.");
            }

            DependencyEntity dependency = modelMapper.map(dependencyDto, DependencyEntity.class);
            dependency.setId(0L);

            dependency = dependencyRepository.save(dependency);

            DependencyDto dependencyResponse = modelMapper.map(dependency, DependencyDto.class);
            
            return dependencyResponse;
            
        } catch (ConflictException e) {
            log.error("Error en createDependency service: {}", e.getMessage());
            throw new ConflictException("Error en createDependency service");
        }catch(Exception e){
            log.error("Error en createUser service: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en createUser service");
        }
        
    }

    @Override
    public DependencyDto updateDependency(DependencyDto dependencyDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDependency'");
    }

    @Override
    public void deleteDependency(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDependency'");
    }

    @Override
    public <Optional> DependencyDto getFirstDependency() {
        log.info("Ingresa a getFirstDependency service");

        try {
            DependencyEntity dependency = dependencyRepository.findAll().get(0);
            if(dependency == null || dependency.getId() == null) {
                log.error("Error en getFirstDependency service: {}", "No se encontraron dependencias");
                throw new ResourceNotFoundException("dependencias");
            }
            DependencyDto dependencyResponse = modelMapper.map(dependency, DependencyDto.class);
            return dependencyResponse;
        } catch (IndexOutOfBoundsException e) {
            log.error("Error en getFirstDependency service: {}", e.getMessage());
            throw new ResourceNotFoundException( "dependencias");
        }catch (ResourceNotFoundException e) {
            log.error("Error en getFirstDependency service: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron dependencias");
        }catch (Exception e) {
            log.error("Error en getFirstDependency service: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en getFirstDependency service");                
        }
    }
    
}
