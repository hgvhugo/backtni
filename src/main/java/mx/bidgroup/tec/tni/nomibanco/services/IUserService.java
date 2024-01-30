package mx.bidgroup.tec.tni.nomibanco.services;

import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.UserDto;

public interface IUserService {

    List<UserDto> getUsers();
    UserDto createUser(UserDto userDto);

}
