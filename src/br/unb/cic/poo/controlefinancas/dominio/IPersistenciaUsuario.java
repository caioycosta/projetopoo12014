package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * persistencia de usuario
 */
public interface IPersistenciaUsuario {
	/**
	 * @param usr
	 * @param senha
	 * cadastra um novo usuario
	 */
	public void CadastrarUsuario(Usuario usr, String senha);
	
	/**
	 * @param login
	 * @param senha
	 * @return null se login invalido, o obj usuario se OK
	 * realiza uma tentativa de login
	 */
	public Usuario loginUsuario(String login,
			String senha);
}
