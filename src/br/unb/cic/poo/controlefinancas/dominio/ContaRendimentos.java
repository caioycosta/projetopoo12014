/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * representa uma conta de rendimentos
 */
public class ContaRendimentos extends Conta {

	/**
	 * cria um novo objeto conta de rendimentos
	 */
	public ContaRendimentos() {
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getNome()
	 */
	@Override
	public String getNome() {
		return "Rendimentos";
	}

}
