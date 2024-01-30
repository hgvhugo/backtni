package mx.bidgroup.tec.tni.nomibanco.services;


import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.RoleMenuDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.rel.RelRoleMenuDto;

public interface IRoleMenuService {

    RoleMenuDto createRoleMenu(RoleMenuDto roleMenuDto);
    RoleMenuDto updateRoleMenu(RoleMenuDto roleMenuDto);
    void deleteRoleMenu(RelRoleMenuDto relRoleMenuDto);
    RoleMenuDto getRoleMenuById(RelRoleMenuDto relRoleMenuDto);
    List<RoleMenuDto> getRoleMenus();
    List<RoleMenuDto> getRoleMenusByRoleId(Long idRol);

}
