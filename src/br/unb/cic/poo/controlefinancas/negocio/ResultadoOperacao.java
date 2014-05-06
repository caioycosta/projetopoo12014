/**
 * 
 */
package br.unb.cic.poo.controlefinancas.negocio;

/**
 * @author CaioYuri
 *
 */
public class ResultadoOperacao {
	private String mensagem;
	private boolean sucesso;
	/**
	 * 
	 */
	public ResultadoOperacao(boolean sucesso) {
		this.setSucesso(sucesso);
	}

	public ResultadoOperacao(boolean sucesso, String mensagem)
	{
		this(sucesso);
		this.setMensagem(mensagem);
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	private void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the sucesso
	 */
	public boolean isSucesso() {
		return sucesso;
	}

	/**
	 * @param sucesso the sucesso to set
	 */
	private void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
}
