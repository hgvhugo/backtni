package mx.bidgroup.tec.tni.nomibanco.repositories.cat;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.bidgroup.tec.tni.nomibanco.entities.cat.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
    
    boolean existsByRfc(String rfc);


}
