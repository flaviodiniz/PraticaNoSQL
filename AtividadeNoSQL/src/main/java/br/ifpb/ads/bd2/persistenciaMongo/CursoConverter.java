package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Curso;
import br.ifpb.ads.bd2.modelos.Disciplina;
import br.ifpb.ads.bd2.modelos.Tipo;

@SuppressWarnings("rawtypes")
public class CursoConverter {
	
	@SuppressWarnings("unchecked")
	public Map converterToMap(Curso curso) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("nomeCurso", curso.getNome());
		mapPessoa.put("codCurso", curso.getCod_curso());
		mapPessoa.put("tipoCurso", curso.getTipo());
		mapPessoa.put("disciplinasCurso", new DisciplinaConverter().converterToMap((Disciplina) curso.getDisciplinas()));
		return mapPessoa;
	}

	public Curso converterToCurso(DBObject dbo) {
		Curso curso = new Curso();
		curso.setCod_curso((Integer) dbo.get("codCurso"));
		curso.setNome(dbo.get("nomeCurso").toString());
		curso.setTipo((Tipo) dbo.get("tipoCurso"));
		curso.setDisciplinas((List<Disciplina>) new DisciplinaConverter().converterToDisciplina((DBObject) dbo.get("endereco")));
		return curso;
	}

}
