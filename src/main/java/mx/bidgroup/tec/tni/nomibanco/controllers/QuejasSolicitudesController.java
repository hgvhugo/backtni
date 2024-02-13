package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
import mx.bidgroup.tec.tni.nomibanco.services.IQuejasSolicitudesService;
import mx.bidgroup.tec.tni.nomibanco.validations.OnCreate;

@RestController
@RequestMapping("api/v1/quejassolicitudes")
@Tag(name = "QuejasSolicitudes", description = "Endpoint para Quejas y Solciitudes")

public class QuejasSolicitudesController {

    @Autowired
    private IQuejasSolicitudesService quejasSolicitudesService;

    @Operation(summary = "Método para la obtención de quejas y solicitudes", description = "Este endpoint permite obtener una lista de solicitudes y quejas. "
            +
            "Lanza respuesta con estatus 200 OK si hay solciitudes y quejas. " +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay solicitudes y quejas. " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> getQuejasSolicitudes() {
        GenericResponseDto<QuejasSolicitudesDto> genericResponseDto = new GenericResponseDto<>();
        try {
            List<QuejasSolicitudesDto> quejasSolicitudes = quejasSolicitudesService.getQuejasSolicitudes();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Solicitudesy Quejas obtenidos exitosamente");
            genericResponseDto.setData(quejasSolicitudes);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
        // return ResponseEntity.ok("Hola Mundo");
    }
    
    @Operation(summary = "Método para la obtención de quejas y solicitudes", description = "Este endpoint permite guardar un solicitud. "
    +
    "Lanza respuesta con estatus 200 OK al guardar la solciitud. " +
    "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay solicitudes y quejas. " +
    "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping("/create")
    public ResponseEntity<?> createSolicitudesQuejas(@RequestBody QuejasSolicitudesDto obj) {
        GenericResponseDto<QuejasSolicitudesDto> genericResponseDto = new GenericResponseDto<>();

        try {

            QuejasSolicitudesDto dto = quejasSolicitudesService.createSolicitudQueja(obj);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Solicitud creada exitosamente");
            genericResponseDto.setData(List.of(dto));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (Exception e) {
            throw e;
        }
    }
    // @PostMapping()
    // public ResponseEntity<?> createSolicitudesQuejas(@RequestBody QuejasSolicitudesDto obj) {

    //     List<QuejasSolicitudesDto> ls = new ArrayList<>();

    //     GenericResponseDto<QuejasSolicitudesDto> genericResponseDto = new GenericResponseDto<>();

    //     try {
    //         ls.add(quejasSolicitudesService.createSolicitudQueja(obj));
    //         genericResponseDto.setCode("Success");
    //         genericResponseDto.setMessage("Solicitud creada exitosamente");
    //         genericResponseDto.setData(ls);

    //         return ResponseEntity
    //                 .status(HttpStatus.CREATED)
    //                 .header("Content-Type", "application/json")
    //                 .body(genericResponseDto);
    //     } catch(ConflictException e){
    //         throw new ConflictException("Error al crear solicitud, causa: " + e.getMessage()) {
    //         };
    //     }catch(BadRequestException e){
    //         throw new BadRequestException("Error al crear solicitud, causa: " + e.getMessage()) {
    //         };
    //     }catch (Exception e) {
    //         throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear solicitud, causa: " + e.getMessage()) {
    //         };
    //     }

    // }

    // @Operation(summary = "Método para obter una Solicitud y/o queja por id", description = "Este endpoint permite obtener la solicitud por id "
    //         +
    //         "Lanza respuesta con estatus 200 OK si hay Solicitud registrado" +
    //         "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay Solicituds registrados " +
    //         "Lanza una Exception si ocurre un error general durante el proceso.")
    // @GetMapping("/{id}")
    // public ResponseEntity<?> getSolicitudQuejaById(@PathVariable Long id) {
    //     try {
    //         ServicioDto obj = quejasSolicitudesService.getServiceById(id);
    //         GenericResponseDto<ServicioDto> genericResponseDto = new GenericResponseDto<>();
    //         genericResponseDto.setCode("Success");
    //         genericResponseDto.setMessage("Solicitud obtenido exitosamente");
    //         genericResponseDto.setData(List.of(obj));

    //         return ResponseEntity
    //                 .status(HttpStatus.OK)
    //                 .header("Content-Type", "application/json")
    //                 .body(genericResponseDto);

    //     } catch (ResourceNotFoundException e) {
    //         throw new ResourceNotFoundException("Solicitud", "id", id);
    //     } catch (Exception e) {
    //         throw e;
    //     }
    // }

    // @GetMapping("/admin")
    // public ResponseEntity<String> holaAdmin() {
    // try {
    // return ResponseEntity.ok("Hola Mundo admin");
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // ErrorDto error = new ErrorDto();
    // error.setError_message(e.getMessage());
    // error.setError_code("Error 1");
    // return ResponseEntity.badRequest().body("Error");
    // }

    // }

}