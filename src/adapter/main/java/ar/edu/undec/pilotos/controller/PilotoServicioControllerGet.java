package ar.edu.undec.pilotos.controller;

import input.EntradaPiloto;
import modelo.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class PilotoServicioControllerGet {
    private EntradaPiloto entradaPiloto;

    @Autowired
    public PilotoServicioControllerGet(EntradaPiloto entradaPiloto) {
        this.entradaPiloto = entradaPiloto;
    }

    @GetMapping()
    public ResponseEntity<?> listarPilotos(){
        List<Piloto> pilotos=entradaPiloto.listapiloto();
        if(pilotos.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body("No hay pilotos cargados");
        return ResponseEntity.status(HttpStatus.OK).body(pilotos);
    }

}
