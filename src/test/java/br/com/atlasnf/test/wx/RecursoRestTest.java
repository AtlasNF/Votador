package br.com.atlasnf.test.wx;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.google.gson.Gson;

import br.com.atlasnf.model.Recurso;
import br.com.atlasnf.ws.RecursoRest;

public class RecursoRestTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(RecursoRest.class);
	}

	@Test
	public void createRecurso() {
		Gson gson = new Gson();
		Recurso recurso = new Recurso();
		recurso.setNome("Criação WS funcionário");
		recurso.setDescricao("Desenvolver a criação de Webservices para a consulta e cadastro de novos funcionários.");
		String json = gson.toJson(recurso);

		String response = target("recursos/createRecurso").request()
				.post(Entity.entity(json, MediaType.APPLICATION_JSON), String.class);
		System.out.println(response);

	}

}
