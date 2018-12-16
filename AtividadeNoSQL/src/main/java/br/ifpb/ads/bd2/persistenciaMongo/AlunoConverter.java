package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Aluno;
import br.ifpb.ads.bd2.modelos.Turma;

@SuppressWarnings("rawtypes")
public class AlunoConverter {
	
	@SuppressWarnings("unchecked")
	public Map converterToMap(Aluno aluno) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("matriculaAluno", aluno.getMatricula());
		//mapPessoa.put("turmasAluno", new TurmaConverter().converterToMap((Turma) aluno.getTurmas()));
		return mapPessoa;

	}

	public Aluno converterToAluno(DBObject dbo) {
		Aluno aluno = new Aluno();
		aluno.setMatricula((Integer) (dbo.get("matriculaAluno")));
		aluno.setTurmas((List<Turma>) new TurmaConverter().converterToTurma((DBObject) dbo.get("turmasAluno")));
		return aluno;
	}

}
