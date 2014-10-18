/**
 * @author CaioYuri
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author CaioYuri
 * representa uma conta, de qualquer tipo
 */
public abstract class Conta {
	private int id;
	private Collection<Lancamento> lancamentos;
	private Collection<Subconta> subcontas;
	private int total;
	
	/**
	 * cria novo objeto conta
	 */
	public Conta() {
		lancamentos = new ArrayList<Lancamento>();
	}

	
	/**
	 * @return o id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return o lancamentos
	 */
	public Collection<Lancamento> getLancamentos() {
		return lancamentos;
	}

	/**
	 * @return nome da conta
	 */
	public abstract String getNome();

	/**
	 * @return the subcontas
	 */
	public Collection<Subconta> getSubcontas() {
		return subcontas;
	}

	/**
	 * @return o total o per�odo
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param id o id para setar
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param lancamentos o lancamentos para setar
	 */
	public void setLancamentos(Collection<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}


	/**
	 * @param subcontas the subcontas to set
	 */
	public void setSubcontas(Collection<Subconta> subcontas) {
		this.subcontas = subcontas;
	}


	/**
	 * @param saldo o total para setar
	 */
	public void setTotal(int t) {
		this.total = t;
	}	
}
