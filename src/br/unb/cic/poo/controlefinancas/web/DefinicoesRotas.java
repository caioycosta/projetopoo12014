package br.unb.cic.poo.controlefinancas.web;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * @author CaioYuri
 * Carrega as defini��es de rotas para o sistema web.
 */
public class DefinicoesRotas {
	/**
	 * Carrega as defini��es de rotas para o sistema web.
	 * usado pra configurar os prefixos das urls
	 * @param args 
	 */
	public static void CarregarDefinicoes(String args)
	{
		Spark.staticFileRoute(args);
		new RotaUsuario().definirSubRotas("/usuario");
		new RotaConta().definirSubRotas("/contas");
		new RotaGrupoGasto().definirSubRotas("/grupogasto");
		new RotaLancamento().definirSubRotas("/lancamento");		
		new RotaRelatorio().definirSubRotas("/relatorios");
		new RotaPeriodo().definirSubRotas("/periodo");
		new RotaSubconta().definirSubRotas("/subconta");
		
		// rota padr�o
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				arg1.redirect("/contas");
				halt();
				return null;
			}
		});
	}
}
