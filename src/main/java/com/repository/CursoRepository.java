package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, String> {

	@Query("SELECT c FROM Curso c WHERE c.precio >= ?1 AND c.precio <= ?2" )
	List<Curso> getByRangoPrecios(double precioMin, double precioMax);
	
	@Transactional
	@Modifying
	@Query("UPDATE Curso c SET c.duracion = ?2 WHERE c.codCurso = ?1")
	void updateDuracion (String codCurso, double duracion);
	
	
}
