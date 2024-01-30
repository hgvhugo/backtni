package mx.bidgroup.tec.tni.nomibanco.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.bidgroup.tec.tni.nomibanco.dtos.ErrorDto;

@RestController
@RequestMapping("api/v1/prueba")
public class PruebaController {
    

    @GetMapping()
    public ResponseEntity<String> hola(){
        return ResponseEntity.ok("Hola Mundo");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> holaAdmin(){
        try {
            return ResponseEntity.ok("Hola Mundo admin");    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDto  error = new ErrorDto();
            error.setError_message(e.getMessage());
            error.setError_code("Error 1");
            return ResponseEntity.badRequest().body("Error");
        }
        
    }

}
