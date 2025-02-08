package modeloTest;

import excepciones.ExcepcionPiloto;
import modelo.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PilotosTest {

    @Test
    void crearPilotoExitoso() throws ExcepcionPiloto {
        Piloto miPiloto=Piloto.Instancia(7l,"nombre","apellido","nombreCompleto","abreviasion","url");
        Assertions.assertNotNull(miPiloto);
    }

    @Test
    void crearPilotoExepciones() throws ExcepcionPiloto {
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(null,"nombre","apellido","nombreCompleto","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(7l,null,"apellido","nombreCompleto","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(7l,"nombre","","nombreCompleto","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(7l,"nombre","apellido","","abreviasion","url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(7l,"nombre","apellido","nombreCompleto",null,"url"));
        Assertions.assertThrows(ExcepcionPiloto.class,()->Piloto.Instancia(7l,"nombre","apellido","nombreCompleto","abreviasion",null));
    }
}
