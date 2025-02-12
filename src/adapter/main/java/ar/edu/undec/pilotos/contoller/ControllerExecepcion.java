package ar.edu.undec.pilotos.contoller;

import ar.edu.undec.pilotos.entidad.ErrorDTO;
import excepciones.ExcepcionPiloto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExecepcion {

    @ExceptionHandler(value = ExcepcionPiloto.class)
    public ResponseEntity<ErrorDTO> excepcionPiloto(ExcepcionPiloto ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getCodigo(),ex.getMessage());
        return new ResponseEntity<>(errorDTO,ex.getEstado());
    }
}
