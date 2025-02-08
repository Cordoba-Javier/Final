package ar.edu.undec.pilotos.crudRepositorio;

import ar.edu.undec.pilotos.crud.CrudPiloto;
import ar.edu.undec.pilotos.mapeo.Mapeo;
import modelo.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repositorio.RegistrarPilotoRepositorio;

import java.util.List;
import java.util.Optional;

@Repository
public class CrudRepositorio implements RegistrarPilotoRepositorio {

    CrudPiloto crudPiloto;

    @Autowired
    CrudRepositorio (CrudPiloto crudPiloto) {
        this.crudPiloto = crudPiloto;
    }

    @Override
    public boolean existeNombre(String nombre) {
        return crudPiloto.existsByNombre(nombre);
    }

    @Override
    public boolean existeAbreviasion(String abreviasion) {
        return crudPiloto.existsByAbreviasion(abreviasion);
    }

    @Override
    public boolean guardarPiloto(Piloto miPiloto) {
        try {
            return crudPiloto.save(Mapeo.mapeoCoreData(miPiloto)).getNombre()!=null;
        }catch (Exception e) {
            return false;
        }

    }

    @Override
    public Optional<?>  getPiloto(String nombre) {
        return crudPiloto.getByNombre(nombre);
    }

    @Override
    public List<Piloto> getPilotos() {
        return crudPiloto.getPilotoDataBy();
    }
}
