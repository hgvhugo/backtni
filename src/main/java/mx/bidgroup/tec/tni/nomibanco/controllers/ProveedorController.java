package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.ProveedorDto;

import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.services.IProveedorService;


@RestController
@RequestMapping("api/v1/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;


    @Operation(summary = "Método para la obtención de proveedores", 
            description = "Este endpoint permite obtener una lista de proveedores " +
                          "Lanza respuesta con estatus 200 OK si hay proveedores registrados" +
                          "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay proveedores registrados " +
                          "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> getProveedor(){
        List<ProveedorDto> ls = new ArrayList<>();
        GenericResponseDto<ProveedorDto> genericResponseDto = new GenericResponseDto<>();

        try {
            ls = proveedorService.getProveedor();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Proveedores obtenidos exitosamente");
            genericResponseDto.setData(ls);

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

    // @Operation(summary = "Método para obter un servicio por id", description = "Este endpoint permite obtener el servicio por id "
    //         +
    //         "Lanza respuesta con estatus 200 OK si hay servicio registrado" +
    //         "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay servicios registrados " +
    //         "Lanza una Exception si ocurre un error general durante el proceso.")
    // @GetMapping("/{id}")
    // public ResponseEntity<?> getServicioById(@PathVariable Long id) {

    //     try {
    //         ServicioDto obj = servicesService.getServiceById(id);
    //         GenericResponseDto<ServicioDto> genericResponseDto = new GenericResponseDto<>();
    //         genericResponseDto.setCode("Success");
    //         genericResponseDto.setMessage("Servicio obtenido exitosamente");
    //         genericResponseDto.setData(List.of(obj));

    //         return ResponseEntity
    //                 .status(HttpStatus.OK)
    //                 .header("Content-Type", "application/json")
    //                 .body(genericResponseDto);

    //     } catch (ResourceNotFoundException e) {
    //         throw new ResourceNotFoundException("Servicio", "id", id);
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }
}