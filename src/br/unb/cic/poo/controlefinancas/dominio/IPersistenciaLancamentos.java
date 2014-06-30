package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * persistencia de lancamentos
 */
public interface IPersistenciaLancamentos {
	/**
	 * @param l
	 * @param usr
	 * cria um  um lancamento e atualiza o saldo na conta
	 */
	public void criarLancamento(Lancamento l, Usuario usr);

	/**
	 * @param usr
	 * @param parseInt
	 * @return o lancamento ou null se nao encontrar
	 * busca um lancamento na base
	 */
	public Lancamento buscarLancamento(Usuario usr, int parseInt);

	/**
	 * @param l
	 * @param usr
	 * altera  um lancamento e atualiza o saldo na conta
	 */
	public void alterarLancamento(Lancamento l, Usuario usr);

	/**
	 * @param usr
	 * @param l
	 * exclui um lancamento e atualiza o saldo na conta
	 */
	public void excluirLancamento(Usuario usr, Lancamento l);
	
}
