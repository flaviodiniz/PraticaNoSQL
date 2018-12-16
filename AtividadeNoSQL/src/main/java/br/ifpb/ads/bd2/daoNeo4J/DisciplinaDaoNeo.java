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

	private Session session = null;

	public DisciplinaDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void salvar(Disciplina disciplina) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (a:Disciplina {nomeDisciplina: '" + disciplina.getNome() + "', codDisciplina: "
					+ disciplina.getCodDisciplina() + "})");
			tx.success();
			session.close();
		}
	}

	public void atualizar(Disciplina oldDisciplina, Disciplina disciplinaNova) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Disciplina {nomeDisciplina: '" + oldDisciplina.getNome() + "'}) SET a.nomeDisciplina = '"
					+ disciplinaNova.getNome() + "'");
			tx.success();
			session.close();
		}
	}

	public void deletar(Disciplina pessoa) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Pessoa {nomePessoa: '" + pessoa.getNome() + "'}) DETACH DELETE a");
			tx.success();
			session.close();
		}
	}

	public DBObject findOne(Disciplina disciplina) {
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
