/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 *
 */
public class Lancamento {
	private int valor;
	private int descricao;
	private Conta conta;
	private GrupoGasto grupo;
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
	/**
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}
	/**
	 * @return the descricao
	 */
	public int getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(int descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the conta
	 */
	public Conta getConta() {
		return conta;
	}
	/**
	 * @param conta the conta to set
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	/**
	 * @return the grupo
	 */
	public GrupoGasto getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(GrupoGasto grupo) {
		this.grupo = grupo;
	}
}
