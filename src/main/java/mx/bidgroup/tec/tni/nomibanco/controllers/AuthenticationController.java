package mx.bidgroup.tec.tni.nomibanco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationRequestDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationResponseDto;
import mx.bidgroup.tec.tni.nomibanco.services.IAuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        AuthenticationResponseDto authenticationResponseDto = authenticationService.authenticate(authenticationRequestDto);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", authenticationResponseDto.getToken()).body(authenticationResponseDto);
    }

}
