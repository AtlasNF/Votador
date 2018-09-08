package br.com.atlasnf.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecursoIN {

	private String nome;
	private String descricao;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
