package mx.bidgroup.tec.tni.nomibanco.controllers;

import lombok.RequiredArgsConstructor;
import mx.bidgroup.tec.tni.nomibanco.dtos.CatalogAdminDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.ColumnDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.services.ICatalogAdminService;
import mx.bidgroup.tec.tni.nomibanco.validations.OnCreate;
import mx.bidgroup.tec.tni.nomibanco.validations.OnUpdate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/catalogadmin")
@Tag(name = "Administrador de Catalogos", description = "Endpoint para administrar catálogos")
public class CatalogAdminController {

    @Autowired
    private ICatalogAdminService catalogAdminService;

    @Operation(summary = "Método para la obtención de catálogos administrados", description = "Este endpoint permite obtener una lista de catálogos "
            +
            "Lanza respuesta con estatus 200 OK si hay catálogos registrados" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay catálogos registrados " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @GetMapping()
    public ResponseEntity<?> findAll() {

        GenericResponseDto<CatalogAdminDto> genericResponseDto = new GenericResponseDto<>();

        try {
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Catalogos obtenidos exitosamente");
            genericResponseDto.setData(catalogAdminService.getCatalogAdmins());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Método para la obtención de un catálogo por id", description = "Este endpoint permite obtener un catálogo por id "
            +
            "Lanza respuesta con estatus 200 OK si hay catálogo registrado" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay catálogo registrado " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        GenericResponseDto<CatalogAdminDto> genericResponseDto = new GenericResponseDto<>();
        try {

            CatalogAdminDto catalogAdminDto = catalogAdminService.getCatalogAdminById(id);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Catalogo obtenido exitosamente");
            genericResponseDto.setData(List.of(catalogAdminDto));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Método para la creación de un catálogo", description = "Este endpoint permite crear un catálogo "
            +
            "Lanza respuesta con estatus 201 OK si se crea el catálogo" +
            "Lanza una BadRequestException con estatus 400 Bad Request si faltan campos requeridos" +
            "Lanza una ConflictException con estatus 409 Conflict si ya existe el catálogo " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> create(@Validated(OnCreate.class) @RequestBody CatalogAdminDto dto) {
        GenericResponseDto<CatalogAdminDto> genericResponseDto = new GenericResponseDto<>();

        try {

            CatalogAdminDto catalogAdminDto = catalogAdminService.createCatalogAdmin(dto);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Catalogo creado exitosamente");
            genericResponseDto.setData(List.of(catalogAdminDto));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Método para la actualización de un catálogo", description = "Este endpoint permite actualizar un catálogo "
            +
            "Lanza respuesta con estatus 200 OK si se actualiza el catálogo" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no existe el catálogo " +
            "Lanza una BadRequestException con estatus 400 Bad Request si faltan campos requeridos" +
            "Lanza una ConflictException con estatus 409 Conflict si ya existe el catálogo " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @PutMapping()
    public ResponseEntity<?> update(@Validated(OnUpdate.class) @RequestBody CatalogAdminDto dto) {
        GenericResponseDto<CatalogAdminDto> genericResponseDto = new GenericResponseDto<>();
        try {

            CatalogAdminDto catalogAdminDto = catalogAdminService.updateCatalogAdmin(dto);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Catalogo actualizado exitosamente");
            genericResponseDto.setData(List.of(catalogAdminDto));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;

        }
    }

    @Operation(summary = "Método para la baja lógica de un catálogo", description = "Este endpoint permite da de baja lógica un catálogo "
            +
            "Lanza respuesta con estatus 200 OK si se elimina el catálogo" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no existe el catálogo " +
            "Lanza una Exception si ocurre un error general durante el proceso.")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        GenericResponseDto<CatalogAdminDto> genericResponseDto = new GenericResponseDto<>();
        try {
                
                catalogAdminService.deleteCatalogAdmin(id);
                genericResponseDto.setCode("Success");
                genericResponseDto.setMessage("Catalogo eliminado exitosamente");
                genericResponseDto.setData(null);
    
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Método para la obtención de nombres de tablas", description = "Este endpoint permite obtener una lista de tablas"
            +
            "Lanza respuesta con estatus 200 OK si hay catálogos registrados" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay catálogos registrados " +
            "Lanza una Exception si ocurre un error general durante el proceso.")

    @GetMapping("/tables")
    public ResponseEntity<?> getTables() {
        GenericResponseDto<String> genericResponseDto = new GenericResponseDto<>();
        try {
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Tablas obtenidas exitosamente");
            genericResponseDto.setData(catalogAdminService.getTables());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
            
        }         
    }
    
    @Operation(summary = "Método para la obtención de datos de columnas de una tabla", description = "Este endpoint permite obtener una lista de columnas"
            +
            "Lanza respuesta con estatus 200 OK si hay datos registrados" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay datos registrados " +
            "Lanza una Exception si ocurre un error general durante el proceso.")

    @GetMapping("/columns")
        public ResponseEntity<?> getColumnsInfo(@RequestParam("tableName")  String tableName) {
            GenericResponseDto<ColumnDto> genericResponseDto = new GenericResponseDto<>();
            try {
                
                genericResponseDto.setCode("Success");
                genericResponseDto.setMessage("Columnas obtenidas exitosamente");
                genericResponseDto.setData(catalogAdminService.getColumns(tableName));
    
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(genericResponseDto);
                
            } catch (Exception e) {
                throw e;
            }
        }
            
}
