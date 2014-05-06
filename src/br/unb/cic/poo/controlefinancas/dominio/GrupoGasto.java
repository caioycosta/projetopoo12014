/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.Collection;

/**
 * @author CaioYuri
 *
 */
public abstract class GrupoGasto {
	
	private Collection<Conta> contas;
	
	/**
	 * @author CaioYuri
	 * Um multiplicador aplicado aos lançamentos realizados neste grupo.
	 * @return o multiplicador, geralmente 1 ou -1.
	 */
	
	
	public abstract int getMultiplicador();
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	private String nome;
	
	public GrupoGasto()
	{
	}
	/**
	 * @return the contas
	 */
	public Collection<Conta> getContas() {
		return contas;
	}
	/**
	 * @param contas the contas to set
	 */
	public void setContas(Collection<Conta> contas) {
		this.contas = contas;
	}
	
	int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
