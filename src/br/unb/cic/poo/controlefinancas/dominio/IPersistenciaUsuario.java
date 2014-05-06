/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 *
 */
public interface IPersistenciaUsuario {
	public void CadastrarUsuario(Usuario usr, String senha);
	
	public Usuario loginUsuario(String login,
			String senha);
}
