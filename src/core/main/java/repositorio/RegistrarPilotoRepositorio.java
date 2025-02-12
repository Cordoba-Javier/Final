package repositorio;

import modelo.Piloto;
import java.util.List;


public interface RegistrarPilotoRepositorio {
    boolean existeNombre(String nombre);

    boolean existeAbreviasion(String abreviasion);

    boolean guardarPiloto(Piloto miPiloto);

    List<Piloto> getPilotos();
}
