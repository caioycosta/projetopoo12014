/**
 * 
 */
package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.Usuario;

/**
 * @author CaioYuri
 * 
 */
public class UsuarioViewModel {
	/**
	 * @param usr
	 * @param mensagem
	 */

	public UsuarioViewModel() {

	}

	public UsuarioViewModel(Usuario usr) {
		this.usr = usr;
	}

	public UsuarioViewModel(Usuario usr, String mensagem) {
		this.usr = usr;
		this.mensagem = mensagem;
	}

	Usuario usr;

	/**
	 * @return the usr
	 */
	public Usuario getUsr() {
		return usr;
	}

	/**
	 * @param usr
	 *            the usr to set
	 */
	public void setUsr(Usuario usr) {
		this.usr = usr;
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem
	 *            the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	String mensagem;
}
