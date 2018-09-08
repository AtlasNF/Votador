package br.com.atlasnf.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.atlasnf.model.Funcionario;
import br.com.atlasnf.model.dao.FuncionarioDAO;
import br.com.atlasnf.tx.Transacional;


@Named
@ViewScoped
public class LoginBean implements Serializable {
	
	
	private static final String USUARIO_LOGADO = "usuarioLogado";

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpSession session;

	@Inject
	FuncionarioDAO funcionarioDAO;

	private String email;
	private String senha;
	private Funcionario funcionario;
	
	
	@PostConstruct
	public void init() {
		System.out.println("LoginBean.init();");
		funcionario = (Funcionario) session.getAttribute(USUARIO_LOGADO);
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
	}
	
	
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Transacional
	public String logar() {
		Funcionario aux = funcionarioDAO.executarlogin(email, senha);
		String mensagem;
		if (aux != null) {
			funcionario = aux;
			session.setAttribute(USUARIO_LOGADO, funcionario);
			return "/view/sistema/index.xhtml?faces-redirect=true";

		} else {
			mensagem = "Usuário ou senha inválido!";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", mensagem));
			return "";
		}

	}

}
