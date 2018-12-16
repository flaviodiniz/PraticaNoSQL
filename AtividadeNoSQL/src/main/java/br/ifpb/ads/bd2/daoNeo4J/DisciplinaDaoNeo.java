package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Disciplina;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class DisciplinaDaoNeo implements DaoNeo4j<Disciplina> {

	Neo4jConnection connection;

	@SuppressWarnings("static-access")
	public void salvar(Disciplina disciplina) {
		try (Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("CREATE (a:Disciplina {nomeDisciplina: '" + disciplina.getNome() + "', codDisciplina: '"
						+ disciplina.getCodDisciplina() + "'})");
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

	public void atualizar(Disciplina oldDisciplina, Disciplina disciplinaNova) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Disciplina {nomeDisciplina: '" + oldDisciplina.getNome()
						+ "'}) SET a.nomeDisciplina = '" + disciplinaNova.getNome() + "'");
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

	public void deletar(Disciplina pessoa) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Pessoa {nomePessoa: '" + pessoa.getNome() + "'}) DETACH DELETE a");
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

	public DBObject findOne(Disciplina disciplina) {
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
		StatementResult resultado = session
				.run("MATCH (n:Disciplina) WHERE n.nomeDisciplina = '" + disciplina.getNome() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Disciplina disciplinaR = new Disciplina();
			disciplina.setNome(nodeAtual.get("nomePessoa").toString());
			return (DBObject) disciplinaR;
		}
		session.close();
		return null;
	}

	public List<Disciplina> buscarTodos() {
		ArrayList<Disciplina> allDisciplina = new ArrayList<Disciplina>();
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
		StatementResult resultado = session.run("MATCH (n:Disciplina) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Disciplina disciplina = new Disciplina();
			disciplina.setNome(nodeAtual.get("nomeDisciplina").toString());
			allDisciplina.add(disciplina);
		}
		session.close();
		return allDisciplina;
	}

}
