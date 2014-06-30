package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.fabrica.*;

/**
 * @author CaioYuri
 * classe base para modulos do sistema web
 */
public abstract class Rota {
	/**
	 * obtem a instancia da fabrica. troque aqui para 
	 * determinar qual implementacao o sistema utiliza
	 * @return instancia da fabrica
	 */
	protected Fabrica getFabrica()
	{
		return FabricaSQLite.getInstance();
	}
	
	/**
	 * @param nomeRota prefixo na url exemplo /usuario, /contas, /lancamentos.
	 * classes filhas usam esse metodo para inicializar rotas, 
	 * isto é, metodos que serao vinculados a determinada URL
	 */
	public abstract void DefinirSubRotas(String nomeRota);
}
