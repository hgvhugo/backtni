package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.UserDto;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.services.IUserService;
import org.springframework.web.bind.annotation.GetMapping; 

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<?> getUsers() {
            
        List<UserDto> userList = new ArrayList<>();

        GenericResponseDto<UserDto> genericResponseDto = new GenericResponseDto<>();

        try {
            userList = userService.getUsers();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Usuarios obtenidos exitosamente");
            genericResponseDto.setData(userList);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch (Exception e) {

            throw new BadRequestException("Error al obtener usuarios, causa: " + e.getMessage()) {
            };

        }
        
    }
    

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {

        List<UserDto> userList = new ArrayList<>();

        GenericResponseDto<UserDto> genericResponseDto = new GenericResponseDto<>();

        try {
            UserDto user = userService.createUser(userDto);
            userList.add(user);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Usuario creado exitosamente");
            genericResponseDto.setData(userList);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        }catch (ConflictException e) {
            throw new ConflictException("Error al crear usuario, causa: " + e.getMessage()) {
            };
        } 
        catch (BadRequestException e) {
            throw new BadRequestException("Error al crear usuario, causa: " + e.getMessage()) {
            };
        } 
        
        catch (Exception e) {

            throw new BadRequestException("Error al crear usuario, causa: " + e.getMessage()) {
            };

        }
    }

}