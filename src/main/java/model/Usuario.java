package model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    private Integer id;
    private String nome;
    private String senha;
}
