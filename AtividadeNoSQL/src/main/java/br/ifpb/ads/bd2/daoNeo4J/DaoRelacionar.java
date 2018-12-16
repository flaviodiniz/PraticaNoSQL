package br.ifpb.ads.bd2.daoNeo4J;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.exceptions.ClientException;

import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class DaoRelacionar<O> {
	Neo4jConnection connection;

	public void criarRelacionamento(O object1, O object2, String nomeRelacao) {
		@SuppressWarnings("static-access")
		Session session = (Session) connection.getInstance();
		Transaction transacao = session.beginTransaction();

		try {
			transacao.run("MATCH (n:" + object1.getClass() + ") MATCH (n2:" + object2.getClass() + ")"
					+ " CREATE (n)-[:" + nomeRelacao + "]->(n2)");
			transacao.success();

		} finally {
			try {
				transacao.close();
			} catch (ClientException excep) {
				transacao.failure();
				transacao.close();
			}
		}

		session.close();

	}

}
