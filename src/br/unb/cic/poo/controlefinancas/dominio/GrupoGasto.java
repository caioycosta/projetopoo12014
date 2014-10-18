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

	int id;

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
	 * @return o id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return representacao textual do tipo do grupo de gasto 
	 */
	public abstract String getTipo();

	/**
	 * @return representacao textual do tipo do grupo de gasto 
	 */
	public abstract String getTipoId();

	/**
	 * @param contas  o contas para setar
	 */
	public void setContas(Collection<Conta> contas) {
		this.contas = contas;
	}

	
	/**
	 * @param id o id para setar
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param nome o nome para setar
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		GrupoGasto other = (GrupoGasto) obj;
		if (id != other.id)
			return false;
		
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		
		return true;
	}
}
