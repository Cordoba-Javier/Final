package ar.edu.undec.pilotos.contoller;


import ar.edu.undec.pilotos.entidad.PilotoDTO;
import ar.edu.undec.pilotos.mapeo.Mapeo;
import input.EntradaPiloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("piloto")
public class RegistrarPilotoCrontoller {
    private EntradaPiloto entradaPiloto;

    @Autowired
    public RegistrarPilotoCrontoller(EntradaPiloto entradaPiloto) {
        this.entradaPiloto = entradaPiloto;
    }

    @PostMapping("piloto")
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


}
