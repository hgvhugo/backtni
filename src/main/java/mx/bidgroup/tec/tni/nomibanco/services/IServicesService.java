package mx.bidgroup.tec.tni.nomibanco.services;
import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;

public interface IServicesService {
    ServicioDto getServiceById(Long id);
    // ServicioDto getServicioById(Long id);
    // MenuDto getMenuById(Long id);
}