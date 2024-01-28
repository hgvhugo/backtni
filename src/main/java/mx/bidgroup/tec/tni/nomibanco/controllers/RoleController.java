package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag; 
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.RolDto;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.services.IRoleService;
import mx.bidgroup.tec.tni.nomibanco.validations.OnCreate; 
import mx.bidgroup.tec.tni.nomibanco.validations.OnUpdate;

@RestController
@RequestMapping("api/v1/rol")
@Tag(name = "Roles", description = "Endpoint para roles")
public class RoleController {
    
    @Autowired
    private IRoleService roleService;

    @Operation(summary = "Método para la obtención de roles", 
            description = "Este endpoint permite obtener una lista de roles " +
                          "Lanza respuesta con estatus 200 OK si hay roles registrados" +
                          "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay roles registrados " +
                          "Lanza una Exception si ocurre un error general durante el proceso.")
    @GetMapping()
    public ResponseEntity<?> getRoles(){
        List<RolDto> roles = new ArrayList<>();
        GenericResponseDto<RolDto> genericResponseDto = new GenericResponseDto<>();

        try {
            roles = roleService.getRoles();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Roles obtenidos exitosamente");
            genericResponseDto.setData(roles);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        }catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException("Roles");
        }catch (Exception e) {
                 throw e;
        }  
    }

    @Operation(summary = "Método para obter un rol por id", 
                description = "Este endpoint permite obtener el rol por id " +
                "Lanza respuesta con estatus 200 OK si hay rol registrado" +
                "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay roles registrados " +
                "Lanza una Exception si ocurre un error general durante el proceso.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRolById(@PathVariable Long id){

        try {
            RolDto rol = roleService.getRolById(id);
            GenericResponseDto<RolDto> genericResponseDto = new GenericResponseDto<>();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Rol obtenido exitosamente");
            genericResponseDto.setData(List.of(rol));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

            }catch(ResourceNotFoundException e){
                throw new ResourceNotFoundException("Rol", "id", id);
            }catch (Exception e) {
                        throw e;
            }  
    }

    @Operation(summary = "Método para la creación de roles", 
    description = "Este endpoint permite crear un rol" +
                  "Lanza Respuesta con estatus 201 Created si el rol se crea exitosamente " +
                  "Lanza una BadRequestException con estatus 400 Bad Request si faltan campos requeridos, ignora propiedades no existentes " +
                  "Lanza una ConflictException con estatus 409 Conflict si ya existe un rol con el mismo nombre " +
                  "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> createRol(@Validated(OnCreate.class) @RequestBody RolDto rol){
        try {

            RolDto rolDto = roleService.createRol(rol);
            GenericResponseDto<RolDto> genericResponseDto = new GenericResponseDto<>();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Rol creado exitosamente");
            genericResponseDto.setData(List.of(rolDto));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch(BadRequestException e){
           throw new BadRequestException("Error al crear rol, causa: " + e.getMessage()) {
            };
        } catch (Exception e) {
            throw e;
        }        
    }

    @Operation(summary = "Método para la actualización de roles", 
    description = "Este endpoint permite actualizar un rol" +
                  "Lanza Respuesta con estatus 200 OK si el rol se elimina exitosamente " +
                  "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay roles registrados " +
                  "Lanza una BadRequestException con estatus 400 Bad Request si faltan campos requeridos, ignora propiedades no existentes " +
                  "Lanza una ConflictException con estatus 409 Conflict si ya existe un rol con el mismo nombre " +
                  "Lanza una Exception si ocurre un error general durante el proceso.")
    @PutMapping()
    public ResponseEntity<?> updateRol(@Validated(OnUpdate.class) @RequestBody RolDto rol){
        
        try {
            RolDto rolDto = roleService.updateRol(rol);
            GenericResponseDto<RolDto> genericResponseDto = new GenericResponseDto<>();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Rol actualizado exitosamente");
            genericResponseDto.setData(List.of(rolDto));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch (BadRequestException e) {
            throw new BadRequestException("Error al actualizar rol, causa: " + e.getMessage()) {
            };
            
        } catch (Exception e) {
            throw e;
        }
        
    }


    @Operation(summary = "Método para la baja lógica de roles", 
    description = "Este endpoint permite dar de baja lógica un rol" +
                  "Lanza Respuesta con estatus 200 OK si el rol se elimina exitosamente " + 
                  "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay roles registrados " +
                  "Lanza una Exception si ocurre un error general durante el proceso.")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRol( @PathVariable Long id){

        try {
            roleService.deleteRol(id);
            GenericResponseDto<RolDto> genericResponseDto = new GenericResponseDto<>();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Rol eliminado exitosamente");
            genericResponseDto.setData(null);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Rol", "id", id);
            
        } catch (Exception e) {
            throw e;
        }
      
    }


}
