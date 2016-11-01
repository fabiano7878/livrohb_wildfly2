package br.com.caelum.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class Autorizador implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext context;
	
	public void afterPhase(PhaseEvent event) {
		context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println("Nome da Pagina: "+nomePagina);
		
		if("/login.xhtml".equals(nomePagina)){
			return;
		}
		
		Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(usuarioLogado != null){
			return;
		}
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		
		context.renderResponse();
		
	}

	public void beforePhase(PhaseEvent arg0) {
				
	}

	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}
	
	

}
