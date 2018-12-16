package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Professor;
import br.ifpb.ads.bd2.persistenciaMongo.ProfessorConverter;

@SuppressWarnings("rawtypes")
public class ProfessorDaoMongo extends EntityDao {

	public ProfessorDaoMongo() {
		super(Professor.class);
	}

	public void salvar(Professor endereco) {
		Map mapPerson = new ProfessorConverter().converterToMap(endereco);
		salvar(mapPerson);
	}

	public void atualizar(Professor oldProfessor, Professor novoProfessor) {
		Map query = new ProfessorConverter().converterToMap(oldProfessor);
		Map map = new ProfessorConverter().converterToMap(novoProfessor);
		atualizar(query, map);
	}

	public void deletar(Professor professor) {
		Map map = new ProfessorConverter().converterToMap(professor);
		deletar(map);
	}

	public Professor findPerson(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		Professor professor = new ProfessorConverter().converterToProfessor(dbObject);
		return professor;
	}

}
