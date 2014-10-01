package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;

/**
 * @author CaioYuri
 * view model base utilizado para dados comuns a todas as views
 */
public class ViewModelBase {
	String Titulo;
	String Descricao;
	String InfoDescricao;
	String CategoriaAtual;
	ArrayList<String> ListaCategorias;
	ArrayList<String> ListaPaginas;
	
	public ViewModelBase()
	{
		ListaCategorias = new ArrayList<String>();
		ListaCategorias.add("Contas");
		ListaCategorias.add("Usuario");
		ListaCategorias.add("GrupoGasto");
		ListaCategorias.add("Lancamento");
		ListaCategorias.add("Relatorios");
		ListaCategorias.add("Periodo");
		ListaCategorias.add("Subconta");
	}
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return Titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricaoView() {
		return Descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricaoView(String descricao) {
		Descricao = descricao;
	}

	/**
	 * @return the infoDescricao
	 */
	public String getInfo() {
		return InfoDescricao;
	}
	/**
	 * @param infoDescricao the infoDescricao to set
	 */
	public void setInfo(String infoDescricao) {
		InfoDescricao = infoDescricao;
	}
	/**
	 * @return the categoriaAtual
	 */
	public String getCategoriaAtual() {
		return CategoriaAtual;
	}
	/**
	 * @param categoriaAtual the categoriaAtual to set
	 */
	public void setCategoriaAtual(String categoriaAtual) {
		CategoriaAtual = categoriaAtual;
	}
	
	/**
	 * @return the listaCategorias
	 */
	public ArrayList<String> getListaCategorias() {
		return ListaCategorias;
	}
	/**
	 * @return the listaPaginas
	 */
	public ArrayList<String> getListaPaginas() {
		return ListaPaginas;
	}
	/**
	 * @param listaPaginas the listaPaginas to set
	 */
	public void setListaPaginas(ArrayList<String> listaPaginas) {
		ListaPaginas = listaPaginas;
	}
}
