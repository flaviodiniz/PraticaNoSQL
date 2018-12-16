package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Aluno;
import br.ifpb.ads.bd2.persistenciaMongo.AlunoConverter;

@SuppressWarnings("rawtypes")
public class AlunoDaoMongo extends EntityDao {

	public AlunoDaoMongo() {
		super(Aluno.class);
	}

	public void salvar(Aluno aluno) {
		Map mapPerson = new AlunoConverter().converterToMap(aluno);
		salvar(mapPerson);
	}

	public void atualizar(Aluno oldAluno, Aluno novoAluno) {
		Map query = new AlunoConverter().converterToMap(oldAluno);
		Map map = new AlunoConverter().converterToMap(novoAluno);
		atualizar(query, map);
	}

	public void deletar(Aluno aluno) {
		Map map = new AlunoConverter().converterToMap(aluno);
		deletar(map);
	}

	public Aluno findPerson(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		Aluno aluno = new AlunoConverter().converterToAluno(dbObject);
		return aluno;
	}

}
