package com.soares.hudson.springcrude.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.soares.hudson.springcrude.models.classes.PessoaResponse;

import io.micrometer.common.lang.Nullable;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    @GetMapping("/ola")
    public String hello () {
        return "Olá usuário";
    }

    @GetMapping("/pessoas")
    public PessoaResponse getPessoas( @RequestParam @Nullable String nome ) {
        try {

            String url;
            if( null == nome ) {
               url = "http://gisi-backend.desenvolvimento.prodemge.gov.br/api/extranet/primeiro-acesso/pessoas.json";
            }else {
                url = "http://gisi-backend.desenvolvimento.prodemge.gov.br/api/extranet/primeiro-acesso/pessoas.json?nome=" + nome;
            }
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, PessoaResponse.class);
        } catch ( RestClientException ex ) {
            return null;
        }
    }

}
