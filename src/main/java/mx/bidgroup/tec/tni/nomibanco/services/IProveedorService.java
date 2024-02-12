package mx.bidgroup.tec.tni.nomibanco.services;
import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.ProveedorDto;
// import mx.bidgroup.tec.tni.nomibanco.dtos.ServicioDto;

public interface IProveedorService {
    // ServicioDto getServiceById(Long id);
    List<ProveedorDto> getProveedor();
    // ServicioDto getServicioById(Long id);
    // MenuDto getMenuById(Long id);
}