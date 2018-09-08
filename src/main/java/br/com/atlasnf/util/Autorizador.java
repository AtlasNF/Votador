package br.com.atlasnf.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.atlasnf.model.Funcionario;



public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		System.out.println("afterPhase(PhaseEvent evento)");
		FacesContext context = evento.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println("Nome da página: " + nomePagina);
		if (nomePagina.endsWith("/view/index.xhtml")
				|| nomePagina.endsWith("/view/init/initdb.xhtml")) {
			return;
		}

		Funcionario usuarioLogado = (Funcionario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (usuarioLogado != null) {
			System.out.println("Usuário está logado");
			return;
		}

		// Redirecionamento para index.xhtml
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/view/index.xhtml?faces-redirect=true");
		context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent evento) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
