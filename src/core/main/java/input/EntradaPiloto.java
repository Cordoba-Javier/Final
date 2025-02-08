package input;

import excepciones.ExcepcionPiloto;
import modelo.Piloto;

public interface EntradaPiloto  {
    boolean cargarPiloto(Piloto piloto)throws ExcepcionPiloto;
}
