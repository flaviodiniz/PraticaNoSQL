package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Endereco;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class EnderecoDaoNeo implements DaoNeo4j<Endereco> {

	private Session session = null;

	public EnderecoDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void salvar(Endereco end) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (a:Endereco {rua: '" + end.getRua() + "', cidade: '" + end.getCidade() + "', estado: '"
					+ end.getEstado() + "'})");
			tx.success();
			session.close();
		}
	}

	public void atualizar(Endereco oldEndereco, Endereco enderecoNovo) {

		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Endereco {cidade: '" + oldEndereco.getCidade() + "'}) SET a.cidade = '"
					+ enderecoNovo.getCidade() + "'");
			tx.success();
			session.close();
		}
	}

	public void deletar(Endereco end) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Endereco {rua: '" + end.getRua() + "'}) DETACH DELETE a");
			tx.success();
			session.close();
		}
	}

	public DBObject findOne(Endereco end) {
		StatementResult resultado = session.run("MATCH (n:Endereco) WHERE n.rua = '" + end.getRua() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Endereco pessoaR = new Endereco();
			end.setRua(nodeAtual.get("rua").toString());
			return (DBObject) pessoaR;
		}
		session.close();
		return null;
	}

	public List<Endereco> buscarTodos() {
		ArrayList<Endereco> allEndereco = new ArrayList<Endereco>();
		StatementResult resultado = session.run("MATCH (n:Endereco) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			Endereco end = new Endereco();
			end.setRua(nodeAtual.get("rua").toString());
			allEndereco.add(end);
		}
		session.close();
		return allEndereco;
	}

}
