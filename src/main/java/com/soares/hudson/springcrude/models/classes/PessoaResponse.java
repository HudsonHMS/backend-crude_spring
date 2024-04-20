package com.soares.hudson.springcrude.models.classes;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {
  private Boolean sucesso;
  private Integer status;
  private List<Pessoa> data;
}
