package repositorio;

import modelo.Piloto;

public interface RegistrarPilotoRepositorio {
    boolean existeNombre(String nombre);

    boolean existeAbreviasion(String abreviasion);

    boolean guardarPiloto(Piloto miPiloto);
}
