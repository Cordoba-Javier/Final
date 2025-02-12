package crudrepositorio;

import ar.edu.undec.pilotos.crud.CrudPiloto;
import ar.edu.undec.pilotos.crudRepositorio.CrudRepositorio;
import ar.edu.undec.pilotos.entidad.PilotoData;
import ar.edu.undec.pilotos.mapeo.Mapeo;
import modelo.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Random;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrudRepositorioTest {
    @Mock
    private CrudPiloto crudPiloto;

    @InjectMocks
    private CrudRepositorio crudRepositorio;

    @Test
    public void crudRepositorioExisteNombreTest() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia(rand.nextInt(900), "nombre", "apellido", "nombrecompleto", "abreviacion", "url");
        when(crudPiloto.existsByNombre(piloto.getNombre())).thenReturn(true);
        boolean resultado = crudRepositorio.existeNombre(piloto.getNombre());
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crudRepositorioNoExisteNombreTest() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia( rand.nextInt(900), "nombre", "apellido", "nombrecompleto", "abreviacion", "url");
        when(crudPiloto.existsByNombre(piloto.getNombre())).thenReturn(false);
        boolean resultado = crudRepositorio.existeNombre(piloto.getNombre());
        Assertions.assertFalse(resultado);
    }

    @Test
    public void crudRepositorioExisteAbreviacionTest() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia(rand.nextInt(900), "nombre", "apellido", "nombrecompleto", "abreviacion", "url");
        when(crudPiloto.existsByAbreviasion(piloto.getAbreviasion())).thenReturn(true);
        boolean resultado = crudRepositorio.existeAbreviasion(piloto.getAbreviasion());
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crudRepositorioaNoExisteAbreviacionTest() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia(rand.nextInt(900), "nombre", "apellido", "nombrecompleto", "abreviacion", "url");
        when(crudPiloto.existsByAbreviasion(piloto.getAbreviasion())).thenReturn(false);
        boolean resultado = crudRepositorio.existeAbreviasion(piloto.getAbreviasion());
        Assertions.assertFalse(resultado);
    }

    @Test
    public void crudRepositorioaGuardarPilotoExitosoTest() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia(rand.nextInt(900), "nombre", "apellido", "nombrecompleto", "abreviacion", "url");
        when(crudPiloto.save(any(PilotoData.class))).thenReturn(Mapeo.mapeoCoreData(piloto));
        boolean resultado = crudRepositorio.guardarPiloto(piloto);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crudRepositorioaGuardarPilotoNoExitosoTest() {
        Random rand = new Random();
        Piloto piloto = Piloto.Instancia(rand.nextInt(900),"nombre","apellido","nombrecompleto","abreviacion","url" );
        when(crudPiloto.save(any(PilotoData.class))).thenReturn(new PilotoData());
        boolean resultado=crudRepositorio.guardarPiloto(piloto);
        Assertions.assertFalse(resultado);
    }
}