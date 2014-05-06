/**
 * 
 */
package br.unb.cic.poo.controlefinancas.negocio;

/**
 * @author CaioYuri
 *
 */
public class ResultadoOperacaoG<T> {
	private String mensagem;
	private boolean sucesso;
	private T valor;
	/**
	 * 
	 */
	public ResultadoOperacaoG(boolean sucesso, T valor) {
		this.setSucesso(sucesso);
		this.setValor(valor);
	}

	public ResultadoOperacaoG(boolean sucesso, String mensagem, T valor)
	{
		this(sucesso,valor);
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

	/**
	 * @return the valor
	 */
	public T getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	private void setValor(T valor) {
		this.valor = valor;
	}
}
