/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.Collection;



/**
 * @author CaioYuri
 *
 */
public interface IPersistenciaGrupoGasto {
	public void criarGrupoGasto(Usuario usr, GrupoGasto grp);
	
	public Collection<GrupoGasto> listarGruposGasto(Usuario usr) ;

	public void alterarGrupoGasto(GrupoGasto grp, Usuario usr);

	public void excluirGrupoGasto(Usuario usr, int parseInt);
	
	
}
