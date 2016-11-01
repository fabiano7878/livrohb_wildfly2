package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.interceptors.Log;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	private DAO<Autor> dao;
	
	@PostConstruct
	public void init(){
		this.dao = new DAO<Autor>(this.em, Autor.class);
	}


	/**
	 * @param autor
	 * @see br.com.caelum.livraria.dao.DAO#adiciona(java.lang.Object)
	 */
	@Log
	public void adiciona(Autor autor) {
		dao.adiciona(autor);
	}


	/**
	 * @param autor
	 * @see br.com.caelum.livraria.dao.DAO#remove(java.lang.Object)
	 */
	@Log
	public void remove(Autor autor) {
		dao.remove(autor);
	}


	/**
	 * @param autor
	 * @see br.com.caelum.livraria.dao.DAO#atualiza(java.lang.Object)
	 */
	@Log
	public void atualiza(Autor autor) {
		dao.atualiza(autor);
	}


	/**
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#listaTodos()
	 */
	@Log
	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}


	/**
	 * @param autorId
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#buscaPorId(java.lang.Integer)
	 */
	@Log
	public Autor buscaPorId(Integer autorId) {
		return dao.buscaPorId(autorId);
	}


	/**
	 * @param firstResult
	 * @param maxResults
	 * @param coluna
	 * @param valor
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#listaTodosPaginada(int, int, java.lang.String, java.lang.String)
	 */
	@Log
	public List<Autor> listaTodosPaginada(int firstResult, int maxResults,
			String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}


	/**
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#quantidadeDeElementos()
	 */
	@Log
	public int quantidadeDeElementos() {
		return dao.quantidadeDeElementos();
	}

	
	
	
	
}
