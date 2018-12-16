package br.ifpb.ads.bd2.persistenciaNeo4J;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

public class Neo4jConnection implements AutoCloseable {
	Driver driver = null;
	Session session = null;

	public Neo4jConnection() {
		criarDriver();
		criarSession();
	}
	
	public void criarDriver() {
		try {
			this.driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "admin"));
		} catch (Exception e) {
			System.out.println("Erro ao criar ao estabelecer conexao.");
		} 
	}

	public void criarSession() {
		try {
			this.session = driver.session();
		} catch (Exception e) {
			System.out.println("Erro ao criar Session.");
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
