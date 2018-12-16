package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Curso;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class CursoDaoNeo implements DaoNeo4j<Curso> {
	private Session session = null;

	public CursoDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	@SuppressWarnings("static-access")
	public void salvar(Curso curso) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (a:Curso {nomeCurso: '" + curso.getNome() + "', codCurso: " + curso.getCod_curso() + "})");
			tx.success();
			session.close();
		}
	}

	public void atualizar(Curso oldCurso, Curso cursoNovo) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Curso {nomePessoa: '" + oldCurso.getNome() + "'}) SET a.nomeCurso = '"
					+ cursoNovo.getNome() + "'");
			tx.success();
			session.close();
		}
	}

	public void deletar(Curso curso) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Curso {nomeCurso: '" + curso.getNome() + "'}) DETACH DELETE a");
			tx.success();
			session.close();
		}
	}

	public DBObject findOne(Curso curso) {
		StatementResult resultado = session
				.run("MATCH (n:Curso) WHERE n.nomeCurso = '" + curso.getNome() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Curso cursoR = new Curso();
			curso.setNome(nodeAtual.get("nomePessoa").toString());
			return (DBObject) cursoR;
		}
		session.close();
		return null;
	}

	public List<Curso> buscarTodos() {
		ArrayList<Curso> allCurso = new ArrayList<Curso>();
		StatementResult resultado = session.run("MATCH (n:Curso) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Curso curso = new Curso();
			curso.setNome(nodeAtual.get("nomeCurso").toString());
			allCurso.add(curso);
		}
		session.close();
		return allCurso;
	}

}
