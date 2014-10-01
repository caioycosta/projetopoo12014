/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * Representa uma sub-conta (por exemplo, para
 * gerenciar empréstimos específicos)
 * @author CaioYuri
 *
 */
public class Subconta extends Conta {
    private int saldoAcumulado;
	
    private MinhaClasseData dataInicio;
    private MinhaClasseData dataFim;
	
	/**
	 * @return a dataInicio (a partir de quando a conta sera visivel)
	 */
	public MinhaClasseData getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set  (a partir de quando a conta sera visivel)
	 */
	public void setDataInicio(MinhaClasseData dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim (até quando a conta será visível)
	 */
	public MinhaClasseData getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set (até quando a conta será visível)
	 */
	public void setDataFim(MinhaClasseData dataFim) {
		this.dataFim = dataFim;
	}

	/**
	 * @return o saldo Acumulado da sub-conta.
	 */
	public int getSaldoAcumulado() {
		return saldoAcumulado;
	}

	/**
	 * @param saldoAcumulado o saldo Acumulado para setar na sub-conta.
	 */
	public void setSaldoAcumulado(int saldoAcumulado) {
		this.saldoAcumulado = saldoAcumulado;
	}

	/**
	 * 
	 */
	public Subconta() {
		// TODO Auto-generated constructor stub
	}

	/** Nome da sub-conta.
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getNome()
	 */
	@Override
	public String getNome() {
		return nome;
	}

	private String nome;
	
	/** Nome da sub-conta.
	 * @see br.unb.cic.poo.controlefinancas.dominio.Conta#getNome()
	 */
	public void setNome(String n) {
		nome = n;
	}
}
