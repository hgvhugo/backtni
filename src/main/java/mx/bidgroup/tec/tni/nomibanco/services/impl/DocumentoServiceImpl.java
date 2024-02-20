package mx.bidgroup.tec.tni.nomibanco.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.DocumentoDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.DocumentoDto;
import mx.bidgroup.tec.tni.nomibanco.entities.tbl.DocumentoEntity;
// import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.repositories.tbl.DocumentoRepository;
// import mx.bidgroup.tec.tni.nomibanco.repositories.tbl.QuejasSolicitudesRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IDocumentoService;
// import mx.bidgroup.tec.tni.nomibanco.services.IQuejasSolicitudesService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class DocumentoServiceImpl implements IDocumentoService {
    private final DocumentoRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<DocumentoDto> getDocumento() {
        try {
            log.info("Ingresa a getDocumento method de DocumentoRepository");

            List<DocumentoEntity> entity = repository.findAll();
            if (entity.isEmpty()) {
                log.error(
                        "Error en getDocumento method de DocumentoRepository:  No se encontraron Documento");
                throw new ResourceNotFoundException("Documento");
            }

            return entity.stream()
                    .map(mapeo -> modelMapper.map(mapeo, DocumentoDto.class)).toList();

        } catch (Exception e) {
            log.error("Error en getDocumento method de DocumentoRepository: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public DocumentoDto createDocumento(DocumentoDto obj) {
        log.info("Ingresa a createDocumento service");
        try {
            DocumentoEntity entity = modelMapper.map(obj, DocumentoEntity.class);
            entity.setId(0L);
            entity = repository.save(entity);
            DocumentoDto entityResponse = modelMapper.map(entity, DocumentoDto.class);
            return entityResponse;
        } catch (ConflictException e) {
            log.error("Error en createDocumento service: {}", e.getMessage());
            throw new ConflictException("Error en createDocumento service");
        } catch (Exception e) {
            log.error("Error en createDocumento service: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error en createDocumento service");
        }

    }
}
