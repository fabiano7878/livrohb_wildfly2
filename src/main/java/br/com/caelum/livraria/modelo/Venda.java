package br.com.caelum.livraria.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Venda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Livro livro;
	private int quantidade;
	/**
	 * @param livro
	 * @param quantidade
	 */
/*	
 * Com CDI é preciso de um construtor padrão ou não usar nenhum.
 * public Venda(Livro livro, int quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}*/
	/**
	 * @return the livro
	 */
	public Livro getLivro() {
		return livro;
	}
	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	
	
}
