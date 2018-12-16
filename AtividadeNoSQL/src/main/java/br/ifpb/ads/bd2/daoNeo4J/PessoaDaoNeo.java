package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Pessoa;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class PessoaDaoNeo implements DaoNeo4j<Pessoa> {

	private Session session = null;

	public PessoaDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void salvar(Pessoa pessoa) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (a:Pessoa {nomePessoa: '" + pessoa.getNome() + "', idPessoa: " + pessoa.getIdPessoa()
					+ ", email: '" + pessoa.getEmail() + "'})");
			tx.success();
		}
	}

	public void atualizar(Pessoa oldPessoa, Pessoa pessoaNova) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Pessoa {nomePessoa: '" + oldPessoa.getNome() + "'}) SET a.nomePessoa = '"
					+ pessoaNova.getNome() + "'");
			tx.success();
		}
	}

	public void deletar(Pessoa pessoa) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Pessoa {nomePessoa: '" + pessoa.getNome() + "'}) DETACH DELETE a");
			tx.success();
		}
	}

	public DBObject findOne(Pessoa pessoa) {
		StatementResult resultado = session
				.run("MATCH (n:Pessoa) WHERE n.nomePessoa = '" + pessoa.getNome() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Pessoa pessoaR = new Pessoa();
			pessoa.setNome(nodeAtual.get("nomePessoa").toString());
			return (DBObject) pessoaR;
		}
		session.close();
		return null;
	}

	public List<Pessoa> buscarTodos() {
		ArrayList<Pessoa> allPessoa = new ArrayList<Pessoa>();
		StatementResult resultado = session.run("MATCH (n:Pessoa) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(nodeAtual.get("nomePessoa").toString());
			allPessoa.add(pessoa);
		}
		session.close();
		return allPessoa;
	}

}
