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
	private HashMap<Usuario, Collection<GrupoGasto>> grpStore;
	
	public GruposGastoEmMemoria()
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
		
		
		return grpStore.get(u);
	}

}
