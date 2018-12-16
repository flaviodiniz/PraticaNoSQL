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

public class PessoaDaoNeo implements DaoNeo4j<Pessoa>{

	Neo4jConnection connection;

	@SuppressWarnings("static-access")
	public void salvar(Pessoa pessoa){
		try (Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("CREATE (a:Pessoa {nomePessoa: '" + pessoa.getNome() + "', idPessoa: '" + pessoa.getIdPessoa()
						+ "', email: '" + pessoa.getEmail() + "'})");
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

	public void atualizar(Pessoa oldPessoa, Pessoa pessoaNova) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Pessoa {nomePessoa: '" + oldPessoa.getNome() + "'}) SET a.nomePessoa = '"
						+ pessoaNova.getNome() + "'");
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

	public void deletar(Pessoa pessoa) {
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

	public DBObject findOne(Pessoa pessoa) {
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
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
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
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
