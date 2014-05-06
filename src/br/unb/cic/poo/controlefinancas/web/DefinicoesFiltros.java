/**
 * 
 */
package br.unb.cic.poo.controlefinancas.web;

import spark.Spark;

/**
 * @author CaioYuri
 *
 */
public class DefinicoesFiltros {

	public static void CarregarDefinicoes() {
		Spark.before( new FiltroAutenticacao());
	}

}
