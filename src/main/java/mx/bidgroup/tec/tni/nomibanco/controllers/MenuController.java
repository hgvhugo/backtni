package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;
import mx.bidgroup.tec.tni.nomibanco.services.IMenuService;
import mx.bidgroup.tec.tni.nomibanco.validations.OnCreate;
import mx.bidgroup.tec.tni.nomibanco.validations.OnUpdate;

@RestController
@RequestMapping("api/v1/menu")
@Tag(name = "Menu", description = "Endpoint para menús")

public class MenuController {

    @Autowired
    private IMenuService menuService;
 

     @Operation(summary = "Método para la obtención de menús", 
            description = "Este endpoint permite obtener una lista de menús " +
                          "Lanza respuesta con estatus 200 OK si hay menús registrados" +
                          "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay menús registrados " +
                          "Lanza una Exception si ocurre un error general durante el proceso.")
    @GetMapping()
    public ResponseEntity<?> getMenus() {
        GenericResponseDto<MenuDto> genericResponseDto = new GenericResponseDto<>();
        
        try {
            List<MenuDto> menus = menuService.getMenus();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Menus obtenidos exitosamente");
            genericResponseDto.setData(menus);
            
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }

    }

    @Operation(summary = "Método para obter un menú por id", 
                description = "Este endpoint permite obtener el menú por id " +
                "Lanza respuesta con estatus 200 OK si hay menú registrado" +
                "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay menús registrados " +
                "Lanza una Exception si ocurre un error general durante el proceso.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuById(@PathVariable Long id) {
        GenericResponseDto<MenuDto> genericResponseDto = new GenericResponseDto<>();
        
        try {
            MenuDto menu = menuService.getMenuById(id);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Menu obtenido exitosamente");
            genericResponseDto.setData(List.of(menu));
            
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }

    }

    @Operation(summary = "Método para la creación de menús", 
                description = "Este endpoint permite crear un menú " +
                "Lanza respuesta con estatus 201 Created si el menú se creó exitosamente" +
                "Lanza una ConflictException con estatus 409 Conflict si el menú ya existe " +
                "Lanza una BadRequestException con estatus 400 Bad Request si el menú ya existe " +
                "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> createMenu(@Validated(OnCreate.class) @RequestBody MenuDto menuDto) {
        GenericResponseDto<MenuDto> genericResponseDto = new GenericResponseDto<>();
        
        try {
            MenuDto menu = menuService.createMenu(menuDto);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Menu creado exitosamente");
            genericResponseDto.setData(List.of(menu));
            
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Método para la actualización de menús", 
                description = "Este endpoint permite actualizar un menú " +
                "Lanza respuesta con estatus 200 OK si el menú se actualizó exitosamente" +
                "Lanza una ResourceNotFoundException con estatus 404 Not Found si el menú no existe " +
                "Lanza una ConflictException con estatus 409 Conflict si el menú ya existe " +
                "Lanza una BadRequestException con estatus 400 Bad Request si faltan campos requeridos, ignora propiedades no existentes " +
                "Lanza una Exception si ocurre un error general durante el proceso.")
    @PutMapping()
    public ResponseEntity<?> updateMenu(@Validated(OnUpdate.class) @RequestBody MenuDto menuDto) {
        GenericResponseDto<MenuDto> genericResponseDto = new GenericResponseDto<>();
        
        try {
            MenuDto menu = menuService.updateMenu(menuDto);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Menu actualizado exitosamente");
            genericResponseDto.setData(List.of(menu));
            
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Método para la eliminación lógica de menús", 
                description = "Este endpoint permite eliminar lógicamente un menú " +
                "Lanza respuesta con estatus 200 OK si el menú se eliminó exitosamente" +
                "Lanza una ResourceNotFoundException con estatus 404 Not Found si el menú no existe " +
                "Lanza una Exception si ocurre un error general durante el proceso.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        GenericResponseDto<MenuDto> genericResponseDto = new GenericResponseDto<>();
        
        try {
            menuService.deleteMenu(id);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Menu eliminado exitosamente");
            genericResponseDto.setData(null);
            
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }
    
}
