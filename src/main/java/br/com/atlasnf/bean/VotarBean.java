package br.com.atlasnf.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.atlasnf.model.Funcionario;
import br.com.atlasnf.model.Recurso;
import br.com.atlasnf.model.Voto;
import br.com.atlasnf.model.dao.FuncionarioDAO;
import br.com.atlasnf.model.dao.RecursoDAO;
import br.com.atlasnf.model.dao.VotoDAO;
import br.com.atlasnf.tx.Transacional;
import br.com.atlasnf.util.RecursoDataModel;

@Named
@ViewScoped
public class VotarBean implements Serializable {

	private static final String USUARIO_LOGADO = "usuarioLogado";

	private static final long serialVersionUID = 1L;

	@Inject
	private HttpSession session;

	@Inject
	FuncionarioDAO funcionarioDAO;
	@Inject
	RecursoDAO recursoDAO;
	@Inject
	VotoDAO votoDAO;

	private Funcionario funcionario;
	private List<Recurso> recursos;
	private Recurso recursoSelected;
	private Voto voto;

	@PostConstruct
	@Transacional
	public void init() {
		System.out.println("VotarBean.init();");
		funcionario = (Funcionario) session.getAttribute(USUARIO_LOGADO);
		voto = new Voto();
	}

	@Transacional
	public List<Recurso> getRecursos() {
		return recursoDAO.listaTodosPorMaisVotados();
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public Recurso getRecursoSelected() {
		return recursoSelected;
	}

	public void setRecursoSelected(Recurso recursoSelected) {
		this.recursoSelected = recursoSelected;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Recurso selecionado!", ((Recurso) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@Transacional
	public void confrimarVoto() {
		FacesMessage msg;
		if (recursoSelected == null) {
			msg = new FacesMessage("Erro", "Recurso não selecionado!");
		} else {
			if (!votoDAO.buscarVotoFuncERecurso(funcionario, recursoSelected)) {
				voto.setFuncionario(funcionario);
				voto.setRecurso(recursoSelected);
				voto.setData(Calendar.getInstance());
				votoDAO.adiciona(voto);
				msg = new FacesMessage("Voto", "Voto realizado com sucesso!");
			} else {
				msg = new FacesMessage("Voto", "Voçê já votou neste recurso!");

			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
}
