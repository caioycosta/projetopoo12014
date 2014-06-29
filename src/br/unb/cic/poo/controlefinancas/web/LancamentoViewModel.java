package br.unb.cic.poo.controlefinancas.web;

import java.util.*;

import br.unb.cic.poo.controlefinancas.dominio.*;

public class LancamentoViewModel {
	Lancamento lancamento;
	String mensagem;
	int idGrupo;
	int idConta;
	/**
	 * @return the idConta
	 */
	public int getIdConta() {
		return idConta;
	}
	/**
	 * @param idConta the idConta to set
	 */
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	/**
	 * @return the idGrupo
	 */
	public int getIdGrupo() {
		return idGrupo;
	}
	/**
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}
	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	/**
	 * @return the lancamento
	 */
	public Lancamento getLancamento() {
		return lancamento;
	}
	/**
	 * @param lancamento the lancamento to set
	 */
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	/**
	 * @return the gruposGasto
	 */
	public Collection<GrupoGasto> getGruposGasto() {
		return GruposGasto;
	}
	/**
	 * @param gruposGasto the gruposGasto to set
	 */
	public void setGruposGasto(Collection<GrupoGasto> gruposGasto) {
		GruposGasto = gruposGasto;
	}
	Collection<GrupoGasto> GruposGasto;
	Collection<Conta> contas;
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
}
