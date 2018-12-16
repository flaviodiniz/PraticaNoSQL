package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.ContatoTelefone;

@SuppressWarnings("unchecked")
public class ConverterContatoTelefone {
	
	public Map converterToMap(ContatoTelefone contato) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("numero", contato.getNumero());
		mapPessoa.put("pessoa", new PessoaConverter().converterToMap(contato.getPessoa()));
		return mapPessoa;
	}

	public ContatoTelefone converterToContato(DBObject dbo) {
		ContatoTelefone contato = new ContatoTelefone();
		contato.setNumero(dbo.get("numero").toString());
		contato.setPessoa(new PessoaConverter().converterToPessoa((DBObject) dbo.get("pessoa")));
		return contato;
	}

}
