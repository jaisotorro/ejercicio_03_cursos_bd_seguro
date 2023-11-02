package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Curso;
import com.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {
	
	@Autowired
	CursoRepository cursoRepository;

	@Override
	public List<Curso> buscaAllCursos() {
		return cursoRepository.findAll();
	}

	@Override
	public Curso buscaCursoPorCodigo(String codCurso) {
		return cursoRepository.findById(codCurso).orElse(null);
	}

	@Override
	public List<Curso> buscaCursosPorRangoPrecios(double precioMin, double precioMax) {
		return cursoRepository.getByRangoPrecios(precioMin, precioMax);
	}

	@Override
	public List<Curso> creaCurso(Curso curso) {
		cursoRepository.save(curso);
		return cursoRepository.findAll();
	}

	@Override
	public List<Curso> eliminaCurso(String codCurso) {
		cursoRepository.deleteById(codCurso);
		return cursoRepository.findAll();
	}

	@Override
	public void modificaDuracion(String codCurso, int duracion) {
		cursoRepository.updateDuracion(codCurso, duracion);

	}

}
