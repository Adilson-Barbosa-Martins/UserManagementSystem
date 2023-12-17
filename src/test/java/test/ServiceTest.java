package test;

import dto.UsuarioDTOInput;
import service.UsuarioService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testInsercaoUsuario() {
        UsuarioService service = new UsuarioService();
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Teste");
        usuarioDTOInput.setSenha("senha123");

        service.inserir(usuarioDTOInput);
        assertEquals(1, service.listar().size());
    }
}
