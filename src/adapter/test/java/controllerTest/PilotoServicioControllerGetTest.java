package controllerTest;

import ar.edu.undec.pilotos.controller.PilotoServicioControllerGet;
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
public class PilotoServicioControllerGetTest {
    @Mock
    private EntradaPiloto entradaPiloto;

    @InjectMocks
    private PilotoServicioControllerGet pilotoServicioControllerGet;

    @Test
    public void testPilotoServicioListarPilotoExitosa() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia(rand.nextInt(900),"nombre","apellido","nombrecompleto","abreviacion","url" );
        List<Piloto> pilotos=new ArrayList<>();
        pilotos.add(piloto);
        ResponseEntity<?> esperado=ResponseEntity.status(HttpStatus.OK).body(pilotos);
        when(entradaPiloto.listapiloto()).thenReturn(pilotos);
        ResponseEntity<?> resultado= pilotoServicioControllerGet.listarPilotos();
        Assertions.assertEquals(esperado,resultado);
    }

    @Test
    public void testPilotoServicioListarPilotoSinPilotosEnBaseDatos() {
        List<Piloto> pilotos=new ArrayList<>();
        ResponseEntity<?>esperado=ResponseEntity.status(HttpStatus.OK).body("No hay pilotos cargados");
        when(entradaPiloto.listapiloto()).thenReturn(pilotos);
        ResponseEntity<?> resultado= pilotoServicioControllerGet.listarPilotos();
        Assertions.assertEquals(esperado,resultado);
    }

}
