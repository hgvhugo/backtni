package mx.bidgroup.tec.tni.nomibanco.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
// import mx.bidgroup.tec.tni.nomibanco.dtos.MenuDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;
// import mx.bidgroup.tec.tni.nomibanco.entities.cat.MenuEntity;
import mx.bidgroup.tec.tni.nomibanco.entities.tbl.QuejasSolicitudesEntity;
import mx.bidgroup.tec.tni.nomibanco.exceptions.ResourceNotFoundException;
// import mx.bidgroup.tec.tni.nomibanco.repositories.cat.IMenuRepository;
import mx.bidgroup.tec.tni.nomibanco.repositories.tbl.QuejasSolicitudesRepository;
import mx.bidgroup.tec.tni.nomibanco.services.IQuejasSolicitudesService;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class QuejasSolicitudesServiceImpl implements IQuejasSolicitudesService {
    private final QuejasSolicitudesRepository quejasSolicitudesRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<QuejasSolicitudesDto> getQuejasSolicitudes() {
         try {
            log.info("Ingresa a getQuejasSolicitudes method de QuejasSolicitudesRepository");

            List<QuejasSolicitudesEntity> quejasSolicitudes = quejasSolicitudesRepository.findAll();
            if (quejasSolicitudes.isEmpty()) {
                log.error("Error en getQuejasSolicitudes method de QuejasSolicitudesRepository:  No se encontraron Quejas y/o Solicitudes");
                throw new ResourceNotFoundException("Quejas y/o Solicitudes");
            }

            return quejasSolicitudes.stream().map(quejasolicitud -> modelMapper.map(quejasolicitud, QuejasSolicitudesDto.class)).toList();
            
         } catch (Exception e) {
            log.error("Error en getQuejasSolicitudes method de QuejasSolicitudesRepository: {}", e.getMessage());
            throw e;
        }
    }
}
