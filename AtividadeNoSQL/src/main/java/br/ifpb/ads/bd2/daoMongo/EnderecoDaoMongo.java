package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Endereco;
import br.ifpb.ads.bd2.persistenciaMongo.EnderecoConverter;

@SuppressWarnings("rawtypes")
public class EnderecoDaoMongo extends EntityDao {

	public EnderecoDaoMongo() {
		super(Endereco.class);
	}

	public void salvar(Endereco endereco) {
		Map mapPerson = new EnderecoConverter().converterToMap(endereco);
		salvar(mapPerson);
	}

	public void atualizar(Endereco oldEndereco, Endereco novoEndereco) {
		Map query = new EnderecoConverter().converterToMap(oldEndereco);
		Map map = new EnderecoConverter().converterToMap(novoEndereco);
		atualizar(query, map);
	}

	public void deletar(Endereco endereco) {
		Map map = new EnderecoConverter().converterToMap(endereco);
		deletar(map);
	}

	public Endereco findPerson(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		Endereco endereco = new EnderecoConverter().converterToEndereco(dbObject);
		return endereco;
	}

}
