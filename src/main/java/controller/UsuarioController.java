package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UsuarioDTOInput;
import service.UsuarioService;
import static spark.Spark.*;

public class UsuarioController {
    private UsuarioService usuarioService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void respostasRequisicoes() {
        get("/usuarios", (req, res) -> {
            res.type("application/json");
            return objectMapper.writeValueAsString(usuarioService.listar());
        });

        get("/usuarios/:id", (req, res) -> {
            res.type("application/json");
            Integer id = Integer.parseInt(req.params(":id"));
            return objectMapper.writeValueAsString(usuarioService.buscar(id));
        });

        post("/usuarios", (req, res) -> {
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
            usuarioService.inserir(usuarioDTOInput);
            res.status(201);
            res.type("application/json");
            return "";
        });

        put("/usuarios", (req, res) -> {
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
            usuarioService.alterar(usuarioDTOInput);
            res.type("application/json");
            return "";
        });

        delete("/usuarios/:id", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            usuarioService.excluir(id);
            res.type("application/json");
            return "";
        });
    }
}
