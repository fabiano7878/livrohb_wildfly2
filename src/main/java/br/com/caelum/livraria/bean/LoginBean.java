package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class LoginBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario user;

	@Inject
	FacesContext context;
	
	@Inject
	private UsuarioDAO dao;
	
	/**
	 * @return the user
	 */
	public Usuario getUser() {
		return user;
	}


	public RedirectView efetuaLogin(){
		System.out.println("Efetuando login do Usuário: " + this.user.getEmail());
		boolean validaUsuario = this.dao.validaUsuario(this.user);		
		if(validaUsuario){
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.user);
			return new RedirectView("livro");			
		}	
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage("null", new FacesMessage("Usuario não encontrado"));
			return new RedirectView("login");
		
	}
	
}
