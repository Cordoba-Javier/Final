package controllerTest;

import ar.edu.undec.pilotos.contoller.PilotoServicioController;
import ar.edu.undec.pilotos.servicio.PilotoServicio;
import input.EntradaPiloto;
import modelo.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PilotoServicioControllerTest {

    @Mock
    private PilotoServicio pilotoServicio;

    @Mock
    private EntradaPiloto entradaPiloto;

    @InjectMocks
    private PilotoServicioController pilotoServicioController;

    @Test
    public void testPilotoServicioSincronizarPilotosExitosa() {
        ResponseEntity<?>esperado=ResponseEntity.status(HttpStatus.CREATED).body("Sincronización de pilotos correcta");
        when(pilotoServicio.sincronizarPilotos()).thenReturn(true);
        ResponseEntity<?> resultado=pilotoServicioController.sincronizarPilotos();
        Assertions.assertEquals(esperado,resultado);
    }

    @Test
    public void testPilotoServicioSincronizarPilotosNo()  {
        ResponseEntity<?>esperado=ResponseEntity.status(HttpStatus.OK).body("No se agrego nuevos pilotos");
        when(pilotoServicio.sincronizarPilotos()).thenReturn(false);

        ResponseEntity<?> resultado=pilotoServicioController.sincronizarPilotos();
        Assertions.assertEquals(esperado,resultado);

    }

    @Test
    public void testPilotoServicioListarPilotoExitosa() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia(rand.nextInt(900),"nombre","apellido","nombrecompleto","abreviacion","url" );
        List<Piloto> pilotos=new ArrayList<>();
        pilotos.add(piloto);
        ResponseEntity<?>esperado=ResponseEntity.status(HttpStatus.OK).body(pilotos);
        when(entradaPiloto.listapiloto()).thenReturn(pilotos);
        ResponseEntity<?> resultado=pilotoServicioController.listarPilotos();
        Assertions.assertEquals(esperado,resultado);
    }

    @Test
    public void testPilotoServicioListarPilotoSinPilotosEnBaseDatos() {
        List<Piloto> pilotos=new ArrayList<>();
        ResponseEntity<?>esperado=ResponseEntity.status(HttpStatus.OK).body("No hay pilotos cargados");
        when(entradaPiloto.listapiloto()).thenReturn(pilotos);
        ResponseEntity<?> resultado=pilotoServicioController.listarPilotos();
        Assertions.assertEquals(esperado,resultado);
    }


}
