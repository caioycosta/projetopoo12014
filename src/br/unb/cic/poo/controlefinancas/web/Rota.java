package br.unb.cic.poo.controlefinancas.web;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
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
	
	public void definirSubRotas(final String nomeRota)
	{
		Spark.get(new Route(nomeRota) {
			@Override
			public Object handle(Request rq, Response rp) {
				rp.redirect(nomeRota + getRotaPadrao());
				halt();
				return null;
			}
		});
		
		definirSubRotasProtected(nomeRota);
	}
	
	protected abstract String getRotaPadrao();

	/**
	 * @param nomeRota prefixo na url exemplo /usuario, /contas, /lancamentos.
	 * classes filhas usam esse metodo para inicializar rotas, 
	 * isto é, metodos que serao vinculados a determinada URL
	 */
	protected abstract void definirSubRotasProtected(String nomeRota);
}
