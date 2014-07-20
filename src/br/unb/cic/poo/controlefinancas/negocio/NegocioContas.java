package br.unb.cic.poo.controlefinancas.negocio;

import br.unb.cic.poo.controlefinancas.dominio.*;

/**
 * @author CaioYuri
 * regras do negocio para contas
 */
public class NegocioContas {
	private IPersistenciaConta persistencia;

	/**
	 * @param pers
	 * cria um novo obj negocio contas utilizado
	 * a implementacao de persistencia fornecida
	 */
	public NegocioContas(IPersistenciaConta pers) {
		this.persistencia = pers;
	}

	/**
	 * @param usr
	 * @return conjunto de contas do usuario
	 * lista as contas do usuário.
	 */
	public ConjuntoContas listarContas(Usuario usr, Periodo p) {
		return persistencia.listarContas(usr, p);
	}

}
