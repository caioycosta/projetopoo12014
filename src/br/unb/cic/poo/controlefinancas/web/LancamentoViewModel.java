package br.unb.cic.poo.controlefinancas.web;

import java.util.*;

import br.unb.cic.poo.controlefinancas.dominio.*;

/**
 * @author CaioYuri
 * usado para enviar/receber dados da view de grupos de lancamento
 */
public class LancamentoViewModel {
	Lancamento lancamento;
	String mensagem;
	int idGrupo;
	int idConta;
	/**
	 * @return o idConta
	 */
	public int getIdConta() {
		return idConta;
	}
	/**
	 * @param idConta o idConta para setar
	 */
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	/**
	 * @return o idGrupo
	 */
	public int getIdGrupo() {
		return idGrupo;
	}
	/**
	 * @param idGrupo o idGrupo para setar
	 */
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	/**
	 * @return o mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}
	/**
	 * @param mensagem o mensagem para setar
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	/**
	 * @return o lancamento
	 */
	public Lancamento getLancamento() {
		return lancamento;
	}
	/**
	 * @param lancamento o lancamento para setar
	 */
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	/**
	 * @return o gruposGasto
	 */
	public Collection<GrupoGasto> getGruposGasto() {
		return GruposGasto;
	}
	/**
	 * @param gruposGasto o gruposGasto para setar
	 */
	public void setGruposGasto(Collection<GrupoGasto> gruposGasto) {
		GruposGasto = gruposGasto;
	}
	Collection<GrupoGasto> GruposGasto;
	Collection<Conta> contas;
	/**
	 * @return o contas
	 */
	public Collection<Conta> getContas() {
		return contas;
	}
	/**
	 * @param contas o contas para setar
	 */
	public void setContas(Collection<Conta> contas) {
		this.contas = contas;
	}
}
