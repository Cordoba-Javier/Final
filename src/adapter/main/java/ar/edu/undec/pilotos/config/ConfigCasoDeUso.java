package ar.edu.undec.pilotos.config;

import casoDeUso.RegistrarPiloto;
import input.EntradaPiloto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositorio.RegistrarPilotoRepositorio;

@Configuration
public class ConfigCasoDeUso {
    @Bean
    public EntradaPiloto entradaPiloto(RegistrarPilotoRepositorio repositorio) {
        return new RegistrarPiloto(repositorio);
    }
}
