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
	private java.util.Date DataInicio;
	private java.util.Date DataFim;
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
	public java.util.Date getDataInicio() {
		return DataInicio;
	}
	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(java.util.Date dataInicio) {
		DataInicio = dataInicio;
	}
	/**
	 * @return the dataFim
	 */
	public java.util.Date getDataFim() {
		return DataFim;
	}
	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(java.util.Date dataFim) {
		DataFim = dataFim;
	}
}
