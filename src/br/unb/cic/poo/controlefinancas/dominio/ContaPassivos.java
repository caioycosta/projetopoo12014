package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * representa uma conta de passivos
 */
public class ContaPassivos extends Conta {

	private int saldoAcumulado;
	/**
	 * @return the saldoAcumulado
	 */
	public int getSaldoAcumulado() {
		return saldoAcumulado;
	}

	/**
	 * @param saldoAcumulado the saldoAcumulado to set
	 */
	public void setSaldoAcumulado(int saldoAcumulado) {
		this.saldoAcumulado = saldoAcumulado;
	}

	/**
	 * cria um novo objeto conta de passivos
	 */
	public ContaPassivos() {
		
	}


	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getNome()
	 */
	@Override
	public String getNome() {
		return "Passivos";
	}

}
