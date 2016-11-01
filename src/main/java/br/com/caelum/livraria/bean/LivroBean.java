package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.dao.LivroDAO;
import br.com.caelum.livraria.interceptors.Log;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.LivroDataModel;

@Named
@ViewScoped
public class LivroBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Livro livro;
	
	private List<Livro> livros;
	private Autor autor;
	private Integer autorId;	
	private LivroDataModel livroDataModel = new LivroDataModel();
	private List<String> listaGeneros = Arrays.asList("Romance", "Drama", "Ação");
	
	@Inject
	private LivroDAO daoLivro;
	
	@Inject
	private AutorDAO daoAutor;
	
	@Inject
	FacesContext context;

	/**
	 * @return the autor
	 */
	public Autor getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	/**
	 * @return the autorId
	 */
	public Integer getAutorId() {
		return autorId;
	}

	/**
	 * @param autorId
	 *            the autorId to set
	 */
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	/**
	 * @param livro the livro to set
	 */
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	@Log
	public List<Livro> getLivros() {
		if(this.livros == null){
			this.livros = this.daoLivro.listaTodos();
		}
		return livros;
	}
	
	@Transactional
	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		if(this.livro.getId() == null){
			System.out.println("Gravando livro " + this.livro.getTitulo());
			daoLivro.adiciona(this.livro);
			this.livros = this.daoLivro.listaTodos();
		}else{
			System.out.println("Atualizando livro " + this.livro.getTitulo());
			this.daoLivro.atualiza(this.livro);
		}
				
		this.livro = new Livro();
	}
	
	public void removerAutorLivro(Autor pAutor){
		System.out.println("Removevento Autor.");
		this.livro.removeAutor(pAutor);
	}
	
	public void gravarAutor() {
		Autor autor = this.daoAutor.buscaPorId(this.autorId);
			this.livro.adicionaAutor(autor);
	}

	public void carregaLivroPorId() {
		System.out.println("Carrega Livro por Id!");
		this.livro = this.daoLivro.buscaPorId(this.livro.getLivroId());
	}
	
	public void carregar(Livro pLivro) {
		System.out.println("Carregar/Atualizar o livro.");
		this.livro = this.daoLivro.buscaPorId(this.livro.getId());
	}
	
	@Transactional
	public void remover(Livro pLivro) {
		System.out.println("Removendo o livro.");
		this.daoLivro.remove(pLivro);
		this.livros = this.daoLivro.listaTodos();
	}

/*	public RedirectView formAutor() {
		System.out.println("Direciona para Pagina do autor!");
		String pagina = "/login.xhtml";
		context.getExternalContext().getSessionMap().put("paginaAnterior", pagina);
		return new RedirectView("autor");
	}*/
	
	public void comecaComDigitoUm(FacesContext fd, UIComponent component,
			Object value) throws ValidationException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"Deveria começar com 1"));
		}
	}
	
	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) { // java.util.Locale

        //tirando espaços do filtro
        String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();

        System.out.println("Filtrando pelo " + textoDigitado + ", Valor do elemento: " + valorColuna);

        // o filtro é nulo ou vazio?
        if (textoDigitado == null || textoDigitado.equals("")) {
            return true;
        }

        // elemento da tabela é nulo?
        if (valorColuna == null) {
            return false;
        }

        try {
            // fazendo o parsing do filtro para converter para Double
            Double precoDigitado = Double.valueOf(textoDigitado);
            Double precoColuna = (Double) valorColuna;

            // comparando os valores, compareTo devolve um valor negativo se o value é menor do que o filtro
            return precoColuna.compareTo(precoDigitado) < 0;

        } catch (NumberFormatException e) {

            // usuario nao digitou um numero
            return false;
        }
        
        
	}

	/**
	 * @return the listaGeneros
	 */
	public List<String> getListaGeneros() {
		return listaGeneros;
	}

	/**
	 * @return the livroDataModel
	 */
	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}
	
	
	
}
