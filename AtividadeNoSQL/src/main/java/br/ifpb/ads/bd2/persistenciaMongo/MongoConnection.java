package br.ifpb.ads.bd2.persistenciaMongo;

import com.mongodb.DB;
import com.mongodb.Mongo;

@SuppressWarnings("deprecation")
public class MongoConnection {

	private static final String HOST = "localhost";
	private static final int PORT = 27017;
	private static final String DB_NAME = "bd2note_flavio";

	private static MongoConnection uniqInstance;
	private static int mongoInstance = 1;

	private Mongo mongo;
	private DB db;

	private MongoConnection() {
	}

	public static synchronized MongoConnection getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new MongoConnection();
			System.out.println("Conectado ao mongo!");
		}
		return uniqInstance;
	}

	public DB getDB() {
		if (mongo == null) {
			try {
				mongo = new Mongo(HOST, PORT);
				db = mongo.getDB(DB_NAME);
				System.out.println("Mongo instance equals :> " + mongoInstance++);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return db;
	}
}