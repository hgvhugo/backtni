package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.PlazoDto;

import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.services.IPlazoService;

@RestController
@RequestMapping("api/v1/plazo")
public class PlazoController {

    @Autowired
    private IPlazoService plazoService;

    @Operation(summary = "Método para la obtención de plazo", description = "Este endpoint permite obtener una lista de plazo "
            +
            "Lanza respuesta con estatus 200 OK si hay plazo registrados" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay proveedores registrados " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> getPlazo() {
        List<PlazoDto> ls = new ArrayList<>();
        GenericResponseDto<PlazoDto> genericResponseDto = new GenericResponseDto<>();

        try {
            ls = plazoService.getPlazo();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Plazos obtenidos exitosamente");
            genericResponseDto.setData(ls);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Plazos");
        } catch (Exception e) {
            throw e;
        }
    }
}