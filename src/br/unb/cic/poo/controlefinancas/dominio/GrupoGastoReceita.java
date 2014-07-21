/**
 * @author CaioYuri
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * representa um grupo de gastos tipo receita
 */
public class GrupoGastoReceita extends GrupoGasto {

	/**
	 * cria um novo objeto grupo de gasto receita
	 */
	public GrupoGastoReceita() {
		
	}


	/** 
	 * @see br.unb.cic.poo.controlefinancas.dominio.GrupoGasto#getTipo()
	 */
	@Override
	public String getTipo() {
		
		return "Receita";
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.GrupoGasto#getTipoId()
	 */
	@Override
	public String getTipoId() {
		
		return "R";
	}

}
