/**
 * 
 */
package br.unb.cic.poo.controlefinancas.negocio;

import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.persistencia.UsuarioEmMemoria;

/**
 * @author CaioYuri
 *
 */
public class NegocioUsuario {
	IPersistenciaUsuario persistencia;
	/**
	 * 
	 */
	public NegocioUsuario() {
		persistencia = new UsuarioEmMemoria();
	}
	
	public NegocioUsuario(IPersistenciaUsuario persistencia)
	{
		this.persistencia = persistencia;
	}
	
	public ResultadoOperacao CadastrarUsuario(Usuario usr, String senha)
	{
		if (usr.getNome() == null || usr.getNome().isEmpty())
			return new ResultadoOperacao(false, "Nome de usuário em branco");
		
		if (senha == null || senha.isEmpty())
			return new ResultadoOperacao(false, "Senha em branco");
		
		persistencia.CadastrarUsuario(usr, senha);
		return new ResultadoOperacao(true);
	}

	public ResultadoOperacaoG<Usuario> loginUsuario(String login,
			String senha) {
		Usuario resultado = persistencia.loginUsuario(login, senha);
		if (resultado != null)
		{
			return new ResultadoOperacaoG<Usuario>(true,resultado);
		} else {
			return new ResultadoOperacaoG<Usuario>(false, "login ou senha inválidos", null);
		}
	}
}
