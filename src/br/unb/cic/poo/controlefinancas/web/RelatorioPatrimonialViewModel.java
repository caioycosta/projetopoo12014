package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.RelatorioPatrimonial;

public class RelatorioPatrimonialViewModel extends ViewModelBase {
	private RelatorioPatrimonial rp;

	public RelatorioPatrimonialViewModel(RelatorioPatrimonial rp)
	{
		this.setRp(rp);
	}

	/**
	 * @return the rp
	 */
	public RelatorioPatrimonial getRp() {
		return rp;
	}

	/**
	 * @param rp the rp to set
	 */
	public void setRp(RelatorioPatrimonial rp) {
		this.rp = rp;
	}
}
