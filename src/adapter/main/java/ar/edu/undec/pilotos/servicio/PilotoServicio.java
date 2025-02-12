package ar.edu.undec.pilotos.servicio;

import ar.edu.undec.pilotos.entidad.PilotoDTO;
import ar.edu.undec.pilotos.mapeo.Mapeo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import excepciones.ExcepcionPiloto;
import input.EntradaPiloto;
import modelo.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PilotoServicio {
    private RestTemplate restTemplate;
    private EntradaPiloto entradaPiloto;
    private ObjectMapper objectMapper;
    private long cont;

    @Autowired
    public PilotoServicio(EntradaPiloto entradaPiloto, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.entradaPiloto = entradaPiloto;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public boolean sincronizarPilotos() {
        //Conexión con la Api
        String apiUrl = "https://api.openf1.org/v1/drivers";
        String response = restTemplate.getForObject(apiUrl, String.class);

        List<PilotoDTO> pilotos = new ArrayList<>();
        // Convertimos el JSON en un array de mapas
        try {
            //Deserializar un JSON en una lista <String,Objeto>
            List<Map<String, Object>> pilotosJsonList = objectMapper.readValue(response, new TypeReference<List<Map<String, Object>>>() {
            });
            for (Map<String, Object> pilotoObj : pilotosJsonList) {
                // Convertimos cada objeto del JSON a PilotoDTO
                PilotoDTO piloto = new PilotoDTO(((Integer) pilotoObj.get("session_key")),
                        (String) pilotoObj.get("first_name"),
                        (String) pilotoObj.get("last_name"),
                        (String) pilotoObj.get("full_name"),
                        (String) pilotoObj.get("name_acronym"),
                        (String) pilotoObj.get("headshot_url")
                );
                pilotos.add(piloto);
            }
            //Filtro la lista sacando todos aquellos que sus campos sean nulos
            List<PilotoDTO> pilotosExternos = pilotos.
                    stream().
                    filter(p -> p.getNombre() != null && p.getApellido() != null && p.getAbreviasion() != null && p.getUrl() != null && p.getNombreCompleto() != null).
                    collect(Collectors.toUnmodifiableList());

            // Guardar en la base de datos
            cont = 0;
            if (pilotosExternos.isEmpty()) {
                throw new ExcepcionPiloto("406", HttpStatus.NOT_ACCEPTABLE, "Lista de pilotos vacia para la carga");
            } else {
                for (PilotoDTO pilotoDTO : pilotosExternos) {
                    Piloto piloto = Mapeo.mapeoDTOCore(pilotoDTO);
                    if (entradaPiloto.cargarPiloto(piloto))
                        cont++;
                }
            }

            //Respuesta para ver si se agregaron nuevos pilotos
            if (cont != 0)
                return true;
            return false;

        } catch (JsonProcessingException e) {
            //Manejo de error por si la salio mal la deserializar
            throw new ExcepcionPiloto("400", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno al procesar los datos JSON");
        }
    }
}



