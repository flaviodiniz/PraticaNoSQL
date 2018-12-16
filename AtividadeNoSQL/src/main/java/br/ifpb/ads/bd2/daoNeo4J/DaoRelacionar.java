package br.ifpb.ads.bd2.daoNeo4J;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;

import br.ifpb.ads.bd2.persistenciaNeo4J.Neo4jConnection;

public class DaoRelacionar<O> {
	private Session session = null;

	public DaoRelacionar() {
		@SuppressWarnings("resource")
		Neo4jConnection connection = new Neo4jConnection();
		this.session = connection.getSession();
	}

	public void criarRelacionamento(O object1, O object2, String nomeRelacao) {
		try (Transaction transacao = session.beginTransaction();){
			transacao.run("MATCH (n:" + object1 + ") MATCH (n2:" + object2 + ")"
					+ " CREATE (n)-[:" + nomeRelacao + "]->(n2)");
			transacao.success();
		}
		session.close();
	}
}
