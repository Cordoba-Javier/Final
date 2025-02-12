package controllerTest;

import ar.edu.undec.pilotos.controller.PilotoServicioControllerPost;
import ar.edu.undec.pilotos.servicio.ConsumoApi;
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
public class PilotoServicioControllerPostTest {

    @Mock
    private ConsumoApi consumoApi;

    @Mock
    private EntradaPiloto entradaPiloto;

    @InjectMocks
    private PilotoServicioControllerPost pilotoServicioControllerPost;

    @Test
    public void testPilotoServicioSincronizarPilotosExitosa() {
        ResponseEntity<?>esperado=ResponseEntity.status(HttpStatus.CREATED).body("Sincronización de pilotos correcta");
        when(consumoApi.sincronizarPilotos()).thenReturn(true);
        ResponseEntity<?> resultado= pilotoServicioControllerPost.sincronizarPilotos();
        Assertions.assertEquals(esperado,resultado);
    }

    @Test
    public void testPilotoServicioSincronizarPilotosNo()  {
        ResponseEntity<?>esperado=ResponseEntity.status(HttpStatus.OK).body("No se agrego nuevos pilotos");
        when(consumoApi.sincronizarPilotos()).thenReturn(false);

        ResponseEntity<?> resultado= pilotoServicioControllerPost.sincronizarPilotos();
        Assertions.assertEquals(esperado,resultado);

    }



}
