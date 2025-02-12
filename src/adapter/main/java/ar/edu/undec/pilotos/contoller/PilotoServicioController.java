package ar.edu.undec.pilotos.contoller;

import ar.edu.undec.pilotos.servicio.PilotoServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import excepciones.ExcepcionPiloto;
import input.EntradaPiloto;
import modelo.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/drivers")
public class PilotoServicioController {
    private PilotoServicio pilotoServicio;
    private EntradaPiloto entradaPiloto;

    @Autowired
    public PilotoServicioController(PilotoServicio pilotoServicio, EntradaPiloto entradaPiloto) {
        this.pilotoServicio = pilotoServicio;
        this.entradaPiloto = entradaPiloto;
    }

    @PostMapping()
    public ResponseEntity<?> cargarPilotos() throws JsonProcessingException {
        if (pilotoServicio.sincronizarPilotos())
            return ResponseEntity.status(HttpStatus.CREATED).body("Sincronización de pilotos correcta");
        return ResponseEntity.status(HttpStatus.OK).body("No se agrego nuevos pilotos");
    }

    @GetMapping()
    public ResponseEntity<?> listarPilotos(){
        List<Piloto> pilotos=entradaPiloto.listapiloto();
        if(pilotos.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body("No hay pilotos cargados");
        return ResponseEntity.status(HttpStatus.OK).body(pilotos);
    }


}
