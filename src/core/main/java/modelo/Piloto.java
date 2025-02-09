package modelo;

import excepciones.ExcepcionPiloto;

public class Piloto {
    private  Long id;
    private  String nombre;
    private  String apellido;
    private  String nombreCompleto;
    private  String abreviasion;
    private  String url;

    private Piloto(Long id, String nombre, String apellido, String nombreCompleto, String abreviasion, String url) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.abreviasion = abreviasion;
        this.url = url;
    }

    public static Piloto Instancia(Long id, String nombre, String apellido, String nombreCompleto, String abreviasion, String url) throws ExcepcionPiloto {
        if(nombre==null && nombre.isEmpty())
            throw new ExcepcionPiloto("Nombre Nulo");

        if (apellido==null && apellido.isEmpty())
            throw new ExcepcionPiloto("Apellido Nulo");

        if(nombreCompleto==null && nombreCompleto.isEmpty())
            throw new ExcepcionPiloto("Nombre Completo Nulo");

        if(abreviasion==null && abreviasion.isEmpty())
            throw new ExcepcionPiloto("Abreviasion Nulo");

        if(url==null && url.isEmpty())
            throw new ExcepcionPiloto("URL Nulo");

        return new Piloto(id, nombre, apellido, nombreCompleto, abreviasion, url);
    }

    public Long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getAbreviasion() {
        return abreviasion;
    }

    public void setAbreviasion(String abreviasion) {
        this.abreviasion = abreviasion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
