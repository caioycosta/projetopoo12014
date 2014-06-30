package br.unb.cic.poo.controlefinancas.web;

/**
 * @author CaioYuri inicializacao do framework Web .
 */
public class SparkInit {

	/**
	 * @param args
	 * realiza operacaos de inicializacao
	 */
	public static void main(String[] args) {
		
		DefinicoesRotas.CarregarDefinicoes();
		DefinicoesFiltros.CarregarDefinicoes();
		
	}
}
