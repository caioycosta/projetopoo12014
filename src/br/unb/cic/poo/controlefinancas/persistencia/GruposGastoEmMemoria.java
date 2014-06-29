/**
 * 
 */
package br.unb.cic.poo.controlefinancas.persistencia;

import java.util.*;

import br.unb.cic.poo.controlefinancas.dominio.*;

/**
 * @author CaioYuri
 *
 */
public class GruposGastoEmMemoria implements IPersistenciaGrupoGasto {
	private static HashMap<Usuario, Collection<GrupoGasto>> grpStore;
	
	static
	{
		grpStore = new HashMap<Usuario,Collection<GrupoGasto>>();
	}
	static int ultimoid = 0;
	@Override
	public void criarGrupoGasto(Usuario usr, GrupoGasto grp) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);
		
		if (!grpStore.containsKey(u)){
			grpStore.put(u, new ArrayList<GrupoGasto>());
			
			
		}
		
		grp.setId(++ultimoid);
		
		grpStore.get(u).add(grp);
	}

	@Override
	public Collection<GrupoGasto> listarGruposGasto(Usuario usr) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);

		if (!grpStore.containsKey(u)){
			grpStore.put(u, new ArrayList<GrupoGasto>());
			
			
		}
		
		return grpStore.get(u);
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto#alterarGrupoGasto(br.unb.cic.poo.controlefinancas.dominio.GrupoGasto, br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public void alterarGrupoGasto(GrupoGasto grp, Usuario usr) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);
		GrupoGasto remover = null;
		for (GrupoGasto gg : grpStore.get(u))
		{
			if (gg.getId() == grp.getId())
			{
				remover = gg;
			}
		}
		
		grpStore.get(u).remove(remover);
		grpStore.get(u).add(grp);
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto#excluirGrupoGasto(br.unb.cic.poo.controlefinancas.dominio.Usuario, int)
	 */
	@Override
	public void excluirGrupoGasto(Usuario usr, int parseInt) {
		// TODO Auto-generated method stub
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);
		GrupoGasto remover = null;
		for (GrupoGasto gg : grpStore.get(u))
		{
			if (gg.getId() == parseInt)
			{
				remover = gg;
			}
		}
		
		grpStore.get(u).remove(remover);
	}

}
