package br.unb.cic.poo.controlefinancas.dominio;

import java.util.Date;

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
	private Date data;

	/**
	 * esta � a data de efetiva��o
	 * do lan�amento, n�o a data de sua cria��o.
	 * @return a data do lan�amento
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data a data para setar
	 */
	public void setData(Date data) {
		this.data = data;
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
}
