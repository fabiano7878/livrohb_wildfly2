package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.LivroDAO;

@Named
@ViewScoped
public class LivroDataModel extends LazyDataModel<Livro> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDAO dao; 
	
	@PostConstruct
	public void init(){
		super.setRowCount(dao.quantidadeDeElementos());
	}
	
	
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String titulo = (String) filtros.get("titulo");
		return dao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);

	}
}
