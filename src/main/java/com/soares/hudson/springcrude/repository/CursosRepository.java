package com.soares.hudson.springcrude.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soares.hudson.springcrude.enuns.StatusEnum;
import com.soares.hudson.springcrude.models.Cursos;
import com.soares.hudson.springcrude.models.RecordInterface;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long>, RecordInterface<Object> {
    @Query(value = "Select id, nome, categoria, valor, status from cursos where id=?1", nativeQuery = true)
    public Cursos getCursoPorId(Long id);

    public List<Cursos> findByStatusIn( StatusEnum[] status );
    public Page<Cursos> findByStatusIn( StatusEnum[] status, Pageable pageable );
}
