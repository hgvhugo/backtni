package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.RoleMenuDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.rel.RelRoleMenuDto;
import mx.bidgroup.tec.tni.nomibanco.services.IRoleMenuService;
import mx.bidgroup.tec.tni.nomibanco.validations.OnCreate;


@RestController
@RequestMapping("api/v1/rolemenu")
public class RoleMenuController {
    
    @Autowired
    private IRoleMenuService roleMenuService;

 
    @GetMapping()
    public ResponseEntity<?> getRoleMenus(){
        
        GenericResponseDto<RoleMenuDto> genericResponseDto = new GenericResponseDto<>();

        try {
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("RoleMenus obtenidos exitosamente");
            genericResponseDto.setData(roleMenuService.getRoleMenus());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);    
        } catch (Exception e) {
            throw e;
        }
        
    }

    @GetMapping("/{roleId}/{menuId}")
    public ResponseEntity<?> getRoleMenuById(@PathVariable Long roleId, @PathVariable Long menuId){
        
        GenericResponseDto<RoleMenuDto> genericResponseDto = new GenericResponseDto<>();
        RelRoleMenuDto relRoleMenuDto = new RelRoleMenuDto();
        relRoleMenuDto.setId_rol(roleId);
        relRoleMenuDto.setId_menu(menuId);

        try {
            RoleMenuDto roleMenuDto = roleMenuService.getRoleMenuById(relRoleMenuDto);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("RoleMenu obtenido exitosamente");
            genericResponseDto.setData(List.of(roleMenuDto));

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);    
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    @PostMapping()
    public ResponseEntity<?> createRoleMenu(@Validated(OnCreate.class) @RequestBody RoleMenuDto roleMenuDto){
        
        GenericResponseDto<RoleMenuDto> genericResponseDto = new GenericResponseDto<>();

        try {
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("RoleMenu creado exitosamente");
            genericResponseDto.setData(List.of(roleMenuService.createRoleMenu(roleMenuDto)));

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);    
        } catch (Exception e) {
            throw e;
        }
        
    }
    
}
