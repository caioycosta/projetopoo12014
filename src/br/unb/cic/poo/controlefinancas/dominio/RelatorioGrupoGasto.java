/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.HashMap;


/**
 * @author Caio Yuri
 *
 */
public class RelatorioGrupoGasto {
	HashMap<GrupoGasto, Integer> gastoPorGrupo;

	public HashMap<GrupoGasto, Integer> getGastoPorGrupo() {
		return gastoPorGrupo;
	}

	public void setGastoPorGrupo(HashMap<GrupoGasto, Integer> gastoPorGrupo) {
		this.gastoPorGrupo = gastoPorGrupo;
	}
}
