package input;

import excepciones.ExcepcionPiloto;
import modelo.Piloto;
import java.util.List;

public interface EntradaPiloto  {
    boolean cargarPiloto(Piloto piloto)throws ExcepcionPiloto;

    List<Piloto> listapiloto() throws ExcepcionPiloto;
}
