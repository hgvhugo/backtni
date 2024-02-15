package mx.bidgroup.tec.tni.nomibanco.services;
import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.CatalogoDocumentoDto;

public interface ICatalogoDocumentoService {
    // ServicioDto getServiceById(Long id);
    List<CatalogoDocumentoDto> getCatalogoDocumento();
    // ServicioDto getServicioById(Long id);
    // MenuDto getMenuById(Long id);
}