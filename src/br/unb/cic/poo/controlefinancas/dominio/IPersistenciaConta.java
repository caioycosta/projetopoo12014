/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 *
 */
public interface IPersistenciaConta {
	public ConjuntoContas listarContas(Usuario usr);
	
	public void atualizarConta(Conta c, Usuario usr);
	
		
	
}
