package mx.bidgroup.tec.tni.nomibanco.repositories.conf;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Tuple;

import mx.bidgroup.tec.tni.nomibanco.entities.conf.CatalogAdminEntity;

@Repository
public interface ICatalogAdminRepository extends JpaRepository<CatalogAdminEntity, Long> {

    Boolean existsByCatalogName(String catalogName);

    List<CatalogAdminEntity> findByCatalogName(String catalogName);

    List<CatalogAdminEntity> findByLowLogicFalse();

    @Query(value = "SELECT table_name FROM information_schema.tables WHERE table_type = 'BASE TABLE'"
            , nativeQuery = true)
    List<String> getTableNames();

    @Query(value = "select COLUMN_NAME,DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME =:tableName"
            , nativeQuery = true)
    List<Tuple> getTablesInf(@Param("tableName") String tableName);



}