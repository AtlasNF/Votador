package br.com.atlasnf.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.atlasnf.model.Funcionario;
import br.com.atlasnf.model.Voto;
import br.com.atlasnf.model.dao.FuncionarioDAO;
import br.com.atlasnf.model.dao.RecursoDAO;
import br.com.atlasnf.model.dao.VotoDAO;
import br.com.atlasnf.tx.Transacional;

@Named
@ViewScoped
public class ListaVotosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String USUARIO_LOGADO = "usuarioLogado";

	@Inject
	private HttpSession session;

	@Inject
	FuncionarioDAO funcionarioDAO;
	@Inject
	RecursoDAO recursoDAO;
	@Inject
	VotoDAO votoDAO;
	private Funcionario funcionario;
	

	@PostConstruct
	@Transacional
	public void init() {
		System.out.println("ListaVotosBean.init();");
		funcionario = (Funcionario) session.getAttribute(USUARIO_LOGADO);
		
	}
	
	public List<Voto> getListVotos(){
		return votoDAO.buscarVotoFunc(funcionario);
	}
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


}
