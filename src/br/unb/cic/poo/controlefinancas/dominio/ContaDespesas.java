package br.unb.cic.poo.controlefinancas.dominio;


/**
 * @author CaioYuri
 * representa uma conta de despesas
 */
public class ContaDespesas extends Conta {

	/**
	 * cria um novo objeto conta de despesas
	 */
	public ContaDespesas() {
		
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
		return "Despesas";
	}

}
