package mx.bidgroup.tec.tni.nomibanco.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.bidgroup.tec.tni.nomibanco.dtos.RolDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.UserDto;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.RoleEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.cat.UserEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IRoleRepository;
import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IUserRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IUserService;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserDto> getUsers() {

        log.info("Ingresa a getUsers service");

        try {
            List<UserDto> users = new ArrayList<>();
            userRepository.findAll().forEach(user -> {
                users.add(modelMapper.map(user, UserDto.class));
            });
            log.info("Obtencion exitosa de Usuarios, cantidad: {}", users.size());
            return users;
        } catch (Exception e) {
            log.error("Error en getUsers service: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en getUsers service");
        }

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Ingresa a createUser service");
        
        try{
            //Valida si existe el usuario por username o rfc
            if (userRepository.existsByUsername(userDto.getUsername())) {
                throw new ConflictException( "Usuario ya existe");
            }
            if (userRepository.existsByRfc(userDto.getRfc())) {
                throw new ConflictException( "Ya existe un usuario con RFC indicado.");
            }

            // Crea un objeto UserEntity a partir de UserDto
            UserEntity user = modelMapper.map(userDto, UserEntity.class);
            
            
            
            // Verifica si los roles existen en el roleRepository
            Set<RolDto> roles = userDto.getRoles();
            
            Set<RoleEntity> roleEntities = new HashSet<>();

            for (RolDto rolDto : roles) {
                RoleEntity roleEntity = new RoleEntity();

                if (!roleRepository.existsById(rolDto.getId())) {
                    log.error("Error en createUser service: El rol con ID {} no existe.", rolDto.getId());
                    throw new BadRequestException("El rol con ID " + rolDto.getId() + " no existe.");
                } else {
                    roleEntity = roleRepository.findById(rolDto.getId()).orElseThrow();
                    log.info("El rol con ID {} existe.", rolDto.getId());
                }
                roleEntities.add(roleEntity);
            }
            
            // Mapea los roles a Set<RolEntity>
            

            user.setRoles(roleEntities);

            
            
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            // Guarda el objeto UserEntity en la base de datos
            user = userRepository.save(user);

            // Mapea el objeto UserEntity a UserDto
            UserDto savedUserDto = modelMapper.map(user, UserDto.class);

            return savedUserDto;
        }catch(ConflictException e){
            log.error("Error en createUser service: {}", e.getMessage());
            throw e;
        }catch(Exception e){
            log.error("Error en createUser service: {}", e.getMessage());
           throw e;
        }
        
    }
}
