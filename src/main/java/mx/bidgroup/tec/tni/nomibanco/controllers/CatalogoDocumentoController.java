package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import mx.bidgroup.tec.tni.nomibanco.dtos.CatalogoDocumentoDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.PlazoDto;

import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.services.ICatalogoDocumentoService;

@RestController
@RequestMapping("api/v1/catalogoDocumento")
public class CatalogoDocumentoController {

    @Autowired
    private ICatalogoDocumentoService catalogoDocumentoService;

    @Operation(summary = "Método para la obtención de Documentos en el catálogo", description = "Este endpoint permite obtener una lista de documentos del catálogo "
            +
            "Lanza respuesta con estatus 200 OK si hay Documentos en el catálogo registrados" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay documentos en catálogo registrados " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> getCatalogoDocumento() {
        List<CatalogoDocumentoDto> ls = new ArrayList<>();
        GenericResponseDto<CatalogoDocumentoDto> genericResponseDto = new GenericResponseDto<>();

        try {
            ls = catalogoDocumentoService.getCatalogoDocumento();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Catálogo de documentos obtenido exitosamente");
            genericResponseDto.setData(ls);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("CatalogoDocumentos");
        } catch (Exception e) {
            throw e;
        }
    }
}