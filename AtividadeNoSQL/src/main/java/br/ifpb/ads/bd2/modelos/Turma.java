package br.ifpb.ads.bd2.modelos;

import java.util.List;

public class Turma {
	private String nome;

	private int codigo_turma;

	private Professor professor;

	private List<Aluno> alunos;

	private Disciplina disciplina;

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo_turma() {
		return codigo_turma;
	}

	public void setCodigo_turma(int codigo_turma) {
		this.codigo_turma = codigo_turma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Turma() {

	}

}
