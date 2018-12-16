package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Pessoa;
import br.ifpb.ads.bd2.persistenciaMongo.PessoaConverter;

@SuppressWarnings("rawtypes")
public class PessoaDaoMongo extends EntityDao {

	public PessoaDaoMongo() {
		super(Pessoa.class);
	}

	public void salvar(Pessoa pessoa) {
		Map mapPerson = new PessoaConverter().converterToMap(pessoa);
		salvar(mapPerson);
	}

	public void atualizar(Pessoa oldPessoa, Pessoa newPessoa) {
		Map query = new PessoaConverter().converterToMap(oldPessoa);
		Map map = new PessoaConverter().converterToMap(newPessoa);
		atualizar(query, map);
	}

	public void deletar(Pessoa pessoa) {
		Map map = new PessoaConverter().converterToMap(pessoa);
		deletar(map);
	}

	public Pessoa findPessoa(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		Pessoa pessoa = new PessoaConverter().converterToPessoa(dbObject);
		return pessoa;
	}

}
