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
import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.MenuEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IMenuRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IMenuService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class MenuServiceImpl implements IMenuService {

    private final IMenuRepository menuRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<MenuDto> getMenus() {
         try {
            log.info("Ingresa a getMenus method de MenuServiceImpl");   

            List<MenuEntity> menus = menuRepository.findByLowLogicFalse();
            if (menus.isEmpty()) {
                log.error("Error en getMenus method de MenuServiceImpl:  No se encontraron menus");
                throw new ResourceNotFoundException("Menus");
            }

            return menus.stream().map(menu -> modelMapper.map(menu, MenuDto.class)).toList();
            
         } catch (Exception e) {
            log.error("Error en getMenus method de MenuServiceImpl: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public MenuDto getMenuById(Long id) {
        try {
            log.info("Ingresa a getMenuById method de MenuServiceImpl");   

            try {
                return modelMapper.map(menuRepository.findById(id).orElseThrow(), MenuDto.class);
            
            } catch (NoSuchElementException e) {
                log.error("Error en getMenuById method de MenuServiceImpl: {}", e.getMessage());
                throw new ResourceNotFoundException("Menu", "id", id);
            } catch (Exception e) {
                log.error("Error en getMenuById method de MenuServiceImpl: {}", e.getMessage());
                throw e;
            }
                        

        } catch (Exception e) {
            log.error("Error en getMenuById method de MenuServiceImpl: {}", e.getMessage());
            throw e;
        }
        
    }

    @Override
    public MenuDto createMenu(MenuDto menuDto) {
       try {
            log.info("Ingresa a createMenu method de MenuServiceImpl");   

            MenuEntity menuEntity = modelMapper.map(menuDto, MenuEntity.class);
            menuEntity.setId(0L);
            
            if( menuRepository.findByMenu(menuDto.getMenu()).size() > 0) {
                log.error("Error en createMenu method de MenuServiceImpl: El menu ya existe");
                throw new ConflictException("El menu ya existe");
            }
            menuEntity.setLowLogic(false);
            menuEntity = menuRepository.save(menuEntity);
            return modelMapper.map(menuEntity, MenuDto.class);

       } catch (BadRequestException e){
        log.error("Error en createMenu method de MenuServiceImpl: {}", e.getMessage());
        throw new BadRequestException(e.getMessage());        
        }catch (Exception e) {
            log.error("Error en createMenu method de MenuServiceImpl: {}", e.getMessage());
            throw e;
       }
    }

    @Override
    public MenuDto updateMenu(MenuDto menuDto) {
        try {
            log.info("Ingresa a updateMenu method de MenuServiceImpl");
            MenuEntity menuEntity = menuRepository.findById(menuDto.getId()).orElseThrow();
            
            List<MenuEntity> menus = menuRepository.findByMenu(menuDto.getMenu());

            Optional<MenuEntity> matchingMenu = menus.stream()
                .filter(menu -> !menu.getId().equals(menuDto.getId()))
                .findFirst();

            if(matchingMenu.isPresent()) {
                log.error("Error en updateMenu method de MenuServiceImpl: El menu ya existe con otro id");
                throw new ConflictException("El menu ya existe con otro identificador");
            }   
            menuEntity.setMenu(menuDto.getMenu());

            if (menuDto.getLowLogic())  {
                menuEntity.setDeletionDate(LocalDateTime.now());
            } else {
                menuEntity.setDeletionDate(null);
            }

            menuEntity.setLowLogic(menuDto.getLowLogic());

            menuEntity = menuRepository.save(menuEntity);

            return modelMapper.map(menuEntity, MenuDto.class);

        }catch (NoSuchElementException e) {
            log.error("Error en updateMenu method de MenuServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Menu", "id", menuDto.getId());
        } catch (BadRequestException e) {
            log.error("Error en updateMenu method de MenuServiceImpl: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Error en updateMenu method de MenuServiceImpl: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteMenu(Long id) {
         
        try {
            log.info("Ingresa a deleteMenu method de MenuServiceImpl");
            MenuEntity menuEntity = menuRepository.findById(id).orElseThrow();
            menuEntity.setLowLogic(true);
            menuEntity.setDeletionDate(LocalDateTime.now());
            menuRepository.save(menuEntity);
        } catch (NoSuchElementException e) {
            log.error("Error en deleteMenu method de MenuServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Menu", "id", id);
        
        } catch (ResourceNotFoundException e) {
            log.error("Error en deleteMenu method de MenuServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Menu", "id", id);
        } catch (Exception e) {
            log.error("Error en deleteMenu method de MenuServiceImpl: {}", e.getMessage());
            throw e;
        }
    }

  
    
    
}
