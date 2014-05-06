/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 *
 */
public class GrupoGastoReceita extends GrupoGasto {

	/**
	 * 
	 */
	public GrupoGastoReceita() {
		
	}

	/** 
	 * @see br.unb.cic.poo.controlefinancas.dominio.GrupoGasto#getMultiplicador()
	 */
	@Override
	public int getMultiplicador() {		
		return 1;
	}

}
