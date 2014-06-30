package br.unb.cic.poo.controlefinancas.web;

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
		new RotaUsuario().DefinirSubRotas("/usuario");
		new RotaConta().DefinirSubRotas("/contas");
		new RotaGrupoGasto().DefinirSubRotas("/grupogasto");
		new RotaLancamento().DefinirSubRotas("/lancamento");				
	}
}
