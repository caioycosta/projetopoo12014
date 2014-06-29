package br.unb.cic.poo.controlefinancas.web;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;

public class ListaGrupoGastoViewModel {
	private Collection<GrupoGasto> gruposGasto;

	/**
	 * @return the gruposGasto
	 */
	public Collection<GrupoGasto> getGruposGasto() {
		return gruposGasto;
	}

	/**
	 * @param gruposGasto the gruposGasto to set
	 */
	public void setGruposGasto(Collection<GrupoGasto> gruposGasto) {
		this.gruposGasto = gruposGasto;
	}
}
