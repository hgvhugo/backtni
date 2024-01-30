package mx.bidgroup.tec.tni.nomibanco.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.RolDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.RoleEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IRoleRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IRoleService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class RoleServiceImpl implements IRoleService{

    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RolDto getRolById(Long id) {
        log.info("Ingresa a getRolById method de RoleServiceImpl");
        try {
            return modelMapper.map(roleRepository.findById(id).orElseThrow(), RolDto.class);
        }catch(NoSuchElementException e) {
            log.error("Error en getRolById method de RoleServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Rol", "id", id);
        }catch (Exception e) {
            log.error("Error en getRolById method de RoleServiceImpl: {}", e.getMessage());
            throw e;
        }
        
    }

    @Override
    public List<RolDto> getRoles() { 
        try {
            log.info("Ingresa a getRoles method de RoleServiceImpl");
            // List<RoleEntity> roles = roleRepository.findAll();
            List<RoleEntity> roles = roleRepository.findByLowLogicFalse();

            if (roles.isEmpty()) {
                  log.error("Error en getRoles method de RoleServiceImpl:  No se encontraron roles");
                throw new ResourceNotFoundException("Roles");
            }
            return roles.stream().map(role -> modelMapper.map(role, RolDto.class)).toList();

        } catch (Exception e) {
            log.error("Error en getRoles method de RoleServiceImpl: {}", e.getMessage());
            throw e;
        }
        
    }

    @Override
    public RolDto createRol(RolDto rolDto) {
      
        try {
            log.info("Ingresa a createRol method de RoleServiceImpl");
            RoleEntity roleEntity = modelMapper.map(rolDto, RoleEntity.class);
            roleEntity.setId(0L);

            if( roleRepository.findByRol(rolDto.getRol()).size() > 0) {
                log.error("Error en createRol method de RoleServiceImpl: El rol ya existe");
                throw new ConflictException("El rol ya existe");
            }
            roleEntity = roleRepository.save(roleEntity);
            return modelMapper.map(roleEntity, RolDto.class);
        }catch (BadRequestException e) {
            log.error("Error en createRol method de RoleServiceImpl: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Error en createRol method de RoleServiceImpl: {}", e.getMessage());
            throw e;
        }

    }

    @Override
    public RolDto updateRol(RolDto rolDto) {
        try {
            
            log.info("Ingresa a updateRol method de RoleServiceImpl");

            RoleEntity roleEntity = roleRepository.findById(rolDto.getId()).orElseThrow();
            
            List<RoleEntity> roles = roleRepository.findByRol(rolDto.getRol());

            Optional<RoleEntity> matchingRole = roles.stream()
                .filter(role -> !role.getId().equals(rolDto.getId()))
                .findFirst();

            if(matchingRole.isPresent()) {
                log.error("Error en updateRol method de RoleServiceImpl: El rol ya existe con otro id");
                throw new ConflictException("El rol ya existe con otro identificador");
            }
            roleEntity.setRol(rolDto.getRol());

            if (rolDto.getLowLogic())  {
                roleEntity.setDeletionDate(LocalDateTime.now());
            } else {
                roleEntity.setDeletionDate(null);
            }


            roleEntity.setLowLogic(rolDto.getLowLogic());



            roleEntity = roleRepository.save(roleEntity);
            return modelMapper.map(roleEntity, RolDto.class);
        } catch(NoSuchElementException e) {
            log.error("Error en updateRol method de RoleServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Rol", "id", rolDto.getId());
        } catch (BadRequestException e) {
            log.error("Error en updateRol method de RoleServiceImpl: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Error en updateRol method de RoleServiceImpl: {}", e.getMessage());
            throw e;
         }
    }

    @Override
    public void deleteRol(Long id) { 
        try {
            log.info("Ingresa a deleteRol method de RoleServiceImpl");
            RoleEntity roleEntity = roleRepository.findById(id).orElseThrow();
            roleEntity.setDeletionDate(LocalDateTime.now());
            roleEntity.setLowLogic(true);
            roleRepository.save(roleEntity);
        } catch(NoSuchElementException e) {
            log.error("Error en deleteRol method de RoleServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Rol", "id", id);
           
        } catch(ResourceNotFoundException e) {
            log.error("Error en deleteRol method de RoleServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Rol", "id", id);
        } catch (BadRequestException e) {
            log.error("Error en deleteRol method de RoleServiceImpl: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Error en deleteRol method de RoleServiceImpl: {}", e.getMessage());
            throw e;
        }
    }
    

}
