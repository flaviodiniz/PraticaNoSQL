package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Disciplina;
import br.ifpb.ads.bd2.persistenciaMongo.DisciplinaConverter;

@SuppressWarnings("rawtypes")
public class DisciplinaDaoMongo extends EntityDao {

	public DisciplinaDaoMongo() {
		super(Disciplina.class);
	}

	public void salvar(Disciplina disciplina) {
		Map mapPerson = new DisciplinaConverter().converterToMap(disciplina);
		salvar(mapPerson);
	}

	public void atualizar(Disciplina oldDisciplina, Disciplina novoDisciplina) {
		Map query = new DisciplinaConverter().converterToMap(oldDisciplina);
		Map map = new DisciplinaConverter().converterToMap(novoDisciplina);
		atualizar(query, map);
	}

	public void deletar(Disciplina disciplina) {
		Map map = new DisciplinaConverter().converterToMap(disciplina);
		deletar(map);
	}

	public Disciplina findPerson(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		Disciplina disciplina = new DisciplinaConverter().converterToDisciplina(dbObject);
		return disciplina;
	}

}
