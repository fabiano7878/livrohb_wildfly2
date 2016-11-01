package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer livroId;
	private String titulo;
	private String isbn;
	private double preco;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataLancamento = Calendar.getInstance();
	private String genero;

	//@ManyToMany(fetch=FetchType.Lazy) este é o padrão por este motivo não usamos os parenteses
	@ManyToMany
	private List<Autor> autores = new ArrayList<Autor>();
	
	/**
	 * @return the livroId
	 */
	public Integer getLivroId() {
		return livroId;
	}

	/**
	 * @param livroId the livroId to set
	 */
	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}
	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

	public Livro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	/**
	 * @return the dataLancamento
	 */
	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	/**
	 * @param dataLancamento the dataLancamento to set
	 */
	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void removeAutor(Autor pAutor) {
		System.out.println("Carrega a lista de autores e remove o autor de Livro");
		this.autores.remove(pAutor);
		
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	

}