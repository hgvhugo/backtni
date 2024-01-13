package mx.bidgroup.tec.tni.nomibanco.services;

import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.RolDto;

public interface IRolService {

    RolDto getRolById(Long id);
    List<RolDto> getRoles();
    RolDto createRol(RolDto rolDto);
    RolDto updateRol(RolDto rolDto);
    void deleteRol(Long id);
    

}
