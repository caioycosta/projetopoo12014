package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.Usuario;

/**
 * @author CaioYuri
 * usado para enviar/receber dados da view de usuario
 */
public class UsuarioViewModel extends ViewModelBase {

	/**
	 * constroi um novo view model usuario
	 */
	public UsuarioViewModel() {

	}

	/**
	 * @param usr
	 *  constroi um novo view model usuario
	 */
	public UsuarioViewModel(Usuario usr) {
		this.usr = usr;
	}

	/**
	 * @param usr
	 * @param mensagem
	 * constroi um novo view model usuario
	 */
	public UsuarioViewModel(Usuario usr, String mensagem) {
		this.usr = usr;
		this.mensagem = mensagem;
	}

	Usuario usr;

	/**
	 * @return o usr
	 */
	public Usuario getUsr() {
		return usr;
	}

	/**
	 * @param usr o usr para setar
	 */
	public void setUsr(Usuario usr) {
		this.usr = usr;
	}

	/**
	 * @return o mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem  o mensagem para setar
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	String mensagem;
}
