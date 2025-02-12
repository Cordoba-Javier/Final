package excepciones;

import org.springframework.http.HttpStatus;


public class ExcepcionPiloto extends RuntimeException {
    private String codigo;
    private HttpStatus estado;

    public ExcepcionPiloto(String codigo,HttpStatus estado,String message) {
        super(message);
        this.codigo = codigo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public HttpStatus getEstado() {
        return estado;
    }
}
