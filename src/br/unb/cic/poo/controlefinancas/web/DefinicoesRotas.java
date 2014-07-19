package br.unb.cic.poo.controlefinancas.web;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * @author CaioYuri
 * Carrega as definições de rotas para o sistema web.
 */
public class DefinicoesRotas {
	/**
	 * Carrega as definições de rotas para o sistema web.
	 * usado pra configurar os prefixos das urls
	 */
	public static void CarregarDefinicoes()
	{
		Spark.staticFileRoute("/spark/staticcontent");
		new RotaUsuario().definirSubRotas("/usuario");
		new RotaConta().definirSubRotas("/contas");
		new RotaGrupoGasto().definirSubRotas("/grupogasto");
		new RotaLancamento().definirSubRotas("/lancamento");		
		new RotaRelatorio().definirSubRotas("/relatorios");
		new RotaPeriodo().definirSubRotas("/periodo");
		
		// rota padrão
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
