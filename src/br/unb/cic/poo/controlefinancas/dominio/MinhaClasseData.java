package br.unb.cic.poo.controlefinancas.dominio;

/*
 * Esta classe tem por objetivo normalizar a criação de objetos
 * data, já que havia um problema em que alguns lugares era gerada
 * data local e em outros data GMT. Já com esta classe, todas as datas criadas
 * serão 3h da manhã em GMT.  */
public class MinhaClasseData {

	private java.util.Date innerDate;
	
	/**
	 * @return the innerDate
	 */
	public java.util.Date getInnerDate() {
		return innerDate;
	}

	/**
	 * @param innerDate the innerDate to set
	 */
	public void setInnerDate(java.util.Date innerDate) {
		this.innerDate = innerDate;
	}

	@SuppressWarnings("deprecation")
	public MinhaClasseData(int ano, int mes, int dia) {
		innerDate = new java.util.Date(java.util.Date.UTC(ano, mes, dia, 3, 0, 0));
	}

	public MinhaClasseData(long sinceEpoch)
	{
		innerDate = new java.util.Date(sinceEpoch);
	}		
	
	public long paraTime()
	{
		return innerDate.getTime();
	}
	
	public static MinhaClasseData agora()
	{
		return new MinhaClasseData(new java.util.Date().getTime());
	}
}
