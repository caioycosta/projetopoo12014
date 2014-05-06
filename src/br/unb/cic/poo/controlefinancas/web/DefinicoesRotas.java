/**
 * 
 */
package br.unb.cic.poo.controlefinancas.web;

/**
 * @author CaioYuri
 *
 */
public class DefinicoesRotas {
	public static void CarregarDefinicoes()
	{
		RotaUsuario.DefinirSubRotas("/usuario");
		RotaConta.DefinirSubRotas("/contas");
		RotaGrupoGasto.DefinirSubRotas("/grupogasto");
		RotaLancamento.DefinirSubRotas("/lancamento");
		
		
	}
}
