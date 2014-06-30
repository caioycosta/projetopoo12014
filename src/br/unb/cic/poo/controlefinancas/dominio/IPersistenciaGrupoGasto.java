package br.unb.cic.poo.controlefinancas.dominio;

import java.util.Collection;



/**
 * @author CaioYuri
 * persistencia de grupos de gasto
 */
public interface IPersistenciaGrupoGasto {
	/**
	 * @param usr
	 * @param grp
	 * cria um novo grupo de gasto
	 */
	public void criarGrupoGasto(Usuario usr, GrupoGasto grp);
	
	/**
	 * @param usr
	 * @return a lista dos grupos do usuario atual
	 * lista os grupos de gasto
	 */
	public Collection<GrupoGasto> listarGruposGasto(Usuario usr) ;

	/**
	 * @param grp
	 * @param usr
	 * altera um grupo de gasto
	 */
	public void alterarGrupoGasto(GrupoGasto grp, Usuario usr);

	/**
	 * @param usr
	 * @param parseInt
	 * exclui um grupo de gasto e quaisquer objetos subordinados a ele
	 */
	public void excluirGrupoGasto(Usuario usr, int parseInt);
	
	
}
