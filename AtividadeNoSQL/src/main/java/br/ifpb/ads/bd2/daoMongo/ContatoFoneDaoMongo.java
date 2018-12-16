package br.ifpb.ads.bd2.daoMongo;

import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.ContatoTelefone;
import br.ifpb.ads.bd2.persistenciaMongo.ConverterContatoTelefone;

@SuppressWarnings("rawtypes")
public class ContatoFoneDaoMongo extends EntityDao {

	public ContatoFoneDaoMongo() {
		super(ContatoTelefone.class);
	}

	public void salvar(ContatoTelefone contato) {
		Map mapPerson = new ConverterContatoTelefone().converterToMap(contato);
		salvar(mapPerson);
	}

	public void atualizar(ContatoTelefone oldPessoa, ContatoTelefone newPessoa) {
		Map query = new ConverterContatoTelefone().converterToMap(oldPessoa);
		Map map = new ConverterContatoTelefone().converterToMap(newPessoa);
		atualizar(query, map);
	}

	public void deletar(ContatoTelefone pessoa) {
		Map map = new ConverterContatoTelefone().converterToMap(pessoa);
		deletar(map);
	}

	public ContatoTelefone findPerson(Map mapKeyValue) {
		DBObject dbObject = findOne(mapKeyValue);
		ContatoTelefone contato = new ConverterContatoTelefone().converterToContato(dbObject);
		return contato;
	}

}
