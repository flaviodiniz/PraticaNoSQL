package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

@SuppressWarnings("rawtypes")
public interface IDao {
    void salvar(Map mapEntity);

    void atualizar(Map mapQuery, Map mapEntity);

    void deletar(Map mapEntity);

    DBObject findOne(Map mapEntity);

}