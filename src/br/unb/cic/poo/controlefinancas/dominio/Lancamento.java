package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * representa um lancamento
 */
public class Lancamento {
	private int valor;
	private String descricao;
	private Conta conta;
	private GrupoGasto grupo;
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
	 * @return o valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param valor  o valor para setar
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	/**
	 * @return o descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param string o descricao para setar
	 */
	public void setDescricao(String string) {
		this.descricao = string;
	}

	/**
	 * @return o conta
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * @param conta o conta para setar
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * @return o grupo
	 */
	public GrupoGasto getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo o grupo para setar
	 */
	public void setGrupo(GrupoGasto grupo) {
		this.grupo = grupo;
	}

	/**
	 * credita o valor deste lancamento na conta
	 * atribuida a este lancamento
	 */
	public void creditar() {
		getConta().creditar(getValor() * getGrupo().getMultiplicador());
	}

	/**
	 * debita o valor deste lancamento na conta
	 * atribuida a este lancamento
	 */
	public void debitar() {
		debitar(getValor());
	}
	
	/**
	 * @param valor valor a debitar
	 *  debita o valor fornecido na conta
	 * atribuida a este lancamento
	 */
	public void debitar(int valor) {
		getConta().debitar(valor * getGrupo().getMultiplicador());
	}
}
