package modeloTest;

import excepciones.ExcepcionPiloto;
import modelo.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class PilotoTest {
    private Random rand = new Random();

    @Test
    void crearPilotoExitoso() throws ExcepcionPiloto {
        Piloto miPiloto=Piloto.Instancia(rand.nextInt(900),"nombre","apellido","nombreCompleto","abreviasion","url");
        Assertions.assertNotNull(miPiloto);
    }

    @Test
    void crearPilotoExepciones() throws ExcepcionPiloto {
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(null,"nombre","apellido","nombreCompleto","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(rand.nextInt(900),null,"apellido","nombreCompleto","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(rand.nextInt(900),"nombre","","nombreCompleto","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(rand.nextInt(900),"nombre","apellido","","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(rand.nextInt(900),"nombre","apellido","nombreCompleto",null,"url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(rand.nextInt(900),"nombre","apellido","nombreCompleto","abreviasion",null));
    }
}
