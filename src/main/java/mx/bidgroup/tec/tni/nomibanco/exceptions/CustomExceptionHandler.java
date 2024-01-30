package mx.bidgroup.tec.tni.nomibanco.exceptions;

import java.util.HashMap;
import java.util.Map;
  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto; 

@RestControllerAdvice 
public class CustomExceptionHandler {

    //controla los errores de autenticacion 401
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GenericResponseDto<?>> handlerAuthenticationException(AuthenticationException exception,
                                                                        WebRequest webRequest) {

        GenericResponseDto<?> genericResponseDto = new GenericResponseDto<>(); 
        genericResponseDto.setCode("Error");
        genericResponseDto.setMessage(exception.getMessage());
        genericResponseDto.setData(null); 
        return new ResponseEntity<>(genericResponseDto, HttpStatus.UNAUTHORIZED);         
    }
    
    
    //controla los errores not found 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GenericResponseDto<?>> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest) {

        GenericResponseDto<?> genericResponseDto = new GenericResponseDto<>(); 
        genericResponseDto.setCode("Error");
        genericResponseDto.setMessage(exception.getMessage());
        genericResponseDto.setData(null); 
        return new ResponseEntity<>(genericResponseDto, HttpStatus.NOT_FOUND);         
    }

    //controla los errores globales de los path en 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<GenericResponseDto<?>> handlerNoHandlerFoundException(NoHandlerFoundException  exception,
                                                                        WebRequest webRequest) {

        GenericResponseDto<?> genericResponseDto = new GenericResponseDto<>(); 
        genericResponseDto.setCode("Error");
        genericResponseDto.setMessage(exception.getMessage());
        genericResponseDto.setData(null);
           
        return new ResponseEntity<>(genericResponseDto, HttpStatus.NOT_FOUND);
    }

    //controla los errores de validacion de los campos 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlderMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                          WebRequest webRequest) {
        Map<String, String> mapErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
                    String clave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();
                    mapErrors.put(clave, valor);
                }
        );

        GenericResponseDto<?> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode("Error");
        genericResponseDto.setMessage("Error en los campos: " + mapErrors.toString());   
        genericResponseDto.setData(null);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.BAD_REQUEST);
    }

     //controla los errores de logica o de los catch en general 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GenericResponseDto<?>> handlerBadRequestException(BadRequestException exception,
                                                                        WebRequest webRequest) {
        GenericResponseDto<?> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode("Error");
        genericResponseDto.setMessage(exception.getMessage());
        genericResponseDto.setData(null);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.BAD_REQUEST);        
    }

    //controla los errores de varios tipos y globalizrlo con un error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDto<?>> handlerException(Exception exception,
                                                                  WebRequest webRequest) {
        GenericResponseDto<?> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode("Error");
        genericResponseDto.setMessage(exception.getMessage());
        genericResponseDto.setData(null);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<GenericResponseDto<?>> handlerConflictException(ConflictException exception,
                                                                  WebRequest webRequest) {
        GenericResponseDto<?> genericResponseDto = new GenericResponseDto<>();
        genericResponseDto.setCode("Error");
        genericResponseDto.setMessage(exception.getMessage());
        genericResponseDto.setData(null);
        return new ResponseEntity<>(genericResponseDto, HttpStatus.CONFLICT);

    }

}
