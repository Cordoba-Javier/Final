package ar.edu.undec.pilotos.servicio;


import ar.edu.undec.pilotos.crudRepositorio.CrudRepositorio;
import ar.edu.undec.pilotos.entidad.PilotoDTO;
import ar.edu.undec.pilotos.mapeo.Mapeo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import excepciones.ExcepcionPiloto;
import input.EntradaPiloto;
import modelo.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PilotoServicio {
    private RestTemplate restTemplate;
    private  EntradaPiloto entradaPiloto;
    private ObjectMapper objectMapper;

    @Autowired
    public PilotoServicio( EntradaPiloto entradaPiloto,RestTemplate restTemplate,ObjectMapper objectMapper) {
        this.entradaPiloto = entradaPiloto;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void sincronizarPilotos() throws ExcepcionPiloto, JsonProcessingException {
        String apiUrl = "https://api.openf1.org/v1/drivers"; // Cambia por la URL de la API externa
        String response = restTemplate.getForObject(apiUrl, String.class);
        // Deserializamos el JSON en una lista de objetos
        List<PilotoDTO> pilotos = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        // Convertimos el JSON en un array de mapas
        List<Map<String, Object>> pilotosJsonList = objectMapper.readValue(response, new TypeReference<List<Map<String, Object>>>() {});


        Random random = new Random();
        for (Map<String, Object> pilotoObj : pilotosJsonList) {
            // Convertimos cada objeto del JSON a PilotoDTO
            PilotoDTO piloto = new PilotoDTO(random.nextLong(),
                    (String)pilotoObj.get("first_name"),
                    (String)pilotoObj.get("last_name"),
                    (String)pilotoObj.get("full_name"),
                    (String)pilotoObj.get("name_acronym"),
                    (String)pilotoObj.get("headshot_url")
            );
            pilotos.add(piloto);
        }


        List<PilotoDTO> pilotosExternos = pilotos.
                stream().
                filter(p->p.getNombre()!=null && p.getApellido()!=null&&p.getAbreviasion()!=null &&p.getUrl()!=null&&p.getNombreCompleto()!=null).
                collect(Collectors.toUnmodifiableList());

        // Guardar en la base de datos

        if(pilotosExternos.isEmpty()){
            throw new ExcepcionPiloto("No se extrajo pilotos");
        }else {
            for (PilotoDTO pilotoDTO : pilotosExternos) {
                try {
                    Piloto piloto = Mapeo.mapeoDTOCore(pilotoDTO);
                    // Mapear otros campos según sea necesario
                    entradaPiloto.cargarPiloto(piloto);
                } catch (Exception e) {
                    throw new ExcepcionPiloto(e.getMessage());
                }
            }
        }
    }
}


