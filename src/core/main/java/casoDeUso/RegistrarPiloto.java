package casoDeUso;

import excepciones.ExcepcionPiloto;
import input.EntradaPiloto;
import modelo.Piloto;
import repositorio.RegistrarPilotoRepositorio;

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
}
