/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio.interfaces;

import br.unb.cic.poo.controlefinancas.dominio.Conta;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.RelatorioGrupoGasto;

/**
 * @author Caio Yuri
 *
 */
public interface IPersistenciaRelatorios {
	RelatorioGrupoGasto gerarRelatorioGrupoGasto(Periodo p, Conta c);
}
