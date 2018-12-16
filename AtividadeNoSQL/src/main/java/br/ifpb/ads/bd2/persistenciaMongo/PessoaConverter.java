package br.ifpb.ads.bd2.persistenciaMongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.DBObject;

import br.ifpb.ads.bd2.modelos.ContatoTelefone;
import br.ifpb.ads.bd2.modelos.Pessoa;

@SuppressWarnings("rawtypes")
public class PessoaConverter {

	@SuppressWarnings("unchecked")
	public Map converterToMap(Pessoa pessoa) {
		Map mapPessoa = new HashMap();
		mapPessoa.put("idPessoa", pessoa.getIdPessoa());
		mapPessoa.put("nomePessoa", pessoa.getNome());
		mapPessoa.put("email", pessoa.getEmail());
		//mapPessoa.put("endereco", new EnderecoConverter().converterToMap(pessoa.getEndereco()));
		return mapPessoa;
	}

	public Pessoa converterToPessoa(DBObject dbo) {
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa((Integer) dbo.get("idPessoa"));
		pessoa.setNome(dbo.get("nomePessoa").toString());
		pessoa.setEmail(dbo.get("segundoNome").toString());
		pessoa.setEndereco(new EnderecoConverter().converterToEndereco((DBObject) dbo.get("endereco")));
		pessoa.setTelefones((List<ContatoTelefone>) new ConverterContatoTelefone().converterToContato((DBObject) dbo.get("contato")));
		return pessoa;
	}

}
