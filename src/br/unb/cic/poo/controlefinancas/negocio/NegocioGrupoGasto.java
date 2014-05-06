package br.unb.cic.poo.controlefinancas.negocio;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.persistencia.GruposGastoEmMemoria;

public class NegocioGrupoGasto {
	private IPersistenciaGrupoGasto persistencia;
	
	public NegocioGrupoGasto() {
		// TODO Auto-generated constructor stub
		persistencia = new GruposGastoEmMemoria();
	}
	
public void criarGrupoGasto(Usuario usr, GrupoGasto grp)
{
	persistencia.criarGrupoGasto(usr, grp);
}
	
	public Collection<GrupoGasto> listarGruposGasto(Usuario usr) 
	{
		return persistencia.listarGruposGasto(usr);
	}
	
	
}
