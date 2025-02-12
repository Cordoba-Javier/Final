package casoDeUso;

import input.EntradaPiloto;
import modelo.Piloto;
import repositorio.RegistrarPilotoRepositorio;
import java.util.List;

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
    public List<Piloto> listapiloto() {
        return registrarPilotoRepositorio.getPilotos();
    }
}
