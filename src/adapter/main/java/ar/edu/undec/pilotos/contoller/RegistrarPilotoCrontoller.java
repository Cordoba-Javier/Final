package ar.edu.undec.pilotos.contoller;


import ar.edu.undec.pilotos.entidad.PilotoDTO;
import ar.edu.undec.pilotos.mapeo.Mapeo;
import input.EntradaPiloto;
import modelo.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegistrarPilotoCrontoller {
    private EntradaPiloto entradaPiloto;

    @Autowired
    public RegistrarPilotoCrontoller(EntradaPiloto entradaPiloto) {
        this.entradaPiloto = entradaPiloto;
    }

    @PostMapping("/cargapiloto")
    public ResponseEntity<?> crearPiloto(@RequestBody PilotoDTO piloto) {
        try{
            if(entradaPiloto.cargarPiloto(Mapeo.mapeoDTOCore(piloto))){
                return ResponseEntity.ok("Piloto creado correctamente");
            }
            return ResponseEntity.badRequest().body("No se pudo cargar Piloto");

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/piloto/{nombre}")
    public ResponseEntity<?> obtenerPiloto(@PathVariable String nombre) {
        try{
            Optional<?> p=entradaPiloto.obtenerPiloto(nombre);
            return ResponseEntity.ok(p);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listapiloto")
    public ResponseEntity<?> listaPilotos() {
        try{
            return ResponseEntity.ok(entradaPiloto.listapiloto());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
