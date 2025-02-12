package ar.edu.undec.pilotos.entidad;

import jakarta.persistence.*;
import modelo.Piloto;

@Entity(name="pilotos")
@SequenceGenerator(name = "pilotos_id_seq", initialValue =1,sequenceName ="pilotos_id_seq", allocationSize = 1 )
public class PilotoData {
    @Id
    @Column(name="idpiloto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pilotos_id_seq")
    private  Integer idpiloto;
    @Column(name="nombre")
    private  String nombre;
    @Column(name="apellido")
    private  String apellido;
    @Column(name="nombrecompleto")
    private  String nombreCompleto;
    @Column(name="abreviasion")
    private  String abreviasion;
    @Column(name="url")
    private  String url;

    public PilotoData() {}

    public PilotoData(Integer idpiloto, String nombre, String apellido, String nombreCompleto, String abreviasion, String url) {
        this.idpiloto = idpiloto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombreCompleto;
        this.abreviasion = abreviasion;
        this.url = url;
    }

    public static PilotoData instancia(Piloto piloto) {
        return new PilotoData(piloto.getId(), piloto.getNombre(), piloto.getApellido(), piloto.getNombreCompleto(), piloto.getAbreviasion(), piloto.getUrl());
    }

    public Integer getIdpiloto() {
        return idpiloto;
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
