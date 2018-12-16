package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Aluno;
import br.ifpb.ads.bd2.modelos.Turma;

@SuppressWarnings("rawtypes")
public class TurmaConverter {
	
	@SuppressWarnings("unchecked")
	public Map converterToMap(Turma turma) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("nomeTurma", turma.getNome());
		mapPessoa.put("codigoTurma", turma.getCodigo_turma());
		mapPessoa.put("disciplinaTurma", new DisciplinaConverter().converterToMap(turma.getDisciplina()));
		mapPessoa.put("alunosTurma", new AlunoConverter().converterToMap((Aluno) turma.getAlunos()));
		mapPessoa.put("professorTurma", new ProfessorConverter().converterToMap(turma.getProfessor()));
		return mapPessoa;
	}

	public Turma converterToTurma(DBObject dbo) {
		Turma turma = new Turma();
		turma.setNome(dbo.get("nomeTurma").toString());
		turma.setCodigo_turma((Integer) dbo.get("codigoTurma"));
		turma.setDisciplina(new DisciplinaConverter().converterToDisciplina((DBObject) dbo.get("disciplinaTurma")));
		turma.setAlunos((List<Aluno>) new AlunoConverter().converterToAluno((DBObject) dbo.get("alunosTurma")));
		turma.setProfessor(new ProfessorConverter().converterToProfessor((DBObject) dbo.get("professorTurma")));
		return turma;
	}

}
