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
public class ConsumoApi {
    private RestTemplate restTemplate;
    private EntradaPiloto entradaPiloto;
    private ObjectMapper objectMapper;
    private long cont;

    @Autowired
    public ConsumoApi(EntradaPiloto entradaPiloto, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.entradaPiloto = entradaPiloto;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public boolean sincronizarPilotos() {

        try {
            String apiUrl = "https://api.openf1.org/v1/drivers";
            String respuesta = restTemplate.getForObject(apiUrl, String.class);
            List<PilotoDTO> pilotos = new ArrayList<>();
            List<Map<String, Object>> pilotosJsonList = objectMapper.readValue(respuesta, new TypeReference<>() {});

            for (Map<String, Object> pilotoObj : pilotosJsonList) {

                PilotoDTO piloto = new PilotoDTO();
                piloto.setIdpiloto((Integer) pilotoObj.get("session_key"));
                piloto.setNombre((String) pilotoObj.get("first_name"));
                piloto.setApellido((String) pilotoObj.get("last_name"));
                piloto.setNombreCompleto((String) pilotoObj.get("full_name"));
                piloto.setAbreviasion((String) pilotoObj.get("name_acronym"));
                piloto.setUrl((String) pilotoObj.get("headshot_url"));

                pilotos.add(piloto);
            }

            List<PilotoDTO> pilotosExternos = pilotos.
                    stream().
                    filter(p -> p.getNombre() != null && p.getApellido() != null && p.getAbreviasion() != null && p.getUrl() != null && p.getNombreCompleto() != null).
                    collect(Collectors.toCollection(ArrayList<PilotoDTO>::new));

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

            if (cont != 0)
                return true;
            return false;

        } catch (JsonProcessingException e) {

            throw new ExcepcionPiloto("400", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno al procesar los datos JSON");
        }
    }
}



