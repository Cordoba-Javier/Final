package casoDeUso;

import excepciones.ExcepcionPiloto;
import input.EntradaPiloto;
import modelo.Piloto;
import repositorio.RegistrarPilotoRepositorio;

import java.util.List;
import java.util.Optional;

public class RegistrarPiloto implements EntradaPiloto {
    private final RegistrarPilotoRepositorio registrarPilotoRepositorio;

    public RegistrarPiloto(RegistrarPilotoRepositorio registrarPilotoRepositorio) {
        this.registrarPilotoRepositorio = registrarPilotoRepositorio;
    }

    @Override
    public boolean cargarPiloto(Piloto piloto) throws ExcepcionPiloto {

        if(registrarPilotoRepositorio.existeNombre(piloto.getNombre()))
            throw new ExcepcionPiloto("Nombre ya existe");

        if(registrarPilotoRepositorio.existeAbreviasion(piloto.getAbreviasion()))
            throw new ExcepcionPiloto("Abreviasion ya existe");

        registrarPilotoRepositorio.guardarPiloto(piloto);
        return true;
    }

    @Override
    public Optional<?> obtenerPiloto(String nombre) throws ExcepcionPiloto {
        if (!registrarPilotoRepositorio.existeNombre(nombre))
            throw new ExcepcionPiloto("Piloto no existe");

        return registrarPilotoRepositorio.getPiloto(nombre);
    }

    @Override
    public List<Piloto> listapiloto() throws ExcepcionPiloto {
        return registrarPilotoRepositorio.getPilotos();
    }
}
