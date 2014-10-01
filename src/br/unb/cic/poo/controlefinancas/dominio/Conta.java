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
	private int total;
	private int id;
	private Collection<Lancamento> lancamentos;
	private Collection<Subconta> subcontas;
	
	/**
	 * cria novo objeto conta
	 */
	public Conta() {
		lancamentos = new ArrayList<Lancamento>();
	}

	
	/**
	 * @return nome da conta
	 */
	public abstract String getNome();

	/**
	 * @return o total o período
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param saldo o total para setar
	 */
	public void setTotal(int t) {
		this.total = t;
	}

	/**
	 * @return o id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id o id para setar
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return o lancamentos
	 */
	public Collection<Lancamento> getLancamentos() {
		return lancamentos;
	}

	/**
	 * @param lancamentos o lancamentos para setar
	 */
	public void setLancamentos(Collection<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}


	/**
	 * @return the subcontas
	 */
	public Collection<Subconta> getSubcontas() {
		return subcontas;
	}


	/**
	 * @param subcontas the subcontas to set
	 */
	public void setSubcontas(Collection<Subconta> subcontas) {
		this.subcontas = subcontas;
	}	
}
