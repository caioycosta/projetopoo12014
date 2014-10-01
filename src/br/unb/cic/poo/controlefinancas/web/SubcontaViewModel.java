package br.unb.cic.poo.controlefinancas.web;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.Subconta;

public class SubcontaViewModel extends ViewModelBase {

	private Collection<Subconta> lista;

	/**
	 * @return the lista
	 */
	public Collection<Subconta> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(Collection<Subconta> lista) {
		this.lista = lista;
	}

	/**
	 * @return the subconta
	 */
	public Subconta getSubconta() {
		return subconta;
	}

	/**
	 * @param subconta the subconta to set
	 */
	public void setSubconta(Subconta subconta) {
		this.subconta = subconta;
	}

	private Subconta subconta;

	
}
