package com.soares.hudson.springcrude.services;

import org.springframework.stereotype.Service;

import com.soares.hudson.springcrude.models.Aulas;
import com.soares.hudson.springcrude.repository.AulasRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

@Service
public class AulasService {
    
    private AulasRepository repository;
    

    public AulasService( AulasRepository repo) {
        this.repository = repo;
    }

    public void deletarAula( @NotNull Long id ) throws EntityNotFoundException {
    
        Aulas aula = this.repository.getReferenceById(id);
        
        if( aula.getNome() == null ) {
            throw new EntityNotFoundException("");
        }
        this.repository.delete(aula); 
       
    }
}
