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
import mx.bidgroup.tec.tni.nomibanco.dtos.RoleMenuDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.rel.RelRoleMenuDto;
import mx.bidgroup.tec.tni.nomibanco.entities.tbr.RoleMenuEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.tbr.pks.RoleMenuPK;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IMenuRepository;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IRoleRepository;
import mx.bidgroup.tec.tni.nomibanco.repositories.tbr.IRoleMenuRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IRoleMenuService;

@Service
@Transactional
@Log4j2
@AllArgsConstructor
public class RoleMenuServiceImpl implements IRoleMenuService {
    
    
    private final IRoleMenuRepository roleMenuRepository;
    private final IRoleRepository roleRepository;
    private final IMenuRepository   menuRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleMenuDto createRoleMenu(RoleMenuDto roleMenuDto) {
       
        try {
            log.info("Ingresa a createRoleMenu method de RoleMenuServiceImpl");   

            RelRoleMenuDto rel = new RelRoleMenuDto();
            rel.setId_rol(roleMenuDto.getId().getId_rol());
            rel.setId_menu(roleMenuDto.getId().getId_menu());

            if(!roleRepository.existsById(rel.getId_rol())) {
                log.error("Error en createRoleMenu method de RoleMenuServiceImpl:  No existe el rol");
                throw new ResourceNotFoundException("rol");
            }

            if(!menuRepository.existsById(rel.getId_menu())) {
                log.error("Error en createRoleMenu method de RoleMenuServiceImpl:  No existe el menu");
                throw new ResourceNotFoundException("menu");
            }

            if(roleRepository.findById(rel.getId_rol()).get().getLowLogic()) {
                log.error("Error en createRoleMenu method de RoleMenuServiceImpl:  El rol esta dado de baja");
                throw new BadRequestException("El rol esta dado de baja");
            }

            if(menuRepository.findById(rel.getId_menu()).get().getLowLogic()) {
                log.error("Error en createRoleMenu method de RoleMenuServiceImpl:  El menu esta dado de baja");
                throw new BadRequestException("El menu esta dado de baja");
            }
            
            RoleMenuPK roleMenuPK =  new RoleMenuPK();
            roleMenuPK.setRoleId(rel.getId_rol());
            roleMenuPK.setMenuId(rel.getId_menu());

            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setId(roleMenuPK);
            roleMenuEntity.setRole(roleRepository.findById(rel.getId_rol()).get());
            roleMenuEntity.setMenu(menuRepository.findById(rel.getId_menu()).get());
           
            
            
            if(roleMenuRepository.existsById(roleMenuEntity.getId())) {
                log.error("Error en createRoleMenu method de RoleMenuServiceImpl:  Ya existe la relacion rol-menu");
                throw new ConflictException("La relacion rol-menu ya existe");
            }

            
            roleMenuRepository.save(roleMenuEntity);

            return modelMapper.map(roleMenuEntity, RoleMenuDto.class);


            // return modelMapper.map(roleMenuRepository.save(roleMenuEntity), RoleMenuDto.class);
            
        }catch (BadRequestException e) {
            log.error("Error en createRoleMenu method de RoleMenuServiceImpl: {}", e.getMessage());
            throw e;
        }catch (Exception e) {
            log.error("Error en createRoleMenu method de RoleMenuServiceImpl: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public RoleMenuDto updateRoleMenu(RoleMenuDto roleMenuDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRoleMenu'");
    }

    @Override
    public void deleteRoleMenu(RelRoleMenuDto relRoleMenuDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRoleMenu'");
    }

    @Override
    public RoleMenuDto getRoleMenuById(RelRoleMenuDto relRoleMenuDto) {
        
        try {
            log.info("Ingresa a getRoleMenuById method de RoleMenuServiceImpl");
            RoleMenuPK roleMenuPK = new RoleMenuPK();
            roleMenuPK.setRoleId(relRoleMenuDto.getId_rol());
            roleMenuPK.setMenuId(relRoleMenuDto.getId_menu());

               
          
            return modelMapper.map(roleMenuRepository.findById(roleMenuPK).orElseThrow(), RoleMenuDto.class);
            
        } catch (NoSuchElementException e) {
            log.error("Error en getRoleMenuById method de RoleMenuServiceImpl: {}", e.getMessage());
            throw  new ResourceNotFoundException("relacion rol-menu");
        }catch (Exception e) {
            log.error("Error en getRoleMenuById method de RoleMenuServiceImpl: {}", e.getMessage());
            throw e;
        }
        
    }

    @Override
    public List<RoleMenuDto> getRoleMenus() {

        try {
            log.info("Ingresa a getRoleMenus method de RoleMenuServiceImpl");   
            // List<RoleMenuEntity> roleMenus = roleMenuRepository.findAll();
            List<RoleMenuEntity> roleMenus = roleMenuRepository.findByLowLogicFalse();
            if (roleMenus.isEmpty()) {
                log.error("Error en getRoleMenus method de RoleMenuServiceImpl:  No se encontraron roleMenus");
                throw new ResourceNotFoundException("relacion rol-menu");
            }

            return roleMenus.stream().map(roleMenu -> modelMapper.map(roleMenu, RoleMenuDto.class)).toList();
            
        } catch (Exception e) {
            log.error("Error en getRoleMenus method de RoleMenuServiceImpl: {}", e.getMessage());
            throw e;
        }
        
    }

    
  
 

}
