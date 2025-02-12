package servico;

import ar.edu.undec.pilotos.servicio.PilotoServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import input.EntradaPiloto;
import modelo.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PilotoServicioTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private EntradaPiloto entradaPiloto;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PilotoServicio pilotoServicio;


    @Test
    public void testPilotoServicioSincronizar()  {
        try {
            String jsonResp ="""
                [
                    {"session_key": 1, "first_name": "Max", "last_name": "Verstappen", "full_name": "Max Verstappen", 
                     "name_acronym": "VER", "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_Max_Verstappen/maxver01.png.transform/1col/image.png"},
                    {"session_key": 2, "first_name": "Logan", "last_name": "Sargeant", "full_name": "Logan SARGEANT", 
                     "name_acronym": "SAR", "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/L/LOGSAR01_Logan_Sargeant/logsar01.png.transform/1col/image.png"}
                ]
                """;

            List<Map<String, Object>> pilotosJsonList =new ObjectMapper().readValue(jsonResp, new TypeReference<>() {});

            when(restTemplate.getForObject(any(String.class), eq(String.class))).thenReturn(jsonResp);
            when(objectMapper.readValue(any(String.class), ArgumentMatchers.<TypeReference<List<Map<String, Object>>>>any())).thenReturn(pilotosJsonList);
            when(entradaPiloto.cargarPiloto(any(Piloto.class))).thenReturn(true);

            boolean resultado = pilotoServicio.sincronizarPilotos();

            Assertions.assertTrue(resultado);
        }catch (JsonProcessingException e) {
            fail("esta linea no deberia correr");
        }
    }

    @Test
    public void testPilotoServicioSincronizarNoHayNuevosPilotos()  {
        try {
            String jsonResp ="""
                [
                    {"session_key": 1, "first_name": "Max", "last_name": "Verstappen", "full_name": "Max Verstappen", 
                     "name_acronym": "VER", "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_Max_Verstappen/maxver01.png.transform/1col/image.png"},
                    {"session_key": 2, "first_name": "Logan", "last_name": "Sargeant", "full_name": "Logan SARGEANT", 
                     "name_acronym": "SAR", "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/L/LOGSAR01_Logan_Sargeant/logsar01.png.transform/1col/image.png"}
                ]
                """;

            List<Map<String, Object>> pilotosJsonList =new ObjectMapper().readValue(jsonResp, new TypeReference<>() {});

            when(restTemplate.getForObject(any(String.class), eq(String.class))).thenReturn(jsonResp);
            when(objectMapper.readValue(any(String.class), ArgumentMatchers.<TypeReference<List<Map<String, Object>>>>any())).thenReturn(pilotosJsonList);
            when(entradaPiloto.cargarPiloto(any(Piloto.class))).thenReturn(false);

            boolean resultado = pilotoServicio.sincronizarPilotos();

            Assertions.assertFalse(resultado);
        }catch (JsonProcessingException e) {
            fail("esta linea no deberia correr");
        }
    }

    @Test
    public void testPilotoServicioSincronizarFalloEnDeserealizacion() {
        String jsonResp = """
                [
                    {"session_": 1, "ft_name": "Max", "last_name": "Verstappen", "full_name": "Max Verstappen", 
                     "name_acronym": "ER", "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_Max_Verstappen/maxver01.png.transform/1col/image.png"
                    {"session_key": 2, "first_name": "Logan", "last_name": "Sargeant", "full_name": "Logan SARGEANT", 
                     "name_acronym": "SAR", "headshot_url": "https://www.formula1.com/content/dam/fom-website/drivers/L/LOGSAR01_Logan_Sargeant/logsar01.png.transform/1col/image.png",
                    
                ]
                """;
        Assertions.assertThrows(JsonProcessingException.class, () -> new ObjectMapper().readValue(jsonResp, new TypeReference<List<Map<String, Object>>>() {}));

    }
}
