package repositorio;

import modelo.Piloto;

import java.util.List;
import java.util.Optional;

public interface RegistrarPilotoRepositorio {
    boolean existeNombre(String nombre);

    boolean existeAbreviasion(String abreviasion);

    boolean guardarPiloto(Piloto miPiloto);

    Optional<?> getPiloto(String nombre);

    List<Piloto> getPilotos();
}
