/**
 * @author CaioYuri
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author CaioYuri
 * representa um grupo de gasto
 */
public abstract class GrupoGasto {

	private Collection<Conta> contas;

	/**
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome o nome para setar
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	private String nome;

	
	/**
	 * cria um novo obj grupo de gasto
	 */
	public GrupoGasto() {
		this.contas = new ArrayList<Conta>();
	}

	/**
	 * @return o contas
	 */
	public Collection<Conta> getContas() {
		return contas;
	}

	/**
	 * @param contas  o contas para setar
	 */
	public void setContas(Collection<Conta> contas) {
		this.contas = contas;
	}

	int id;

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
	 * @return representacao textual do tipo do grupo de gasto 
	 */
	public abstract String getTipo();

	/**
	 * @return representacao textual do tipo do grupo de gasto 
	 */
	public abstract String getTipoId();
}
