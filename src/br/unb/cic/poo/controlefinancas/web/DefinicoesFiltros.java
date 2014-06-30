package br.unb.cic.poo.controlefinancas.web;

import spark.Spark;

/**
 * @author CaioYuri
 * carrega definicioes de filtros
 */
public class DefinicoesFiltros {

	/**
	 * usado para configurar quais filtros serao carregados.
	 */
	public static void CarregarDefinicoes() {
		Spark.before( new FiltroAutenticacao());
	}

}
