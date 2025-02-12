package ar.edu.undec.pilotos.controller;

import ar.edu.undec.pilotos.servicio.ConsumoApi;
import input.EntradaPiloto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/drivers")
public class PilotoServicioControllerPost {
    private ConsumoApi consumoApi;


    @Autowired
    public PilotoServicioControllerPost(ConsumoApi consumoApi, EntradaPiloto entradaPiloto) {
        this.consumoApi = consumoApi;
    }

    @PostMapping()
    public ResponseEntity<?> sincronizarPilotos()  {
        if (consumoApi.sincronizarPilotos())
            return ResponseEntity.status(HttpStatus.CREATED).body("Sincronización de pilotos correcta");
        return ResponseEntity.status(HttpStatus.OK).body("No se agrego nuevos pilotos");
    }

}
