package br.unb.cic.poo.controlefinancas.dominio;

public class GrupoGastoDespesa extends GrupoGasto {

	public GrupoGastoDespesa() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMultiplicador() {
		// TODO Auto-generated method stub
		
		return -1;
		
	}

	/* 
	 * @see br.unb.cic.poo.controlefinancas.dominio.GrupoGasto#getTipo()
	 */
	@Override
	public String getTipo() {
		
		return "Despesa";
	}

	@Override
	public String getTipoId() {
		// TODO Auto-generated method stub
		return "D";
	}

	
	
}
