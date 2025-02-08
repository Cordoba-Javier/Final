package ar.edu.undec.pilotos.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PilotoDTO {

    @JsonProperty("idpiloto")
    private  Long idpiloto;
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

    public PilotoDTO(Long idpiloto, String nombre, String apellido, String nombreCompleto, String abreviasion, String url) {
        this.idpiloto = idpiloto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.abreviasion = abreviasion;
        this.url = url;
    }

    public PilotoDTO() {}


    public Long getIdpiloto() {
        return idpiloto;
    }

    public void setIdpiloto(Long idpiloto) {
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
