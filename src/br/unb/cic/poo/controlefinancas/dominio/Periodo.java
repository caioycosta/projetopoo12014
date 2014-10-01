/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * representa um periodo de contabilizacao (geralmente mensal)
 * @author CaioYuri
 * 
 */
public class Periodo {
	private int IdPeriodo;
	private MinhaClasseData DataInicio;
	private MinhaClasseData DataFim;
	/**
	 * @return the idPeriodo
	 */
	public int getIdPeriodo() {
		return IdPeriodo;
	}
	/**
	 * @param idPeriodo the idPeriodo to set
	 */
	public void setIdPeriodo(int idPeriodo) {
		IdPeriodo = idPeriodo;
	}
	/**
	 * @return the dataInicio
	 */
	public MinhaClasseData getDataInicio() {
		return DataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(MinhaClasseData dataInicio) {
		DataInicio = dataInicio;
	}
	/**
	 * @return the dataFim
	 */
	public MinhaClasseData getDataFim() {
		return DataFim;
	}
	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(MinhaClasseData dataFim) {
		DataFim = dataFim;
	}
}
