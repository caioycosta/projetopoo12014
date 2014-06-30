package br.unb.cic.poo.controlefinancas.web;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;

/**
 * @author CaioYuri
 * usado para enviar/receber dados da view de grupos de gasto
 */
public class ListaGrupoGastoViewModel {
	private Collection<GrupoGasto> gruposGasto;

	/**
	 * @return o gruposGasto
	 */
	public Collection<GrupoGasto> getGruposGasto() {
		return gruposGasto;
	}

	/**
	 * @param gruposGasto o gruposGasto para setar
	 */
	public void setGruposGasto(Collection<GrupoGasto> gruposGasto) {
		this.gruposGasto = gruposGasto;
	}
}
