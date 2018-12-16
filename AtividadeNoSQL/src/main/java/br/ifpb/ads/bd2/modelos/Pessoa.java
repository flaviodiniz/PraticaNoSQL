package br.ifpb.ads.bd2.modelos;

import java.util.List;

public class Pessoa {

	private int idPessoa;

	private String nome;

	private String email;
	
	private Endereco endereco;

	private List<ContatoTelefone> telefones;

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<ContatoTelefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<ContatoTelefone> telefones) {
		this.telefones = telefones;
	}

	public Pessoa() {

	}

}
