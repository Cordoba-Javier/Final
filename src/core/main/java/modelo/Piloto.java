package modelo;

import excepciones.ExcepcionPiloto;
import org.springframework.http.HttpStatus;

public class Piloto {
    private  Integer id;
    private  String nombre;
    private  String apellido;
    private  String nombreCompleto;
    private  String abreviasion;
    private  String url;

    private Piloto(Integer id, String nombre, String apellido, String nombreCompleto, String abreviasion, String url) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.abreviasion = abreviasion;
        this.url = url;
    }

    public static Piloto Instancia(Integer id, String nombre, String apellido, String nombreCompleto, String abreviasion, String url) {
        if (id==null)
            throw new ExcepcionPiloto("400",HttpStatus.BAD_REQUEST,"id nulo");

        if(nombre==null && nombre.isEmpty())
            throw new ExcepcionPiloto("400", HttpStatus.BAD_REQUEST,"Nombre Nulo");

        if (apellido==null && apellido.isEmpty())
            throw new ExcepcionPiloto("400", HttpStatus.BAD_REQUEST,"Apellido Nulo");

        if(nombreCompleto==null && nombreCompleto.isEmpty())
            throw new ExcepcionPiloto("400", HttpStatus.BAD_REQUEST,"Nombre Completo Nulo");

        if(abreviasion==null && abreviasion.isEmpty())
            throw new ExcepcionPiloto("400", HttpStatus.BAD_REQUEST,"Abreviasion Nulo");

        if(url==null && url.isEmpty())
            throw new ExcepcionPiloto("400", HttpStatus.BAD_REQUEST,"URL Nulo");
        return new Piloto(id, nombre, apellido, nombreCompleto, abreviasion, url);
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getAbreviasion() {
        return abreviasion;
    }

    public String getUrl() {
        return url;
    }

}
