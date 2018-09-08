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

import br.com.atlasnf.model.Funcionario;
import br.com.atlasnf.model.FuncionarioIN;
import br.com.atlasnf.model.RetFuncionario;
import br.com.atlasnf.model.RetFuncionarioCreate;
import br.com.atlasnf.model.dao.FuncionarioDAO;
import br.com.atlasnf.tx.Transacional;

@RequestScoped
@Path("funcionarios")
public class FuncionarioRest {

	@Inject
	FuncionarioDAO dao;

	@GET
	@Path("getAllFuncionarios")
	@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML })
	@Transacional
	public List<RetFuncionario> getAllFuncionarios() {
		return dao.getAllRetFuncionarios();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.APPLICATION_XML })
	@Path("createFuncionario")
	@Transacional
	public RetFuncionarioCreate createFuncionario(FuncionarioIN funcionario) {
		RetFuncionarioCreate create = new RetFuncionarioCreate();
		Funcionario aux = dao.buscarFuncionarioPorEmail(funcionario.getEmail());
		if (aux == null) {
			aux = new Funcionario();
			aux.setEmail(funcionario.getEmail());
			aux.setNome(funcionario.getNome());
			aux.setSenha(funcionario.getSenha());
			System.out.println(funcionario.getNome());
			dao.adiciona(aux);
			create.setMessage("Funcionário salvo com sucesso!");
		} else {
			create.setMessage("Já existe um funcionário com esse email!");
		}
		create.setId(aux.getId());
		return create;
	}

}
