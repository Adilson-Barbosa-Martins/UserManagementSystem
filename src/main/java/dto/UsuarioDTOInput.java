package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTOInput {
    private Integer id;
    private String nome;
    private String senha;
}



