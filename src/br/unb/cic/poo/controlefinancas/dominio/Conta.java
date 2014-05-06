/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.*;

/**
 * @author CaioYuri
 * 
 */
public abstract class Conta {
	private int saldo;
	private int id;
	private Collection<Lancamento> lancamentos;

	public Conta() {
		lancamentos = new ArrayList<Lancamento>();
	}

	/**
	 * Retorna um multiplicador que sera aplicado aos valores
	 * 
	 * @author CaioYuri
	 * @return multiplicador aplicado aos valores.
	 */
	public abstract int getMultiplicador();

	public abstract String getNome();

	/**
	 * @return the saldo
	 */
	public int getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lancamentos
	 */
	public Collection<Lancamento> getLancamentos() {
		return lancamentos;
	}

	/**
	 * @param lancamentos
	 *            the lancamentos to set
	 */
	public void setLancamentos(Collection<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
}
