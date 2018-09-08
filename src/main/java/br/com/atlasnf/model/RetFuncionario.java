package br.com.atlasnf.model;

public class RetFuncionario {

	private Long id;
	private String nome;
	private String email;
	private int numVotos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getNumVotos() {
		return numVotos;
	}

	public void setNumVotos(int numVotos) {
		this.numVotos = numVotos;
	}

	public RetFuncionario(Long id, String nome, String email, int numVotos) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.numVotos = numVotos;
	}
	

}
