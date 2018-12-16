package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Professor;
import br.ifpb.ads.bd2.modelos.Turma;

@SuppressWarnings("rawtypes")
public class ProfessorConverter {
	
	@SuppressWarnings("unchecked")
	public Map converterToMap(Professor prof) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("tituloProf", prof.getIdPessoa());
		//mapPessoa.put("turmaProf", new TurmaConverter().converterToMap((Turma) prof.getTurmas()));
		return mapPessoa;
	}

	public Professor converterToProfessor(DBObject dbo) {
		Professor prof = new Professor();
		prof.setTitulo(dbo.get("tituloProf").toString());
		prof.setTurmas((List<Turma>) new TurmaConverter().converterToTurma((DBObject) dbo.get("turmaProf")));
		return prof;
	}

}
