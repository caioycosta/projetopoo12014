package br.unb.cic.poo.controlefinancas.negocio;

import java.util.ArrayList;
import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.RelatorioGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.RelatorioPatrimonial;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaConta;
import br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaRelatorios;

public class NegocioRelatorios {
	IPersistenciaConta pers;
	IPersistenciaRelatorios pr;
	public NegocioRelatorios(IPersistenciaConta p, IPersistenciaRelatorios pr)
	{
		pers = p;
		this.pr = pr;
	}
	
	public RelatorioPatrimonial gerarRelatorio(Usuario usr)
	{
		// TODO refazer o relatorio patrimonial
		RelatorioPatrimonial rp = new RelatorioPatrimonial();
		
		return rp;
	}
	
	public Collection<RelatorioGrupoGasto> gerarRelatorioGrupoGasto (Usuario usr, Periodo per) {
		ArrayList<RelatorioGrupoGasto> resultado = new ArrayList<RelatorioGrupoGasto>();
		
		ConjuntoContas cjc = pers.listarContas(usr, per);
		
		resultado.add(pr.gerarRelatorioGrupoGasto(per, cjc.getContaRendimentos()));
		resultado.add(pr.gerarRelatorioGrupoGasto(per, cjc.getContaDespesas()));
		
		return resultado;
	}
}
