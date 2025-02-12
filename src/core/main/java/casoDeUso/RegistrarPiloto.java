package casoDeUso;

import excepciones.ExcepcionPiloto;
import input.EntradaPiloto;
import modelo.Piloto;
import org.springframework.http.HttpStatus;
import repositorio.RegistrarPilotoRepositorio;

import java.util.List;
import java.util.Optional;

public class RegistrarPiloto implements EntradaPiloto {
    private RegistrarPilotoRepositorio registrarPilotoRepositorio;

    public RegistrarPiloto(RegistrarPilotoRepositorio registrarPilotoRepositorio) {
        this.registrarPilotoRepositorio = registrarPilotoRepositorio;
    }

    @Override
    public boolean cargarPiloto(Piloto piloto){
        if (!registrarPilotoRepositorio.existeNombre(piloto.getNombre()) && !registrarPilotoRepositorio.existeAbreviasion(piloto.getAbreviasion())){
            registrarPilotoRepositorio.guardarPiloto(piloto);
            return true;
        }
        return  false;
    }

    @Override
    public Optional<?> obtenerPiloto(String nombre) throws ExcepcionPiloto {
        if (!registrarPilotoRepositorio.existeNombre(nombre))
            throw new ExcepcionPiloto("", HttpStatus.BAD_REQUEST,"Piloto no existe");

        return registrarPilotoRepositorio.getPiloto(nombre);
    }

    @Override
    public List<Piloto> listapiloto() {
        return registrarPilotoRepositorio.getPilotos();
    }
}
