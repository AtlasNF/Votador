package br.com.atlasnf.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.atlasnf.model.Funcionario;
import br.com.atlasnf.model.dao.FuncionarioDAO;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	private static final String USUARIO_LOGADO = "usuarioLogado";

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpSession session;

	@Inject
	FuncionarioDAO funcionarioDAO;
	private Funcionario funcionario;

	public MenuBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		System.out.println("MenuBean.init();");
		funcionario = (Funcionario) session.getAttribute(USUARIO_LOGADO);
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
	}

	public String logout() {
		session.removeAttribute(USUARIO_LOGADO);
		session.invalidate();
		funcionario = new Funcionario();
		return "/view/index.xhtml?faces-redirect=true";

	}

	public String votar() {

		return "/view/sistema/votar.xhtml";

	}

	public String listaVotos() {

		return "/view/sistema/listavotos.xhtml";

	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
