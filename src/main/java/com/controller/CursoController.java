package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.Curso;
import com.service.CursoService;

@CrossOrigin("*")
@RestController
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscaAllCursos() {
		return cursoService.buscaAllCursos();
	}
	
	@GetMapping(value="curso/{codCurso}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscaCursoPorCodigo(@PathVariable("codCurso") String codCurso) {
		return cursoService.buscaCursoPorCodigo(codCurso);
	}
	
	@GetMapping(value = "cursos/{precioMin}/{precioMax}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscaCursosPorRangoPrecios(@PathVariable("precioMin") double precioMin,
			@PathVariable("precioMax") double precioMax) {
		return cursoService.buscaCursosPorRangoPrecios(precioMin, precioMax);
	}	
	
	@PostMapping(value="curso",consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> crea(@RequestBody Curso curso) {
		cursoService.creaCurso(curso);
		return cursoService.buscaAllCursos();
	}
	
	@DeleteMapping(value="curso/{codCurso}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> elimina(@PathVariable("codCurso") String codCurso){
		cursoService.eliminaCurso(codCurso);
		return cursoService.buscaAllCursos();
	}
	
	@PutMapping(value="curso/{codCurso}/{duracion}")
	public void modificaDuracion(@PathVariable("codCurso") String codCurso,
			@PathVariable("duracion") int duracion) {
		cursoService.modificaDuracion(codCurso, duracion);
	}

}
