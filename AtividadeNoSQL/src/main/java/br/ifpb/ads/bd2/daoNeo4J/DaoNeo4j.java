package br.ifpb.ads.bd2.daoNeo4J;

import org.neo4j.graphdb.Label;

import com.mongodb.DBObject;

public interface DaoNeo4j<O> {

	public enum NodeTipo implements Label {
		Pessoa, Endereco, ContatoTelefone, Curso, Turma, Disciplina, Professor, Aluno;
	}

	void salvar(O object);

	void atualizar(O object, O object2);

	void deletar(O object);

	DBObject findOne(O object);

}
