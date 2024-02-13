package mx.bidgroup.tec.tni.nomibanco.services;
import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.PlazoDto;

public interface IPlazoService {
    // ServicioDto getServiceById(Long id);
    List<PlazoDto> getPlazo();
    // ServicioDto getServicioById(Long id);
    // MenuDto getMenuById(Long id);
}