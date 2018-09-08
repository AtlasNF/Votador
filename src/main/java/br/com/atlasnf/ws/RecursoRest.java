package br.com.atlasnf.ws;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.atlasnf.model.Recurso;
import br.com.atlasnf.model.RecursoIN;
import br.com.atlasnf.model.RetRecurso;
import br.com.atlasnf.model.RetRecursoCreate;
import br.com.atlasnf.model.dao.RecursoDAO;
import br.com.atlasnf.tx.Transacional;

@RequestScoped
@Path("recursos")
public class RecursoRest {

	@Inject
	RecursoDAO dao;

	@GET
	@Path("getListaRecursosProMaisVotados")
	@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML })
	@Transacional
	public List<RetRecurso> getListaRecursosProMaisVotados() {
		return dao.retListaRecursosPorMaisVotadados();
	}

	@GET
	@Path("getListaRecursos")
	@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML })
	@Transacional
	public List<RetRecurso> getListaRecursos() {
		return dao.retListaRecursos();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML})
	@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8" , MediaType.APPLICATION_XML })
	@Path("createRecurso")
	@Transacional
	public RetRecursoCreate createRecurso(RecursoIN recurso) {
		Recurso aux = new Recurso();
		RetRecursoCreate create = new RetRecursoCreate();
		aux.setDescricao(recurso.getDescricao());
		aux.setNome(recurso.getNome());
		System.out.println(recurso.getNome());
		dao.adiciona(aux);
		create.setMessage("Recurso salvo com sucesso!");
		create.setRecursoID(aux.getId());
		return create;
	}
}
