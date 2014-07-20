/**
 * @author CaioYuri
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.*;

/**
 * @author CaioYuri
 * representa uma conta, de qualquer tipo
 */
public abstract class Conta {
	private int total;
	private int id;
	private Collection<Lancamento> lancamentos;

	
	/**
	 * cria novo objeto conta
	 */
	public Conta() {
		lancamentos = new ArrayList<Lancamento>();
	}

	/**
	 * Retorna um multiplicador que sera aplicado aos valores
	 * 
	 * 
	 * @return multiplicador aplicado aos valores.
	 */
	public abstract int getMultiplicador();

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
}
