package br.com.atlasnf.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RetRecurso {

	private long id;
	private String nome;
	private String dercricao;
	private int votos;

	public RetRecurso(long id, String nome, String dercricao, int votos) {
		super();
		this.id = id;
		this.nome = nome;
		this.dercricao = dercricao;
		this.votos = votos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDercricao() {
		return dercricao;
	}

	public void setDercricao(String dercricao) {
		this.dercricao = dercricao;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

}
