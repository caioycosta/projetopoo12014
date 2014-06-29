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
	private String descricao;
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
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param string the descricao to set
	 */
	public void setDescricao(String string) {
		this.descricao = string;
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
