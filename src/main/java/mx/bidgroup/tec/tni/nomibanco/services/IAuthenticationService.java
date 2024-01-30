package mx.bidgroup.tec.tni.nomibanco.services;

import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationRequestDto;
import mx.bidgroup.tec.tni.nomibanco.dtos.AuthenticationResponseDto;

public interface IAuthenticationService {

    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
    
}
