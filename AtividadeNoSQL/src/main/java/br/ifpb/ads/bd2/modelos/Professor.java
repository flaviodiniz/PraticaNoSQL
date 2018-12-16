package br.ifpb.ads.bd2.modelos;

import java.util.List;

public class Professor extends Pessoa {
	
	private String titulo;
	
	private List<Turma> turmas;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Professor() {

	}

}
