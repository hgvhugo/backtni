package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.bidgroup.tec.tni.nomibanco.dtos.ErrorDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
import mx.bidgroup.tec.tni.nomibanco.services.IQuejasSolicitudesService;

@RestController
@RequestMapping("api/v1/quejassolicitudes")
@Tag(name = "QuejasSolicitudes", description = "Endpoint para Quejas y Solciitudes")

public class QuejasSolicitudesController {

    @Autowired
    private IQuejasSolicitudesService quejasSolicitudesService;

    @Operation(summary = "Método para la obtención de quejas y solicitudes", 
            description = "Este endpoint permite obtener una lista de quejas y solicitudes. " +
                          "Lanza respuesta con estatus 200 OK si hay quejas y solciitudes. " +
                          "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay quejas y solicitudes. " +
                          "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> getQuejasSolicitudes() {
        GenericResponseDto<QuejasSolicitudesDto> genericResponseDto = new GenericResponseDto<>();
        try {
            List<QuejasSolicitudesDto> quejasSolicitudes = quejasSolicitudesService.getQuejasSolicitudes();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Quejas y Solicitudes obtenidos exitosamente");
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

    @GetMapping("/admin")
    public ResponseEntity<String> holaAdmin() {
        try {
            return ResponseEntity.ok("Hola Mundo admin");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDto error = new ErrorDto();
            error.setError_message(e.getMessage());
            error.setError_code("Error 1");
            return ResponseEntity.badRequest().body("Error");
        }

    }
}