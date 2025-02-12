package casoDeUsoTest;

import casoDeUso.RegistrarPiloto;
import excepciones.ExcepcionPiloto;
import modelo.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositorio.RegistrarPilotoRepositorio;

import java.util.Random;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrarPilotoCasoDeUsoTest {

    @Mock
    RegistrarPilotoRepositorio registrarPilotoRepositorio;

    @InjectMocks
    private RegistrarPiloto registrarPiloto;



    @Test
    void registrarPilotoNoExiste()throws ExcepcionPiloto {
        Random random = new Random();
        Piloto miPiloto=Piloto.Instancia(random.nextInt(),"nombre","apellido","nombreCompleto","abreviasion","url");


        when(registrarPilotoRepositorio.existeNombre(miPiloto.getNombre())).thenReturn(false);
        when(registrarPilotoRepositorio.existeAbreviasion(miPiloto.getAbreviasion())).thenReturn(false);
        when(registrarPilotoRepositorio.guardarPiloto(miPiloto)).thenReturn(true);

        boolean resultado=registrarPiloto.cargarPiloto(miPiloto);

        Assertions.assertTrue(resultado);
    }

    @Test
    void registrarPilotoYaExisteNombre()throws ExcepcionPiloto {
        Random random = new Random();
        Piloto miPiloto=Piloto.Instancia(random.nextInt(),"nombre","apellido","nombreCompleto","abreviasion","url");

        when(registrarPilotoRepositorio.existeNombre(miPiloto.getNombre())).thenReturn(true);

        boolean resultado=registrarPiloto.cargarPiloto(miPiloto);

        Assertions.assertFalse(resultado);
    }

    @Test
    void registrarPilotoYaExisteAbreviacion()throws ExcepcionPiloto {
        Random random = new Random();
        Piloto miPiloto=Piloto.Instancia(random.nextInt(),"nombre","apellido","nombreCompleto","abreviasion","url");

        when(registrarPilotoRepositorio.existeNombre(miPiloto.getNombre())).thenReturn(false);
        when(registrarPilotoRepositorio.existeAbreviasion(miPiloto.getAbreviasion())).thenReturn(true);

        boolean resultado=registrarPiloto.cargarPiloto(miPiloto);

        Assertions.assertFalse(resultado);
    }
}
