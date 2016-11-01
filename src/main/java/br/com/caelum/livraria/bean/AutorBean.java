package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.AutorDataModel;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Autor autor;
	
	private List<Autor> autores;
	
	@Inject
	private AutorDataModel autorDataModel;
	
	@Inject
	private AutorDAO dao;
	
	@Inject
	FacesContext context;
	
	public Autor getAutor() {
		return autor;
	}
	
	@Transactional
	public RedirectView gravar() {
		RedirectView rv = new RedirectView("autor");
		if (this.autor.getId() == null) {
			System.out.println("Gravando autor: " + this.autor.getNome());
			this.dao.adiciona(this.autor);
			this.autores = this.dao.listaTodos();
		} else if ("/login.xhtml".equals((String) context.getExternalContext()
				.getSessionMap().get("paginaAnterior"))) {
			System.out.println("Atualizando dados do autor: e direcionando para pagina Livro"
					+ this.autor.getNome());
			this.dao.atualiza(this.autor);
		} else {
			System.out.println("Atualizando dados do autor: e fica na pagina Autor."
					+ this.autor.getNome());
			this.dao.atualiza(this.autor);
			rv = new RedirectView("autor");
		}
		this.autor = new Autor();
		return rv;
	}

	public void carregar(Autor pAutor) {
		System.out.println("Carregar/Atualizar o autor.");
		this.autor = pAutor;
	}
	
	@Transactional
	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		this.dao.remove(autor);
		this.autores = this.dao.listaTodos();
		
	}

	public void carregaAutorPorId() {
		System.out.println("Carrega Autor por Id!");
		this.autor = this.dao.buscaPorId(this.autor
				.getAutorId());
	}

	/**
	 * @return the autores
	 */
	public List<Autor> getAutores() {
		if(this.autores == null){
			this.autores = this.dao.listaTodos(); 
		}
		return autores;
	}

	/**
	 * @return the autorDataModel
	 */
	public AutorDataModel getAutorDataModel() {
		return autorDataModel;
	}
	
	
}
