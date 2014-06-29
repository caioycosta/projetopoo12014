/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 *
 */
public interface IPersistenciaLancamentos {
	public void criarLancamento(Lancamento l, Usuario usr);

	public Lancamento buscarLancamento(Usuario usr, int parseInt);

	public void alterarLancamento(Lancamento l, Usuario usr);
}
