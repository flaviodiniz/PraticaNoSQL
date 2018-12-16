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

	private Session session = null;

	public ContatoDaoNeo() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void salvar(ContatoTelefone contato) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("CREATE (:Contato {numero: '" + contato.getNumero() + "'})");
			tx.success();
			session.close();
		}
	}

	public void atualizar(ContatoTelefone oldContato, ContatoTelefone contatoNovo) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Contato {numero: '" + oldContato.getNumero() + "'}) SET a.numero = '"
					+ contatoNovo.getNumero() + "'");
			tx.success();
			session.close();
		}
	}

	public void deletar(ContatoTelefone contato) {
		try (Transaction tx = session.beginTransaction()) {
			tx.run("MATCH (a:Contato {numero: '" + contato.getNumero() + "'}) DETACH DELETE a");
			tx.success();
			session.close();
		}
	}

	public DBObject findOne(ContatoTelefone contato) {
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
