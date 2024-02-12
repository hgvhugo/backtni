package mx.bidgroup.tec.tni.nomibanco.services;
import java.util.List;

import mx.bidgroup.tec.tni.nomibanco.dtos.QuejasSolicitudesDto;

public interface IQuejasSolicitudesService {
    // List<QuejasSolicitudesDto> getQuejasSolicitudesById(Long id);
    List<QuejasSolicitudesDto> getQuejasSolicitudes();
    // QuejasSolicitudesDto getSolicitudesQuejasById(Long id);
}