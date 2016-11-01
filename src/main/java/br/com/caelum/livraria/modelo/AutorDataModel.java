package br.com.caelum.livraria.modelo;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.AutorDAO;

@Named
@ViewScoped
public class AutorDataModel extends LazyDataModel<Autor> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AutorDAO dao; 

	
	@PostConstruct
	public void init(){
		super.setRowCount(dao.quantidadeDeElementos());
	}
	
	public List<Autor> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String nome = (String) filtros.get("nome");
		return dao.listaTodosPaginada(inicio, quantidade, "nome", nome);

	}
	
}
