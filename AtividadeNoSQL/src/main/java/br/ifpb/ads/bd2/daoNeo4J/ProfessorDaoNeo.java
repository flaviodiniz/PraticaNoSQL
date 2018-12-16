package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Professor;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class ProfessorDaoNeo implements DaoNeo4j<Professor> {

	private Session session = null;

	public ProfessorDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void salvar(Professor professor) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (a:Professor {nomePessoa: '" + professor.getNome() + "', idPessoa: "
					+ professor.getIdPessoa() + ", email: '" + professor.getEmail() + "', titulo: '"
					+ professor.getTitulo() + "'})");
			tx.success();
			session.close();
		}
	}

	public void atualizar(Professor oldProfessor, Professor professorNovo) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Professor {nomePessoa: '" + oldProfessor.getNome() + "'}) SET a.nomePessoa = '"
					+ professorNovo.getNome() + "'");
			tx.success();
			session.close();
		}
	}

	public void deletar(Professor professor) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Professor {nomePessoa: '" + professor.getNome() + "'}) DETACH DELETE a");
			tx.success();
			session.close();
		}
	}

	public DBObject findOne(Professor Professor) {
		StatementResult resultado = session
				.run("MATCH (n:Professor) WHERE n.nomePessoa = '" + Professor.getNome() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Professor professorR = new Professor();
			Professor.setNome(nodeAtual.get("nomePessoa").toString());
			return (DBObject) professorR;
		}
		session.close();
		return null;
	}

	public List<Professor> buscarTodos() {
		ArrayList<Professor> allProfessor = new ArrayList<Professor>();
		StatementResult resultado = session.run("MATCH (n:Professor) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Professor prof = new Professor();
			prof.setNome(nodeAtual.get("nomePessoa").toString());
			allProfessor.add(prof);
		}
		session.close();
		return allProfessor;
	}

}
