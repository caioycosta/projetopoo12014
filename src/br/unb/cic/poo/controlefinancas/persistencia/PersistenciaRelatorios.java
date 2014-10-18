package br.unb.cic.poo.controlefinancas.persistencia;

import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.RelatorioGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaRelatorios;

public class PersistenciaRelatorios implements IPersistenciaRelatorios {

	private Usuario usuario;

	public PersistenciaRelatorios(Usuario u)
	{
		this.usuario = u;
	}
	
	@Override
	public RelatorioGrupoGasto gerarRelatorioGrupoGasto(Periodo p) {
		return null;
	}

}
