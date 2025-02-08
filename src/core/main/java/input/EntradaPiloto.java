package input;

import excepciones.ExcepcionPiloto;
import modelo.Piloto;

import java.util.List;
import java.util.Optional;

public interface EntradaPiloto  {
    boolean cargarPiloto(Piloto piloto)throws ExcepcionPiloto;

    Optional<?> obtenerPiloto(String nombre)throws ExcepcionPiloto;

    List<Piloto> listapiloto() throws ExcepcionPiloto;
}
