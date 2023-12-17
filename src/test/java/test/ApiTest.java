package test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testListagemUsuarios() throws Exception {
        URL url = new URL("http://localhost:8080/usuarios");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        assertEquals(200, con.getResponseCode());
    }

    @Test
    public void testInsercaoUsuarioViaApi() throws Exception {
        URL url = new URL("http://localhost:8080/usuarios");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");

        Map<String, Object> usuario = Map.of("id", 1, "nome", "Teste", "senha", "senha123");
        String jsonInputString = objectMapper.writeValueAsString(usuario);

        try (var os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        assertEquals(201, con.getResponseCode());
    }
}
