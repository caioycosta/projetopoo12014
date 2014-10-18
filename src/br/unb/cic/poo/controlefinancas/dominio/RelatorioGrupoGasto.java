/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.Collection;


/**
 * @author Caio Yuri
 *
 */
public class RelatorioGrupoGasto {
	Collection<GrupoComValor> gastoPorGrupo;

	public Collection<GrupoComValor> getGastoPorGrupo() {
		return gastoPorGrupo;
	}

	public void setGastoPorGrupo(Collection<GrupoComValor> gastoPorGrupo) {
		this.gastoPorGrupo = gastoPorGrupo;
	}
}
