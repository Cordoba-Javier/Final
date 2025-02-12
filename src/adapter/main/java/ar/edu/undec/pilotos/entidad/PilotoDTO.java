package ar.edu.undec.pilotos.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotoDTO {

    @JsonProperty("idpiloto")
    private  Integer idpiloto;
    @JsonProperty("nombre")
    private  String nombre;
    @JsonProperty("apellido")
    private  String apellido;
    @JsonProperty("nombrecompleto")
    private  String nombreCompleto;
    @JsonProperty("abreviasion")
    private  String abreviasion;
    @JsonProperty("url")
    private  String url;


    public PilotoDTO() {}


    public Integer getIdpiloto() {
        return idpiloto;
    }

    public void setIdpiloto(Integer idpiloto) {
        this.idpiloto = idpiloto;
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
