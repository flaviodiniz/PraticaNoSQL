package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Disciplina;

@SuppressWarnings("rawtypes")
public class DisciplinaConverter {
	
	@SuppressWarnings("unchecked")
	public Map converterToMap(Disciplina disciplina) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("codDisciplina", disciplina.getCodDisciplina());
		mapPessoa.put("nomeDisciplina", disciplina.getNome());
		mapPessoa.put("ementa", disciplina.getEmenta());
		mapPessoa.put("cursoDisciplina", new CursoConverter().converterToMap(disciplina.getCurso()));
		return mapPessoa;
	}

	public Disciplina converterToDisciplina(DBObject dbo) {
		Disciplina disciplina = new Disciplina();
		disciplina.setCodDisciplina((Integer) dbo.get("codDisciplina"));
		disciplina.setNome(dbo.get("nomeDisciplina").toString());
		disciplina.setEmenta(dbo.get("ementa").toString());
		disciplina.setCurso(new CursoConverter().converterToCurso((DBObject) dbo.get("cursoDisciplina")));
		return disciplina;
	}

}
