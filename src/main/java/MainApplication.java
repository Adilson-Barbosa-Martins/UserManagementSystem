import static spark.Spark.*;
import controller.UsuarioController;
import service.UsuarioService;
import dto.UsuarioDTOInput;

public class MainApplication {
    public static void main(String[] args) {
        port(8080);

        UsuarioService usuarioService = new UsuarioService();

        adicionarUsuariosTeste(usuarioService);

        UsuarioController usuarioController = new UsuarioController(usuarioService);
        usuarioController.respostasRequisicoes();

        get("/usuariosJson", (req, res) -> {
            String jsonUsuarios = usuarioService.getUsuariosAsJson();
            System.out.println(jsonUsuarios);
            res.type("application/json");
            return jsonUsuarios;
        });

        get("/ping", (req, res) -> "Pong!");
    }

    private static void adicionarUsuariosTeste(UsuarioService usuarioService) {
        UsuarioDTOInput usuario1 = new UsuarioDTOInput();
        usuario1.setId(1);
        usuario1.setNome("abm 1");
        usuario1.setSenha("senha123");
        usuarioService.inserir(usuario1);

        UsuarioDTOInput usuario2 = new UsuarioDTOInput();
        usuario2.setId(2);
        usuario2.setNome("Usuario Teste 2");
        usuario2.setSenha("senha456");
        usuarioService.inserir(usuario2);
    }
}
