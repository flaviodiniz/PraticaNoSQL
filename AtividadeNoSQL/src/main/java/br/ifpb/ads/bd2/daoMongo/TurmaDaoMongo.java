package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Turma;
import br.ifpb.ads.bd2.persistenciaMongo.TurmaConverter;

@SuppressWarnings("rawtypes")
public class TurmaDaoMongo extends EntityDao {

	public TurmaDaoMongo() {
		super(Turma.class);
	}

	public void salvar(Turma turma) {
		Map mapPerson = new TurmaConverter().converterToMap(turma);
		salvar(mapPerson);
	}

	public void atualizar(Turma oldTurma, Turma novoTurma) {
		Map query = new TurmaConverter().converterToMap(oldTurma);
		Map map = new TurmaConverter().converterToMap(novoTurma);
		atualizar(query, map);
	}

	public void deletar(Turma turma) {
		Map map = new TurmaConverter().converterToMap(turma);
		deletar(map);
	}

	public Turma findPerson(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		Turma turma = new TurmaConverter().converterToTurma(dbObject);
		return turma;
	}

}
