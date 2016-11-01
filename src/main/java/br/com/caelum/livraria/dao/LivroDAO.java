package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.interceptors.Log;
import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDAO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	private DAO<Livro> dao;
	
	@PostConstruct
	public void init(){
		this.dao = new DAO<Livro>(em, Livro.class);
	}

	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#adiciona(java.lang.Object)
	 */
	@Log
	public void adiciona(Livro t) {
		dao.adiciona(t);
	}

	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#remove(java.lang.Object)
	 */
	@Log
	public void remove(Livro t) {
		dao.remove(t);
	}

	/**
	 * @param t
	 * @see br.com.caelum.livraria.dao.DAO#atualiza(java.lang.Object)
	 */
	@Log
	public void atualiza(Livro t) {
		dao.atualiza(t);
	}

	/**
	 * @param id
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#buscaPorId(java.lang.Integer)
	 */
	@Log
	public Livro buscaPorId(Integer id) {
		return dao.buscaPorId(id);
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
	public List<Livro> listaTodosPaginada(int firstResult, int maxResults,
			String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}

	/**
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#quantidadeDeElementos()
	 */
	@Log
	public int quantidadeDeElementos() {
		System.out.println("LivroDAO: "+dao);
		return dao.quantidadeDeElementos();
	}

	/**
	 * @return
	 * @see br.com.caelum.livraria.dao.DAO#listaTodos()
	 */
	@Log
	public List<Livro> listaTodos() {
		return dao.listaTodos();
	}
	
	
}
