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

	Neo4jConnection connection;

	@SuppressWarnings("static-access")
	public void salvar(Turma turma) {
		try (Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("CREATE (a:Turma {nomeTurma: '" + turma.getNome() + "', codTurma: '" + turma.getCodigo_turma()
						+ "'})");
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

	public void atualizar(Turma oldTurma, Turma turmaNova) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Turma {nomeTurma: '" + oldTurma.getNome() + "'}) SET a.nomeTurma = '"
						+ turmaNova.getNome() + "'");
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

	public void deletar(Turma turma) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Turma {nomeTurma: '" + turma.getNome() + "'}) DETACH DELETE a");
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

	public DBObject findOne(Turma turma) {
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
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
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
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
