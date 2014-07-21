package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * representa um grupo de gasto tipo despesa
 */
public class GrupoGastoDespesa extends GrupoGasto {

	/**
	 * cria um novo objeto grupo de gasto de despesas
	 */
	public GrupoGastoDespesa() {
	
	}


	/** 
	 * @see br.unb.cic.poo.controlefinancas.dominio.GrupoGasto#getTipo()
	 */
	@Override
	public String getTipo() {
		
		return "Despesa";
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.GrupoGasto#getTipoId()
	 */
	@Override
	public String getTipoId() {
	
		return "D";
	}

	
	
}
