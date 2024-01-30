package mx.bidgroup.tec.tni.nomibanco.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import mx.bidgroup.tec.tni.nomibanco.dtos.DependencyDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.GenericResponseDto;
import mx.bidgroup.tec.tni.nomibanco.exceptions.BadRequestException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ConflictException;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
import mx.bidgroup.tec.tni.nomibanco.services.IDependencyService;

@RestController
@RequestMapping("api/v1/dependency")
public class DependencyController {

    @Autowired
    private IDependencyService dependencyService;

    @GetMapping()
    public ResponseEntity<?> getFirstDependency() {

        List<DependencyDto> dependencyList = new ArrayList<>();

        try {
            dependencyList.add(dependencyService.getFirstDependency());
            GenericResponseDto<DependencyDto> genericResponseDto = new GenericResponseDto<>();
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Usuarios obtenidos exitosamente");
            genericResponseDto.setData(dependencyList);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);

        } catch (ResourceNotFoundException ResourceNotFoundException) {
            throw new ResourceNotFoundException("dependencias") {
            }; 
        }catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener usuarios, causa: " + e.getMessage()) {
            };
        }

    }

    @PostMapping()
    public ResponseEntity<?> createDependency(@RequestBody DependencyDto dependencyDto) {

        List<DependencyDto> dependencyList = new ArrayList<>();

        GenericResponseDto<DependencyDto> genericResponseDto = new GenericResponseDto<>();

        try {
            dependencyList.add(dependencyService.createDependency(dependencyDto));
            genericResponseDto.setCode("Success");
            genericResponseDto.setMessage("Dependencia creada exitosamente");
            genericResponseDto.setData(dependencyList);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json")
                    .body(genericResponseDto);
        } catch(ConflictException e){
            throw new ConflictException("Error al crear dependencia, causa: " + e.getMessage()) {
            };
        }catch(BadRequestException e){
            throw new BadRequestException("Error al crear dependencia, causa: " + e.getMessage()) {
            };
        }catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear dependencia, causa: " + e.getMessage()) {
            };
        }

    }

}
