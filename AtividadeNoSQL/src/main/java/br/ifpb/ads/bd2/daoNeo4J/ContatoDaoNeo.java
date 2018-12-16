package br.ifpb.ads.bd2.daoNeo4J;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.ContatoTelefone;
import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class ContatoDaoNeo implements DaoNeo4j<ContatoTelefone> {

	Neo4jConnection connection;

	@SuppressWarnings("static-access")
	public void salvar(ContatoTelefone contato){
		try (Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("CREATE (a:Contato {numro: '" + contato.getNumero() + "'})");
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

	public void atualizar(ContatoTelefone oldContato, ContatoTelefone contatoNovo) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Contato {numero: '" + oldContato.getNumero() + "'}) SET a.numero = '"
						+ contatoNovo.getNumero() + "'");
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

	public void deletar(ContatoTelefone contato) {
		try (@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance()) {
			try (Transaction tx = session.beginTransaction()) {
				tx.run("MATCH (a:Contato {numero: '" + contato.getNumero() + "'}) DETACH DELETE a");
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

	public DBObject findOne(ContatoTelefone contato) {
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
		StatementResult resultado = session
				.run("MATCH (n:Contato) WHERE n.numero = '" + contato.getNumero() + "'RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			ContatoTelefone contatoR = new ContatoTelefone();
			contato.setNumero(nodeAtual.get("numero").toString());
			return (DBObject) contatoR;
		}
		session.close();
		return null;
	}

	public List<ContatoTelefone> buscarTodos() {
		ArrayList<ContatoTelefone> allcontato = new ArrayList<ContatoTelefone>();
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
		StatementResult resultado = session.run("MATCH (n:Contato) RETURN n");
		while (resultado.hasNext()) {
			Record nodeAtual = resultado.next();
			ContatoTelefone contato = new ContatoTelefone();
			contato.setNumero(nodeAtual.get("numero").toString());
			allcontato.add(contato);
		}
		session.close();
		return allcontato;
	}

}
