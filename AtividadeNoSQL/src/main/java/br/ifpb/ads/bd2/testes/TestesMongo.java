package br.ifpb.ads.bd2.testes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.ifpb.ads.bd2.daoMongo.AlunoDaoMongo;
import br.ifpb.ads.bd2.daoMongo.PessoaDaoMongo;
import br.ifpb.ads.bd2.daoMongo.ProfessorDaoMongo;
import br.ifpb.ads.bd2.modelos.Aluno;
import br.ifpb.ads.bd2.modelos.ContatoTelefone;
import br.ifpb.ads.bd2.modelos.Curso;
import br.ifpb.ads.bd2.modelos.Disciplina;
import br.ifpb.ads.bd2.modelos.Endereco;
import br.ifpb.ads.bd2.modelos.Pessoa;
import br.ifpb.ads.bd2.modelos.Professor;
import br.ifpb.ads.bd2.modelos.Tipo;
import br.ifpb.ads.bd2.modelos.Turma;

public class TestesMongo {

	public static void main(String[] args) {
		//Execute um de cada vez
		salvar();
		//atualizar();
		//deletar();

	}

	private static void salvar() {
		System.out.println("Criando contato telefone 1");
		ContatoTelefone ph1 = new ContatoTelefone();
		ph1.setNumero("083-99945.0664");

		System.out.println("Criando Aluno 1");
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Flavio Diniz");
		aluno1.setIdPessoa(1);
		aluno1.setEmail("flvdiniz91@gmail.com");
		aluno1.setMatricula(123);

		ph1.setPessoa(aluno1);
		ArrayList<ContatoTelefone> telefones = new ArrayList<ContatoTelefone>();
		telefones.add(ph1);
		aluno1.setTelefones(telefones);

		System.out.println("Criando contato telefone 2");
		ContatoTelefone ph2 = new ContatoTelefone();
		ph1.setNumero("083-99941.0359");

		System.out.println("Criando Aluno 2");
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Flavianny Diniz");
		aluno2.setIdPessoa(2);
		aluno2.setEmail("annydiniz96@gmail.com");
		aluno2.setMatricula(456);

		ph2.setPessoa(aluno2);
		ArrayList<ContatoTelefone> telefones2 = new ArrayList<ContatoTelefone>();
		telefones.add(ph2);
		aluno2.setTelefones(telefones2);

		System.out.println("Criando endereco 1");
		Endereco endereco = new Endereco();
		endereco.setRua("Manuel Noberto");
		endereco.setBairro("Zezinho Tranquilino");
		endereco.setCidade("Serra Branca");
		endereco.setCep("58580-000");
		endereco.setEstado("Paraiba");

		aluno1.setEndereco(endereco);
		aluno2.setEndereco(endereco);

		System.out.println("Criando turma");
		Turma turma1 = new Turma();
		turma1.setCodigo_turma(7777);

		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(aluno1);
		alunos.add(aluno2);
		turma1.setAlunos(alunos);

		System.out.println("Criando Disciplina");
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("Banco de Dados 2");
		disciplina.setCodDisciplina(48000);
		disciplina.setEmenta("JDBC, JPA, NOSQL");

		System.out.println("Criando Turma");
		Curso curso = new Curso();
		curso.setCod_curso(1);
		curso.setNome("ADS");
		curso.setTipo(Tipo.NOITE);

		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		disciplinas.add(disciplina);

		curso.setDisciplinas(disciplinas);
		disciplina.setCurso(curso);
		turma1.setDisciplina(disciplina);

		System.out.println("Criando Professor");
		Professor professor = new Professor();
		professor.setIdPessoa(3);
		professor.setNome("Anderson");
		professor.setTitulo("Mestre");

		ArrayList<Turma> turmas = new ArrayList<Turma>();
		turmas.add(turma1);
		professor.setTurmas(turmas);

		System.out.println("Criando Salvando Dados");
		new PessoaDaoMongo().salvar(aluno1);
		new PessoaDaoMongo().salvar(aluno2);
		new PessoaDaoMongo().salvar(professor);
		new AlunoDaoMongo().salvar(aluno1);
		new AlunoDaoMongo().salvar(aluno2);
		new ProfessorDaoMongo().salvar(professor);
		}

	@SuppressWarnings("unchecked")
	private static void atualizar() {
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("nomePessoa", "Flavianny Diniz");
		Pessoa query = new PessoaDaoMongo().findPessoa(map);

		Pessoa pessoaNova = new Pessoa();
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Anny Diniz");
		aluno2.setIdPessoa(2);
		aluno2.setEmail("annydiniz96@gmail.com");
		aluno2.setMatricula(456);
		new PessoaDaoMongo().atualizar(query, pessoaNova);

		Pessoa novaPessoa = new PessoaDaoMongo().findPessoa(map);
		System.out.printf("antiga:> " + query + "\nova:> " + novaPessoa.toString());
	}

	private static void deletar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(2);
		new PessoaDaoMongo().deletar(pessoa);
	}

}
