/**
 * @author CaioYuri
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * 
 * Representa o conjunto (fixo) de contas de um
 * usuário, isto é, conta de ativos, conta de passivos,
 * conta de rendimentos e conta de despesas.
 */
public class ConjuntoContas {
	private ContaAtivos contaAtivos;
	private ContaDespesas contaDespesas;
	private ContaRendimentos contaRendimentos;
	private ContaPassivos contaPassivos;
	
	/**
	 * saldo das contas de despesa x receita.
	 */
	private int saldo;
	
	/**
	 * @return a conta de Ativos
	 */
	public ContaAtivos getContaAtivos() {
		return contaAtivos;
	}
	/**
	 * @param contaAtivos a contaAtivos  para setar
	 */
	public void setContaAtivos(ContaAtivos contaAtivos) {
		this.contaAtivos = contaAtivos;
	}
	/**
	 * @return o contaDespesas
	 */
	public ContaDespesas getContaDespesas() {
		return contaDespesas;
	}
	/**
	 * @param contaDespesas o contaDespesas para setar
	 */
	public void setContaDespesas(ContaDespesas contaDespesas) {
		this.contaDespesas = contaDespesas;
	}
	/**
	 * @return o contaRendimentos
	 */
	public ContaRendimentos getContaRendimentos() {
		return contaRendimentos;
	}
	/**
	 * @param contaRendimentos o contaRendimentos para setar
	 */
	public void setContaRendimentos(ContaRendimentos contaRendimentos) {
		this.contaRendimentos = contaRendimentos;
	}
	/**
	 * @return o contaPassivos
	 */
	public ContaPassivos getContaPassivos() {
		return contaPassivos;
	}
	/**
	 * @param contaPassivos o contaPassivos para setar
	 */
	public void setContaPassivos(ContaPassivos contaPassivos) {
		this.contaPassivos = contaPassivos;
	}
	/**
	 * @return the saldo
	 */
	public int getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
}
