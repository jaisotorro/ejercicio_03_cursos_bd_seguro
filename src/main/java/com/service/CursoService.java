package com.service;

import java.util.List;

import com.model.Curso;

public interface CursoService {
	List<Curso> buscaAllCursos();
	Curso buscaCursoPorCodigo(String codCurso);
	List<Curso> buscaCursosPorRangoPrecios(double precioMin, double precioMax);
	List<Curso> creaCurso(Curso curso);
	List<Curso> eliminaCurso(String codCurso);
	void modificaDuracion(String codCurso, int duracion);
	
	
}
