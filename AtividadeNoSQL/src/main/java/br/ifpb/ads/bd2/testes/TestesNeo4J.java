package br.ifpb.ads.bd2.testes;

import br.ifpb.ads.bd2.daoNeo4J.AlunoDaoNeo;
import br.ifpb.ads.bd2.daoNeo4J.ContatoDaoNeo;
import br.ifpb.ads.bd2.daoNeo4J.CursoDaoNeo;
import br.ifpb.ads.bd2.daoNeo4J.DaoRelacionar;
import br.ifpb.ads.bd2.daoNeo4J.DisciplinaDaoNeo;
import br.ifpb.ads.bd2.daoNeo4J.EnderecoDaoNeo;
import br.ifpb.ads.bd2.daoNeo4J.ProfessorDaoNeo;
import br.ifpb.ads.bd2.daoNeo4J.TurmaDaoNeo;
import br.ifpb.ads.bd2.modelos.Aluno;
import br.ifpb.ads.bd2.modelos.ContatoTelefone;
import br.ifpb.ads.bd2.modelos.Curso;
import br.ifpb.ads.bd2.modelos.Disciplina;
import br.ifpb.ads.bd2.modelos.Endereco;
import br.ifpb.ads.bd2.modelos.Professor;
import br.ifpb.ads.bd2.modelos.Turma;

public class TestesNeo4J {

	public static void main(String[] args) {
		salvar();
		//atualizar();
		//deletar();

	}

	@SuppressWarnings("unchecked")
	private static void salvar() {
		System.out.println("Criando contato telefone 1");
		ContatoTelefone ph1 = new ContatoTelefone();
		ph1.setNumero("083-99945.0664");
		ContatoDaoNeo contatoDao = new ContatoDaoNeo();
		System.out.println("Salvando!");
		contatoDao.salvar(ph1);

		System.out.println("Criando Aluno 1");
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Flavio Diniz");
		aluno1.setIdPessoa(1);
		aluno1.setEmail("flvdiniz91@gmail.com");
		aluno1.setMatricula(123);
		System.out.println("Salvando!");
		AlunoDaoNeo alunoDao = new AlunoDaoNeo();
		alunoDao.salvar(aluno1);

//		System.out.println("Criando 1º relação");
//		@SuppressWarnings("rawtypes")
//		DaoRelacionar relacionar = new DaoRelacionar();
//		relacionar.criarRelacionamento(aluno1, ph1, "tem");

		System.out.println("Criando contato telefone 2");
		ContatoTelefone ph2 = new ContatoTelefone();
		ph1.setNumero("083-99941.0359");
		System.out.println("Salvando!");
		contatoDao.salvar(ph2);

		System.out.println("Criando Aluno 2");
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Flavianny Diniz");
		aluno2.setIdPessoa(2);
		aluno2.setEmail("annydiniz96@gmail.com");
		aluno2.setMatricula(456);
		System.out.println("Salvando!");
		alunoDao.salvar(aluno2);

//		System.out.println("Criando 2º relação");
//		relacionar.criarRelacionamento(aluno2, ph2, "tem");

		System.out.println("Criando endereco 1");
		Endereco endereco = new Endereco();
		endereco.setRua("Manuel Noberto");
		endereco.setCidade("Serra Branca");
		endereco.setEstado("Paraiba");
		System.out.println("Salvando!");
		EnderecoDaoNeo enderecoDao = new EnderecoDaoNeo();
		enderecoDao.salvar(endereco);

//		System.out.println("Criando 3º relação");
//		relacionar.criarRelacionamento(aluno1, endereco, "mora");
//		relacionar.criarRelacionamento(aluno2, endereco, "mora");

		System.out.println("Criando turma");
		Turma turma1 = new Turma();
		turma1.setCodigo_turma(7777);
		turma1.setNome("BD2");
		System.out.println("Salvando!");
		TurmaDaoNeo turmaDao = new TurmaDaoNeo();
		turmaDao.salvar(turma1);

//		System.out.println("Criando 4º relação");
//		relacionar.criarRelacionamento(aluno1, turma1, "mora");
//		relacionar.criarRelacionamento(aluno2, turma1, "mora");

		System.out.println("Criando Disciplina");
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("Banco de Dados 2");
		disciplina.setCodDisciplina(48000);
		System.out.println("Salvando!");
		DisciplinaDaoNeo disciplinaDao = new DisciplinaDaoNeo();
		disciplinaDao.salvar(disciplina);

		System.out.println("Criando Curso");
		Curso curso = new Curso();
		curso.setCod_curso(1);
		curso.setNome("ADS");
		System.out.println("Salvando!");
		CursoDaoNeo cursoDao = new CursoDaoNeo();
		cursoDao.salvar(curso);

//		System.out.println("Criando 5º relação");
//		relacionar.criarRelacionamento(curso, disciplina, "possui");
//		relacionar.criarRelacionamento(turma1, disciplina, "estuda");

		System.out.println("Criando Professor");
		Professor professor = new Professor();
		professor.setIdPessoa(3);
		professor.setNome("Anderson");
		professor.setTitulo("Mestre");
		professor.setEmail("Anderson@ifpb.edu.br");
		System.out.println("Salvando!");
		ProfessorDaoNeo profDao = new ProfessorDaoNeo();
		profDao.salvar(professor);

//		System.out.println("Criando 6º relação");
//		relacionar.criarRelacionamento(professor, disciplina, "ensina");
//		relacionar.criarRelacionamento(turma1, professor, "tem aula");

	}

	private static void atualizar() {
		AlunoDaoNeo alunoDao = new AlunoDaoNeo();
		Aluno aluno = new Aluno();
		aluno.setNome("Flavianny Diniz");

		Aluno aluno2 = new Aluno();
		aluno2.setNome("Anny Diniz");
		alunoDao.atualizar(aluno, aluno2);

	}

	private static void deletar() {
		Aluno aluno = new Aluno();
		aluno.setNome("Anny Diniz");
		AlunoDaoNeo alunoDao = new AlunoDaoNeo();
		alunoDao.deletar(aluno);
	}

}
