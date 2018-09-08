package br.com.atlasnf.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RetRecursoCreate {

	private String message;
	private Long recursoID;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getRecursoID() {
		return recursoID;
	}

	public void setRecursoID(Long recursoID) {
		this.recursoID = recursoID;
	}

}
