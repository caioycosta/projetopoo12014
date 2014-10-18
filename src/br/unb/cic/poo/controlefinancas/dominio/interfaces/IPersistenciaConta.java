package br.unb.cic.poo.controlefinancas.dominio.interfaces;

import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

/**
 * @author CaioYuri
 * persistencia de contas
 */
public interface IPersistenciaConta {
	/**
	 * @param usr usu�rio atual
	 * @param p per�odo, controla quais os lan�amentos a obter.
	 * @return o conjunto de contas do usuario
	 * traz todas as contas do usuario
	 */
	public ConjuntoContas listarContas(Usuario usr, Periodo p);
}
