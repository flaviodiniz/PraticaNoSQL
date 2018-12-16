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

	Neo4jConnection connection;

	@SuppressWarnings("static-access")
	public void salvar(Endereco end) {
		try (Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("CREATE (a:Endereco {rua: '" + end.getRua() + "', cidade: '" + end.getCidade() + "', estado: '"
						+ end.getEstado() + "'})");
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

	public void atualizar(Endereco oldEndereco, Endereco enderecoNovo) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Endereco {cidade: '" + oldEndereco.getCidade() + "'}) SET a.cidade = '"
						+ enderecoNovo.getCidade() + "'");
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

	public void deletar(Endereco end) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Endereco {rua: '" + end.getRua() + "'}) DETACH DELETE a");
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

	public DBObject findOne(Endereco end) {
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
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
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
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
