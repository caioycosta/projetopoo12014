/**
 * 
 */
package br.unb.cic.poo.controlefinancas.web;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.Periodo;

/**
 * @author CaioYuri
 *
 */
public class PeriodoViewModel extends ViewModelBase {
	private Periodo periodo;
	private Collection<Periodo> lista;
	
	
	/**
	 * @return the periodo
	 */
	public Periodo getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the lista
	 */
	public Collection<Periodo> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(Collection<Periodo> lista) {
		this.lista = lista;
	}
}
