package com.soares.hudson.springcrude.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
      private String nome;
      private String cargo;
      private String email;
      private String telefone;
      private String telefone_celular;
      private String diretoria_nome;
      private String gerencia_nome;
      private String departamento_nome;
      private String coordenacao_nome;
      private String aniversario;
}
