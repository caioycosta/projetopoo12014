package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * persistencia de contas
 */
public interface IPersistenciaConta {
	/**
	 * @param usr usuário atual
	 * @param p período, controla quais os lançamentos a obter.
	 * @return o conjunto de contas do usuario
	 * traz todas as contas do usuario
	 */
	public ConjuntoContas listarContas(Usuario usr, Periodo p);
}
