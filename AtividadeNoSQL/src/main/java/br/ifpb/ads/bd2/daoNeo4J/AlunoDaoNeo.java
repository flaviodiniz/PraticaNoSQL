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

public class AlunoDaoNeo implements DaoNeo4j<Aluno> {

	private Session session = null;

	public AlunoDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void salvar(Aluno aluno) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (a:Aluno {nomePessoa: '" + aluno.getNome() + "', idPessoa: " + aluno.getIdPessoa()
					+ ", email: '" + aluno.getEmail() + "', matricula: " + aluno.getMatricula() + "})");
			tx.success();
			session.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizar(Aluno oldAluno, Aluno alunoNovo) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Aluno {nomePessoa: '" + oldAluno.getNome() + "'}) SET a.nomePessoa = '"
					+ alunoNovo.getNome() + "'");
			tx.success();
			session.close();
		}
	}

	public void deletar(Aluno aluno) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Aluno {nomePessoa: '" + aluno.getNome() + "'}) DETACH DELETE a");
			tx.success();
			session.close();
		}
	}

	public DBObject findOne(Aluno aluno) {
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
