/**
 * 
 */
package br.unb.cic.poo.controlefinancas.negocio;

/**
 * @author CaioYuri
 * representa um resultado de operacao sem valor de retorno
 */
public class ResultadoOperacao {
	private String mensagem;
	private boolean sucesso;

	/**
	 * @param sucesso
	 * cria um novo obj resultado operacao com um booleano indicando sucesso/falha
	 */
	public ResultadoOperacao(boolean sucesso) {
		this.setSucesso(sucesso);
	}

	
	/**
	 * @param sucesso
	 * @param mensagem
	 * cria um novo obj resultado operacao com um booleano indicando sucesso/falha
	 * e uma mensagem de erro/informativa
	 */
	public ResultadoOperacao(boolean sucesso, String mensagem)
	{
		this(sucesso);
		this.setMensagem(mensagem);
	}

	/**
	 * @return o mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem o mensagem para setar
	 */
	private void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return o sucesso
	 */
	public boolean isSucesso() {
		return sucesso;
	}

	/**
	 * @param sucesso o sucesso para setar
	 */
	private void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
}
