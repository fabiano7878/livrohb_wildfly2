package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class PaginaBean implements Serializable{
	
	@Inject
	FacesContext context;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedirectView formLivro() {
		System.out.println("Direciona para Pagina Livro");
		return new RedirectView("livro");
	}
	
	public RedirectView formAutor() {
		System.out.println("Direciona para Pagina Autor");
		return new RedirectView("autor");
	}

	public RedirectView formVendas() {
		System.out.println("Direciona para Pagina Venda");
		return new RedirectView("vendas");
	}
	
	public RedirectView formCarousel() {
		System.out.println("Direciona para Pagina Carousel");
		return new RedirectView("carousel");
	}
	
	public RedirectView formRing() {
		System.out.println("Direciona para Pagina Ring");
		return new RedirectView("ring");
	}
	
	public RedirectView deslogar(){		
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return new RedirectView("login");
	}
}
