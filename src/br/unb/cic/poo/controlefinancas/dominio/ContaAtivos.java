package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * Conta de Ativos
 */
public class ContaAtivos extends Conta {

	/**
	 * cria um novo objeto conta de ativo
	 */
	public ContaAtivos() {
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getMultiplicador()
	 */
	@Override
	public int getMultiplicador() {
		return 1;
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getNome()
	 */
	@Override
	public String getNome() {
		return "Ativos";
	}

}
