package br.unb.cic.poo.controlefinancas.negocio;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
/**
 * @author CaioYuri
 * regras do negocio para grupo de gasto
 */
public class NegocioGrupoGasto {
	private IPersistenciaGrupoGasto persistencia;

	/**
	 * @param p implementacao de persistencia
	 * cria um novo obj negocio grupo de gasto com a impl. de persistencia fornecida
	 */
	public NegocioGrupoGasto(IPersistenciaGrupoGasto p) {		
		persistencia = p;
	}

	/**
	 * @param usr
	 * @param grp
	 * cria um novo grupo de gasto
	 */
	public void criarGrupoGasto(Usuario usr, GrupoGasto grp) {
		persistencia.criarGrupoGasto(usr, grp);
	}

	/**
	 * @param usr
	 * @return lista de grupos
	 * obtem a lista de gurpos de gasto do usuario
	 */
	public Collection<GrupoGasto> listarGruposGasto(Usuario usr) {
		return persistencia.listarGruposGasto(usr);
	}

	/**
	 * @param usr
	 * @param grp
	 * modifica os dados de um grup de gasto existente
	 */
	public void alterarGrupoGasto(Usuario usr, GrupoGasto grp) {
		persistencia.alterarGrupoGasto(grp, usr);
	}

	/**
	 * @param usr
	 * @param parseInt
	 * exclui um grupo de gasto 
	 */
	public void excluirGrupoGasto(Usuario usr, int parseInt) {
		persistencia.excluirGrupoGasto(usr, parseInt);
	}

}
