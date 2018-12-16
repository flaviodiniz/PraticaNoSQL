package br.ifpb.ads.bd2.daoMongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import br.ifpb.ads.bd2.persistenciaMongo.MongoConnection;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class EntityDao implements IDao {

	@SuppressWarnings("unused")
	private Class persistentClass;
	private DBCollection dbCollection;

	public EntityDao(Class persistentClass) {
		this.persistentClass = persistentClass;
		this.dbCollection = MongoConnection.getInstance().getDB().getCollection(persistentClass.getSimpleName());
	}

	protected DBCollection getDbCollection() {
		return dbCollection;
	}

	public void salvar(Map mapEntity) {
		BasicDBObject document = new BasicDBObject(mapEntity);
		dbCollection.save(document);
		System.out.println("Save :> " + document);
	}

	public void atualizar(Map mapQuery, Map mapEntity) {
		BasicDBObject query = new BasicDBObject(mapQuery);
		BasicDBObject entity = new BasicDBObject(mapEntity);
		dbCollection.update(query, entity);
	}

	public void deletar(Map mapEntity) {
		BasicDBObject entity = new BasicDBObject(mapEntity);
		dbCollection.remove(entity);
	}

	public DBObject findOne(Map mapEntity) {
		BasicDBObject query = new BasicDBObject(mapEntity);
		return dbCollection.findOne(query);
	}

}