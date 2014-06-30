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
	private int saldo;
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
	 * @author CaioYuri
	 * @return multiplicador aplicado aos valores.
	 */
	public abstract int getMultiplicador();

	/**
	 * @return nome da conta
	 */
	public abstract String getNome();

	/**
	 * @return o saldo
	 */
	public int getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo o saldo para setar
	 */
	public void setSaldo(int saldo) {
		this.saldo = saldo;
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
	 * executa o calculo do saldo levando em conta o multiplicador
	 * @param valor
	 */
	public void creditar(int valor) {
		setSaldo(getSaldo() + valor * getMultiplicador());
	}

	/**
	 * executa o calculo do saldo levando em conta o multiplicador
	 * @param valor
	 */
	public void debitar(int valor) {
		setSaldo(getSaldo() - valor * getMultiplicador());
	}
}
