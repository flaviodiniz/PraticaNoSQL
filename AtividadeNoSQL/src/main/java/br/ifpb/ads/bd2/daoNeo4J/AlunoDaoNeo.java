package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Aluno;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class AlunoDaoNeo implements DaoNeo4j<Aluno>{
	
	Neo4jConnection connection;
	private PessoaDaoNeo pessoa;

	@SuppressWarnings("static-access")
	public void salvar(Aluno aluno) {
		try (Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("CREATE (a:Aluno {nomePessoa: '" + aluno.getNome() + "', idPessoa: '" + aluno.getIdPessoa()
						+ "', email: '" + aluno.getEmail() + ", matricula: '" + aluno.getMatricula() + "'})");
				pessoa = null;
				pessoa.salvar(aluno);
				tx.success();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void atualizar(Aluno oldAluno, Aluno alunoNovo) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Aluno {nomePessoa: '" + oldAluno.getNome() + "'}) SET a.nomePessoa = '"
						+ alunoNovo.getNome() + "'");
				try {
					pessoa.atualizar(oldAluno, alunoNovo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tx.success();
			}
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deletar(Aluno aluno) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Aluno {nomePessoa: '" + aluno.getNome() + "'}) DETACH DELETE a");
				try {
					pessoa.deletar(aluno);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tx.success();
			}
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public DBObject findOne(Aluno aluno) {
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
		StatementResult resultado = session
				.run("MATCH (n:Aluno) WHERE n.nomePessoa = '" + aluno.getNome() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Aluno alunoR = new Aluno();
			aluno.setNome(nodeAtual.get("nomePessoa").toString());
			return (DBObject) alunoR;
		}
		session.close();
		return null;
	}

	public List<Aluno> buscarTodos() {
		ArrayList<Aluno> allAluno = new ArrayList<Aluno>();
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
		StatementResult resultado = session.run("MATCH (n:Aluno) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Aluno aluno = new Aluno();
			aluno.setNome(nodeAtual.get("nomePessoa").toString());
			allAluno.add(aluno);
		}
		session.close();
		return allAluno;
	}

}
