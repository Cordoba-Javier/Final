package ar.edu.undec.pilotos.contoller;

import ar.edu.undec.pilotos.servicio.PilotoServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import excepciones.ExcepcionPiloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PilotoServicioController {
    private PilotoServicio pilotoServicio;

    @Autowired
    public PilotoServicioController(PilotoServicio pilotoServicio) {
        this.pilotoServicio = pilotoServicio;
    }

    @GetMapping("/sincronizarpilotos")
    public String sincronizarPilotos() throws ExcepcionPiloto, JsonProcessingException {
        pilotoServicio.sincronizarPilotos();
        return "Pilotos sincronizados correctamente";
    }

}
