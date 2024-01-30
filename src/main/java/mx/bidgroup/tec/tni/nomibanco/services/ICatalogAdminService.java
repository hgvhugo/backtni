package mx.bidgroup.tec.tni.nomibanco.services;

import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.CatalogAdminDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.ColumnDto;

public interface ICatalogAdminService {

    CatalogAdminDto createCatalogAdmin(CatalogAdminDto catalogAdminDto);
    CatalogAdminDto updateCatalogAdmin(CatalogAdminDto catalogAdminDto);
    void deleteCatalogAdmin(Long id);
    List<CatalogAdminDto> getCatalogAdmins();
    CatalogAdminDto getCatalogAdminById(Long id);
    List<String> getTables();
    List<ColumnDto> getColumns(String tableName);

}
