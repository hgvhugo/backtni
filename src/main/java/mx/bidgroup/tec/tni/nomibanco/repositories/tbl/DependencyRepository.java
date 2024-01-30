package mx.bidgroup.tec.tni.nomibanco.repositories.tbl;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.tbl.DependencyEntity;

@Repository
public interface DependencyRepository extends JpaRepository<DependencyEntity, Long> {

    
    boolean existsByRfc(String rfc);

}
