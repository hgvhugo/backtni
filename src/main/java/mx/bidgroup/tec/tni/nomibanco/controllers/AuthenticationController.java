package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationRequestDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationResponseDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.services.IAuthenticationService;

 
@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Autenticación", description = "Endpoint para la autenticación")
public class AuthenticationController {
    
    @Autowired
    private IAuthenticationService authenticationService;

    
    @Operation(summary = "Método para realizar el inicio de sesión de un usuario", 
            description = "Este endpoint permite a los usuarios iniciar sesión. " +
                          "Lanza una AuthenticationException si ocurre un error durante la autenticación. " +
                          "Lanza una Exception si ocurre un error general durante el proceso.")
    @PostMapping(consumes = "application/json", produces = "application/json" )
    public ResponseEntity<?> login(@RequestBody  AuthenticationRequestDto authenticationRequestDto) throws AuthenticationException, Exception {
        
        List<AuthenticationResponseDto> a = new ArrayList<>();
        GenericResponseDto<AuthenticationResponseDto> genericResponseDto = new GenericResponseDto<>();

        try{
            AuthenticationResponseDto authenticationResponseDto = authenticationService.authenticate(authenticationRequestDto);
            a.add(authenticationResponseDto);
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Autenticación exitosa");
            genericResponseDto.setData(a);

            return ResponseEntity
                .status(HttpStatus.OK)
                .header("Authorization", authenticationResponseDto.getToken())
                .body(genericResponseDto);

        }catch (AuthenticationException e ) {
            throw new AuthenticationException("Error al autenticar, causa: "+e.getMessage()){};
        } catch(Exception e){
            throw new Exception(e.getMessage()){};          
        } 
    }

}
