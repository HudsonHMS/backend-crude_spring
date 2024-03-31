package com.soares.hudson.springcrude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soares.hudson.springcrude.models.Cursos;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long> {
    @Query(value = "Select id, nome, categoria, valor from cursos where id=?1", nativeQuery = true)
    public Cursos getCursoPorId(Long id);
}
