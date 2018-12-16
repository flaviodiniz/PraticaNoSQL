package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Turma;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class TurmaDaoNeo implements DaoNeo4j<Turma> {

	private Session session = null;

	public TurmaDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void salvar(Turma turma) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (a:Turma {nomeTurma: '" + turma.getNome() + "', codTurma: " + turma.getCodigo_turma()
					+ "})");
			tx.success();
			session.close();
		}
	}

	public void atualizar(Turma oldTurma, Turma turmaNova) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Turma {nomeTurma: '" + oldTurma.getNome() + "'}) SET a.nomeTurma = '" + turmaNova.getNome()
					+ "'");
			tx.success();
			session.close();
		}
	}

	public void deletar(Turma turma) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Turma {nomeTurma: '" + turma.getNome() + "'}) DETACH DELETE a");
			tx.success();
			session.close();
		}
	}

	public DBObject findOne(Turma turma) {
		StatementResult resultado = session
				.run("MATCH (n:Turma) WHERE n.nomeTurma = '" + turma.getNome() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Turma turmaR = new Turma();
			turma.setNome(nodeAtual.get("nomePessoa").toString());
			return (DBObject) turmaR;
		}
		session.close();
		return null;
	}

	public List<Turma> buscarTodos() {
		ArrayList<Turma> allTurma = new ArrayList<Turma>();
		StatementResult resultado = session.run("MATCH (n:Turma) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Turma turma = new Turma();
			turma.setNome(nodeAtual.get("nomeTurma").toString());
			allTurma.add(turma);
		}
		session.close();
		return allTurma;
	}

}
