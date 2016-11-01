package br.com.caelum.livraria.util;

public class RedirectView {

	
	private String wiewName;
	
	public RedirectView(String wiewString){
		this.wiewName = wiewString;
	}
	
	public String toString(){
		return wiewName + "?faces-redirect=true";
	}
}
