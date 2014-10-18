package br.unb.cic.poo.controlefinancas.negocio;

import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaUsuario;

/**
 * @author CaioYuri
 * regras de negocio do usuario
 */
public class NegocioUsuario {
	IPersistenciaUsuario persistencia;
	
	
	/**
	 * @param persistencia
	 * cria um novo negocio usuario com a classe de persistencia fornecida
	 */
	public NegocioUsuario(IPersistenciaUsuario persistencia)
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * @param usr
	 * @param senha
	 * @return resultado da operacao
	 * cadastra um novo usuario
	 */
	public ResultadoOperacao CadastrarUsuario(Usuario usr, String senha)
	{
		if (usr.getNome() == null || usr.getNome().isEmpty())
			return new ResultadoOperacao(false, "Nome de usu�rio em branco");
		
		if (senha == null || senha.isEmpty())
			return new ResultadoOperacao(false, "Senha em branco");
		
		persistencia.CadastrarUsuario(usr, senha);
		return new ResultadoOperacao(true);
	}

	
	/**
	 * @param login
	 * @param senha
	 * @return resultado da operacao
	 * faz login do usuario. retorna null se dados invalidos.
	 */
	public ResultadoOperacaoG<Usuario> loginUsuario(String login,
			String senha) {
		Usuario resultado = persistencia.loginUsuario(login, senha);
		if (resultado != null)
		{
			return new ResultadoOperacaoG<Usuario>(true,resultado);
		} else {
			return new ResultadoOperacaoG<Usuario>(false, "login ou senha inv�lidos", null);
		}
	}
}
