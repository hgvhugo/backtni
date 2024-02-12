package mx.bidgroup.tec.tni.nomibanco.services;
import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;

public interface IServicesService {
    ServicioDto getServiceById(Long id);
    List<ServicioDto> getServices();
    // ServicioDto getServicioById(Long id);
    // MenuDto getMenuById(Long id);
}