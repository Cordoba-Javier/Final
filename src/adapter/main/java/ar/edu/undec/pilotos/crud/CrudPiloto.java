package ar.edu.undec.pilotos.crud;

import ar.edu.undec.pilotos.entidad.PilotoData;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface CrudPiloto extends CrudRepository<PilotoData, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByAbreviasion(String abreviasion);
}
