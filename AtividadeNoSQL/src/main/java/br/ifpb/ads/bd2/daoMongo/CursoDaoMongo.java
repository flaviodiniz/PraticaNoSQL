package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Curso;
import br.ifpb.ads.bd2.persistenciaMongo.CursoConverter;

@SuppressWarnings("rawtypes")
public class CursoDaoMongo extends EntityDao {

	public CursoDaoMongo() {
		super(Curso.class);
	}

	public void salvar(Curso curso) {
		Map mapPerson = new CursoConverter().converterToMap(curso);
		salvar(mapPerson);
	}

	public void atualizar(Curso oldCurso, Curso novoCurso) {
		Map query = new CursoConverter().converterToMap(oldCurso);
		Map map = new CursoConverter().converterToMap(novoCurso);
		atualizar(query, map);
	}

	public void deletar(Curso curso) {
		Map map = new CursoConverter().converterToMap(curso);
		deletar(map);
	}

	public Curso findPerson(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		Curso curso = new CursoConverter().converterToCurso(dbObject);
		return curso;
	}

}
