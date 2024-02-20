package mx.bidgroup.tec.tni.nomibanco.services;
import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.DocumentoDto;

public interface IDocumentoService {
    // ServicioDto getServiceById(Long id);
    List<DocumentoDto> getDocumento();
    // ServicioDto getServicioById(Long id);
    // MenuDto getMenuById(Long id);
    DocumentoDto createDocumento(DocumentoDto obj);
}