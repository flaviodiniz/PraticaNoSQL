package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.Endereco;

@SuppressWarnings("rawtypes")
public class EnderecoConverter {

	@SuppressWarnings("unchecked")
	public Map converterToMap(Endereco endereco) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("rua", endereco.getRua());
		mapPessoa.put("cidade", endereco.getCidade());
		mapPessoa.put("bairro", endereco.getBairro());
		mapPessoa.put("estado", endereco.getEstado());
		mapPessoa.put("complemento", endereco.getComplemento());
		mapPessoa.put("cep", endereco.getCep());
		return mapPessoa;
		
	}

	public Endereco converterToEndereco(DBObject dbo) {
		Endereco endereco = new Endereco();
		endereco.setRua(dbo.get("rua").toString());
		endereco.setCidade(dbo.get("cidade").toString());
		endereco.setBairro(dbo.get("bairro").toString());
		endereco.setEstado(dbo.get("estado").toString());
		endereco.setComplemento(dbo.get("complemento").toString());
		endereco.setCep(dbo.get("cep").toString());
		return endereco;
	}

}
