package mx.bidgroup.tec.tni.nomibanco.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
 
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.CatalogAdminDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.ColumnDto;
import mx.bidgroup.tec.tni.nomibanco.entities.conf.CatalogAdminEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.conf.ICatalogAdminRepository;
import mx.bidgroup.tec.tni.nomibanco.services.ICatalogAdminService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class CatalogAdminServiceImpl implements ICatalogAdminService {


    private final ICatalogAdminRepository catalogAdminRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CatalogAdminDto> getCatalogAdmins() {
        try {
            log.info("Ingresa a getCatalogAdmins method de CatalogAdminServiceImpl");   

            // List<CatalogAdminEntity> catalogAdmins = catalogAdminRepository.findByLowLogicFalse();
            List<CatalogAdminEntity> catalogAdmins = catalogAdminRepository.findAll();
            if (catalogAdmins.isEmpty()) {
                log.error("Error en getCatalogAdmins method de CatalogAdminServiceImpl:  No se encontraron catalogos");
                throw new ResourceNotFoundException("Catalogos");
            }

            return catalogAdmins.stream().map(catalogAdmin -> modelMapper.map(catalogAdmin, CatalogAdminDto.class)).toList();
            
         } catch (Exception e) {
            log.error("Error en getCatalogAdmins method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public CatalogAdminDto getCatalogAdminById(Long id) {
        try {
            log.info("Ingresa a getCatalogAdminById method de CatalogAdminServiceImpl");   

            return modelMapper.map(catalogAdminRepository.findById(id).orElseThrow(), CatalogAdminDto.class);
            
        } catch (NoSuchElementException e) {
            log.error("Error en getCatalogAdminById method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Catalogo", "id", id);
        } catch (Exception e) {
            log.error("Error en getCatalogAdminById method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw e;
        }
    }
    


    @Override
    public CatalogAdminDto createCatalogAdmin(CatalogAdminDto catalogAdminDto) {
        
        try {
            log.info("Ingresa a createCatalogAdmin method de CatalogAdminServiceImpl");
           
            CatalogAdminEntity catalogAdmin = modelMapper.map(catalogAdminDto, CatalogAdminEntity.class);
            catalogAdmin.setId(0L);

            if(!catalogAdminRepository.getTableNames().contains(catalogAdminDto.getCatalogName())) {
                log.error("Error en createCatalogAdmin method de CatalogAdminServiceImpl:  La tabla indicada no existe");
                throw new BadRequestException("La tabla indicada no existe, favor de verificar");
            }

            if (catalogAdminRepository.existsByCatalogName(catalogAdminDto.getCatalogName())) {
                log.error("Error en createCatalogAdmin method de CatalogAdminServiceImpl:  Ya existe un catalogo con el nombre indicado");
                throw new ConflictException("Ya existe un catalogo con el nombre indicado");
            }
            catalogAdmin = catalogAdminRepository.save(catalogAdmin);
            return modelMapper.map(catalogAdmin, CatalogAdminDto.class);
            
        } catch (BadRequestException e) {
            log.error("Error en createCatalogAdmin method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }catch(Exception e){
            log.error("Error en createCatalogAdmin method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public CatalogAdminDto updateCatalogAdmin(CatalogAdminDto catalogAdminDto) {
        try {
            log.info("Ingresa a updateCatalogAdmin method de CatalogAdminServiceImpl");

            catalogAdminRepository.findById(catalogAdminDto.getId()).orElseThrow();

            CatalogAdminEntity catalogAdmin = modelMapper.map(catalogAdminDto, CatalogAdminEntity.class);

            List<CatalogAdminEntity> catalogAdmins = catalogAdminRepository.findByCatalogName(catalogAdminDto.getCatalogName());

            Optional<CatalogAdminEntity> catalogAdminOptional = catalogAdmins.stream()
                    .filter(catalog -> !catalog.getId().equals(catalogAdminDto.getId())).findFirst();

            if (catalogAdminOptional.isPresent()) {
                log.error("Error en updateCatalogAdmin method de CatalogAdminServiceImpl:  Ya existe un catalogo con el nombre indicado");
                throw new ConflictException("Ya existe un catalogo con el nombre indicado");
            }

            if(catalogAdminDto.getLowLogic()) {
                catalogAdmin.setDeletionDate(LocalDateTime.now());                
            }else{
                catalogAdmin.setDeletionDate(null);
            }

            catalogAdmin = catalogAdminRepository.save(catalogAdmin);
            return modelMapper.map(catalogAdmin, CatalogAdminDto.class);
        } catch (NoSuchElementException e){
            log.error("Error en updateCatalogAdmin method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Catalogo", "id", catalogAdminDto.getId());
        } catch (BadRequestException e) {
            log.error("Error en updateCatalogAdmin method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Error en updateCatalogAdmin method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw e; 
        }      
    }

    @Override
    public void deleteCatalogAdmin(Long id) {
       
        try {
            log.info("Ingresa a deleteCatalogAdmin method de CatalogAdminServiceImpl");
            CatalogAdminEntity catalogAdmin = catalogAdminRepository.findById(id).orElseThrow();
            catalogAdmin.setDeletionDate(LocalDateTime.now());
            catalogAdmin.setLowLogic(true);
            catalogAdminRepository.save(catalogAdmin);

        } catch (NoSuchElementException e) {
            log.error("Error en deleteCatalogAdmin method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw new ResourceNotFoundException("Catalogo", "id", id);
        } catch (Exception e) {
            log.error("Error en deleteCatalogAdmin method de CatalogAdminServiceImpl: {}", e.getMessage());
            throw e;
            
        }
    }

    @Override
    public List<String> getTables() {
       try {
         log.info("Ingresa a getTables method de CatalogAdminServiceImpl");
         
        return catalogAdminRepository.getTableNames();
         
       } catch (Exception e) {
        log.error("Error en getTables method de CatalogAdminServiceImpl: {}", e.getMessage());
        throw e;
       }
    }

    @Override
    public List<ColumnDto> getColumns(String tableName) {
       try {
            log.info("Ingresa a getColumns method de CatalogAdminServiceImpl");
            
            List<Tuple> columns = catalogAdminRepository.getTablesInf(tableName);
            List<ColumnDto> columnsDto = columns.stream().map(column ->{

                ColumnDto columnDto = new ColumnDto();
                columnDto.setName(column.get(0, String.class));
                columnDto.setType(column.get(1, String.class));
                return columnDto;
            }
            
            ).toList();

            if(getTables().stream().noneMatch(table -> table.equals(tableName))) {
                log.error("Error en getColumns method de CatalogAdminServiceImpl:  La tabla indicada no existe");
                throw new BadRequestException("La tabla indicada no existe, favor de verificar");
            }

            return columnsDto;

       } catch (Exception e) {
        log.error("Error en getColumns method de CatalogAdminServiceImpl: {}", e.getMessage());
        throw e;
       }
    }
}