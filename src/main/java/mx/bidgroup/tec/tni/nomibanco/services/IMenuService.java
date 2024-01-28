package mx.bidgroup.tec.tni.nomibanco.services;

import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;

public interface IMenuService {

    MenuDto getMenuById(Long id);
    MenuDto createMenu(MenuDto menuDto);
    MenuDto updateMenu(MenuDto menuDto);
    void deleteMenu(Long id);
    List<MenuDto> getMenus();   
}
