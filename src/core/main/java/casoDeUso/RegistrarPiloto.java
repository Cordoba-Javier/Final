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
        try{
            if (!registrarPilotoRepositorio.existeNombre(piloto.getNombre()) || !registrarPilotoRepositorio.existeAbreviasion(piloto.getAbreviasion()))
                registrarPilotoRepositorio.guardarPiloto(piloto);
            return true;
        }catch (Exception ex){
            throw new ExcepcionPiloto("Nombre o Abreviason ya exitiste ");
        }
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
