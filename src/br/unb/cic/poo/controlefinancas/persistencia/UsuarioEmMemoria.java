/**
 * 
 */
package br.unb.cic.poo.controlefinancas.persistencia;

import java.util.HashMap;

import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

/**
 * @author CaioYuri
 * 
 */
public class UsuarioEmMemoria implements IPersistenciaUsuario {

	private static HashMap<Usuario, String> usuarioStore;
	private int ultimoId = 0;
	/**
	 * 
	 */
	static {
		usuarioStore = new HashMap<Usuario, String>();
	}

	@Override
	public void CadastrarUsuario(Usuario usr, String senha) {
		usr.setId(++ultimoId);
		usuarioStore.put(usr, senha);
	}

	@Override
	public Usuario loginUsuario(String login, String senha) {
		for (Usuario usr : usuarioStore.keySet()) {
			if (usr.getLogin().equals(login)
					&& usuarioStore.get(usr).equals(senha))
				return usr;
		}
		return null;
	}

	public static Usuario obterObjetoUsuario(Usuario u) {
		for (Usuario usr : usuarioStore.keySet()) {
			if (usr.getId() == u.getId())
				return usr;
		}
		return null;
	}
}
