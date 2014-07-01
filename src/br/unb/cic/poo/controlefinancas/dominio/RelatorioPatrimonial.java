package br.unb.cic.poo.controlefinancas.dominio;

public class RelatorioPatrimonial {
	int saldoAtivos;
	int saldoPassivos;

	/**
	 * @return the saldoAtivos
	 */
	public int getSaldoAtivos() {
		return saldoAtivos;
	}
	/**
	 * @param saldoAtivos the saldoAtivos to set
	 */
	public void setSaldoAtivos(int saldoAtivos) {
		this.saldoAtivos = saldoAtivos;
	}
	/**
	 * @return the saldoPassivos
	 */
	public int getSaldoPassivos() {
		return saldoPassivos;
	}
	/**
	 * @param saldoPassivos the saldoPassivos to set
	 */
	public void setSaldoPassivos(int saldoPassivos) {
		this.saldoPassivos = saldoPassivos;
	}
	/**
	 * @return the patrimonioLiquido
	 */
	public int getPatrimonioLiquido() {
		return getSaldoAtivos() - getSaldoPassivos();
	}
}
