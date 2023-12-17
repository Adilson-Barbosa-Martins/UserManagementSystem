package service;

import model.Usuario;
import dto.UsuarioDTOInput;
import dto.UsuarioDTOOutput;
import org.modelmapper.ModelMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDTOOutput> listar() {
        return listaUsuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTOOutput.class))
                .collect(Collectors.toList());
    }

    public void inserir(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterar(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios = listaUsuarios.stream()
                .map(u -> u.getId().equals(usuario.getId()) ? usuario : u)
                .collect(Collectors.toList());
    }

    public UsuarioDTOOutput buscar(Integer id) {
        return listaUsuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTOOutput.class))
                .orElse(null);
    }

    public void excluir(Integer id) {
        listaUsuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    public String getUsuariosAsJson() {
        JSONArray jsonArray = new JSONArray();
        for (Usuario usuario : listaUsuarios) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", usuario.getId());
            jsonObject.put("nome", usuario.getNome());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }
}
