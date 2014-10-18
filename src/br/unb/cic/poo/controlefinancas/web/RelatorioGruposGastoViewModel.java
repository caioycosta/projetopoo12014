package br.unb.cic.poo.controlefinancas.web;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.RelatorioGrupoGasto;

public class RelatorioGruposGastoViewModel extends ViewModelBase {
     Collection<RelatorioGrupoGasto> relatorios;
 	Collection<Periodo> periodos;
 	Periodo periodoSel;
     public Collection<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(Collection<Periodo> periodos) {
		this.periodos = periodos;
	}

	public Periodo getPeriodoSel() {
		return periodoSel;
	}

	public void setPeriodoSel(Periodo periodoSel) {
		this.periodoSel = periodoSel;
	}

	public RelatorioGruposGastoViewModel(Collection<RelatorioGrupoGasto> rs)
     {
    	 relatorios = rs;
     }
     
	public Collection<RelatorioGrupoGasto> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(Collection<RelatorioGrupoGasto> relatorios) {
		this.relatorios = relatorios;
	}
}
