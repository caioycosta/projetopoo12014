package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * representa uma conta de passivos
 */
public class ContaPassivos extends Conta {

	/**
	 * cria um novo objeto conta de passivos
	 */
	public ContaPassivos() {
		
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getMultiplicador()
	 */
	@Override
	public int getMultiplicador() {
		return -1;
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getNome()
	 */
	@Override
	public String getNome() {
		return "Passivos";
	}

}
