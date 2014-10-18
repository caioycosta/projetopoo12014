package br.unb.cic.poo.controlefinancas.persistencia;

import java.util.HashMap;

import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaUsuario;

/**
 * @author CaioYuri
 * Implementacao concreta de persistencia em memoria
 */
public class UsuarioEmMemoria implements IPersistenciaUsuario {

	private static HashMap<Usuario, String> usuarioStore;
	private static int ultimoId = 0;


	static {
		usuarioStore = new HashMap<Usuario, String>();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaUsuario#CadastrarUsuario(br.unb.cic.poo.controlefinancas.dominio.Usuario, java.lang.String)
	 */
	@Override
	public void CadastrarUsuario(Usuario usr, String senha) {
		usr.setId(++ultimoId);
		usuarioStore.put(usr, senha);
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaUsuario#loginUsuario(java.lang.String, java.lang.String)
	 */
	@Override
	public Usuario loginUsuario(String login, String senha) {
		for (Usuario usr : usuarioStore.keySet()) {
			if (usr.getLogin().equals(login)
					&& usuarioStore.get(usr).equals(senha))
				return usr;
		}
		return null;
	}

	/**
	 * @param u
	 * @return objeto usuario
	 * dado um objeto so com o ID retorna o usuario completo
	 */
	public static Usuario obterObjetoUsuario(Usuario u) {
		for (Usuario usr : usuarioStore.keySet()) {
			if (usr.getId() == u.getId())
				return usr;
		}
		return null;
	}
}
