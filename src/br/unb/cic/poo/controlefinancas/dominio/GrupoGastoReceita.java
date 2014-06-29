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

	/* 
	 * @see br.unb.cic.poo.controlefinancas.dominio.GrupoGasto#getTipo()
	 */
	@Override
	public String getTipo() {
		
		return "Receita";
	}

	@Override
	public String getTipoId() {
		// TODO Auto-generated method stub
		return "R";
	}

}
