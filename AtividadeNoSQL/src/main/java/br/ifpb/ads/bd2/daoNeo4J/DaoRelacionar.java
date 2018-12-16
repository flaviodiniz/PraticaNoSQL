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
			transacao.run("MATCH (n:" + object1.getClass().getSimpleName() + ") MATCH (p:" + object2.getClass().getSimpleName() + ")"
					+ " CREATE (n)-[:" + nomeRelacao + "]->(p)");
			transacao.success();
			System.out.println("relacionamento criado");
		}
		session.close();
	}
}
