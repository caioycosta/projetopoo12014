/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 *
 */
public class ConjuntoContas {
	private ContaAtivos contaAtivos;
	private ContaDespesas contaDespesas;
	private ContaRendimentos contaRendimentos;
	private ContaPassivos contaPassivos;
	/**
	 * @return the contaAtivos
	 */
	public ContaAtivos getContaAtivos() {
		return contaAtivos;
	}
	/**
	 * @param contaAtivos the contaAtivos to set
	 */
	public void setContaAtivos(ContaAtivos contaAtivos) {
		this.contaAtivos = contaAtivos;
	}
	/**
	 * @return the contaDespesas
	 */
	public ContaDespesas getContaDespesas() {
		return contaDespesas;
	}
	/**
	 * @param contaDespesas the contaDespesas to set
	 */
	public void setContaDespesas(ContaDespesas contaDespesas) {
		this.contaDespesas = contaDespesas;
	}
	/**
	 * @return the contaRendimentos
	 */
	public ContaRendimentos getContaRendimentos() {
		return contaRendimentos;
	}
	/**
	 * @param contaRendimentos the contaRendimentos to set
	 */
	public void setContaRendimentos(ContaRendimentos contaRendimentos) {
		this.contaRendimentos = contaRendimentos;
	}
	/**
	 * @return the contaPassivos
	 */
	public ContaPassivos getContaPassivos() {
		return contaPassivos;
	}
	/**
	 * @param contaPassivos the contaPassivos to set
	 */
	public void setContaPassivos(ContaPassivos contaPassivos) {
		this.contaPassivos = contaPassivos;
	}
}
