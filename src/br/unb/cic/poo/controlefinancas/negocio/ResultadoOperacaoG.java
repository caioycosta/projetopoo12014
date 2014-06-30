package br.unb.cic.poo.controlefinancas.negocio;

/**
 * @author CaioYuri
 * representa um resultado de operacao, com mensagem de erro caso tenha havido falha
 */
public class ResultadoOperacaoG<T> {
	private String mensagem;
	private boolean sucesso;
	private T valor;


	
	/**
	 * @param sucesso
	 * @param valor
	 * cria um novo obj resultadoOperacao com um valor de retorno
	 */
	public ResultadoOperacaoG(boolean sucesso, T valor) {
		this.setSucesso(sucesso);
		this.setValor(valor);
	}

	/**
	 * @param sucesso
	 * @param mensagem
	 * @param valor
	 * cria um novo obj resultadooperacao com um valor de retorno e uma mensagem
	 */
	public ResultadoOperacaoG(boolean sucesso, String mensagem, T valor)
	{
		this(sucesso,valor);
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

	/**
	 * @return o valor
	 */
	public T getValor() {
		return valor;
	}

	/**
	 * @param valor o valor para setar
	 */
	private void setValor(T valor) {
		this.valor = valor;
	}
}
