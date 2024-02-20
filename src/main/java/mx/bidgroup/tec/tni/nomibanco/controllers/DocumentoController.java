package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import mx.bidgroup.tec.tni.nomibanco.dtos.CatalogoDocumentoDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.DocumentoDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.PlazoDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
// import mx.bidgroup.tec.tni.nomibanco.services.ICatalogoDocumentoService;
import mx.bidgroup.tec.tni.nomibanco.services.IDocumentoService;

@RestController
@RequestMapping("api/v1/documento")
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    @Operation(summary = "Método para la obtención de Documentos", description = "Este endpoint permite obtener una lista de documentos"
            +
            "Lanza respuesta con estatus 200 OK si hay Documentos registrados" +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay documentos registrados " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping()
    public ResponseEntity<?> getDocumento() {
        List<DocumentoDto> ls = new ArrayList<>();
        GenericResponseDto<DocumentoDto> genericResponseDto = new GenericResponseDto<>();

        try {
            ls = documentoService.getDocumento();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Documentos obtenidos exitosamente");
            genericResponseDto.setData(ls);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Documentos");
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Método para la obtención de quejas y solicitudes", description = "Este endpoint permite guardar un solicitud. "
            +
            "Lanza respuesta con estatus 200 OK al guardar la solciitud. " +
            "Lanza una ResourceNotFoundException con estatus 404 Not Found si no hay solicitudes y quejas. " +
            "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping("/create")
    public ResponseEntity<?> createDocumento(@RequestBody DocumentoDto[] obj) {
        // log.info("objeto de insertar solicitud: " + obj);
        // GenericResponseDto<DocumentoDto> genericResponseDto = new
        // GenericResponseDto<>();
        GenericResponseDto<DocumentoDto> genericResponseDocDto = new GenericResponseDto<>();
        try {
            // QuejasSolicitudesDto dto =
            // quejasSolicitudesService.createSolicitudQueja(obj);
            // genericResponseDto.setCode("Success");
            // genericResponseDto.setMessage("Solicitud creada exitosamente");
            // genericResponseDto.setData(List.of(dto));

            // QuejasSolicitudesDto dto =
            // quejasSolicitudesService.createSolicitudQueja(obj);
            // genericResponseDto.setCode("Success");
            // genericResponseDto.setMessage("Solicitud creada exitosamente");
            // genericResponseDto.setData(List.of(dto));
            // System.out.println(dto);
            for (DocumentoDto iterable_element : obj) {  
                iterable_element.setIdSolicitud(5L);
                DocumentoDto documentoDto = documentoService.createDocumento(iterable_element);

                genericResponseDocDto.setCode("Success");
                genericResponseDocDto.setMessage("Documento de la solicitud creada exitosamente");
                genericResponseDocDto.setData(List.of(documentoDto));
            };

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDocDto);
        } catch (Exception e) {
            throw e;
        }
    }
}