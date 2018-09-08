package br.com.atlasnf.model;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String comentario;
	@Column(name = "data", columnDefinition = "timestamp with time zone", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	@ManyToOne
	private Funcionario funcionario;
	@ManyToOne
	private Recurso recurso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public String dataToStrig() {
		DateFormat dataFormatada = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ZZZ");
		Date dataAux = data.getTime();
		System.out.println(data.toString());
		return dataFormatada.format(dataAux);
	}

}
