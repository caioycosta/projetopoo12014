package br.unb.cic.poo.controlefinancas.web;

/**
 * @author CaioYuri inicializacao do framework Web .
 */
public class SparkInit {

	/**
	 * @param args
	 *            realiza operacaos de inicializacao
	 */
	public static void main(String[] args) {
		if (args.length >= 1) {
			DefinicoesRotas.CarregarDefinicoes(args[0]);
		} else {
			DefinicoesRotas.CarregarDefinicoes("/spark/staticcontent");
		}
		DefinicoesFiltros.CarregarDefinicoes();

	}
}
