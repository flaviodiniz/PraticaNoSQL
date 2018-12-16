package br.ifpb.ads.bd2.persistenciaNeo4J;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

public class Neo4jConnection implements AutoCloseable {
	private final Driver driver;
	private static Neo4jConnection uniqInstance;

	public static synchronized Neo4jConnection getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new Neo4jConnection("bolt://localhost:7687", "neo4j", "admin");
			System.out.println("Conectado ao neo4j!");
		}
		return uniqInstance;
	}

	public Neo4jConnection(String uri, String user, String password) {
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
	}

	public void close() throws Exception {
		driver.close();
	}

	public void printGreeting(final String message) {
		try (Session session = driver.session()) {
			String greeting = session.writeTransaction(new TransactionWork<String>() {
				@Override
				public String execute(Transaction tx) {
					StatementResult result = tx.run("CREATE (a:Greeting) " + "SET a.message = $message "
							+ "RETURN a.message + ', from node ' + id(a)");
					return result.single().get(0).asString();
				}
			});
			System.out.println(greeting);
		}
	}
	
}
