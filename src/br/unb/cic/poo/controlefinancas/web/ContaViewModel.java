package br.unb.cic.poo.controlefinancas.web;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.ContaAtivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaDespesas;
import br.unb.cic.poo.controlefinancas.dominio.ContaPassivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaRendimentos;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;

public class ContaViewModel extends ViewModelBase {
	ConjuntoContas cj;
	/**
	 * @return the cj
	 */
	public ConjuntoContas getCj() {
		return cj;
	}

	/**
	 * @param cj the cj to set
	 */
	public void setCj(ConjuntoContas cj) {
		this.cj = cj;
	}

	Collection<Periodo> periodos;
	Periodo periodoSel;
	/**
	 * @return the periodos
	 */
	public Collection<Periodo> getPeriodos() {
		return periodos;
	}

	/**
	 * @param periodos the periodos to set
	 */
	public void setPeriodos(Collection<Periodo> periodos) {
		this.periodos = periodos;
	}

	/**
	 * @return the periodoSel
	 */
	public Periodo getPeriodoSel() {
		return periodoSel;
	}

	/**
	 * @param periodoSel the periodoSel to set
	 */
	public void setPeriodoSel(Periodo periodoSel) {
		this.periodoSel = periodoSel;
	}

	public ContaViewModel(ConjuntoContas c)
	{
		cj = c;
	}
	
	public int getSaldo()
	{
		return cj.getSaldo();
	}
	
	public ContaAtivos getContaAtivos() {
		return cj.getContaAtivos();
	}
	
	public ContaPassivos getContaPassivos() {
		return cj.getContaPassivos();
	}
	
	public ContaRendimentos getContaRendimentos() {
		return cj.getContaRendimentos();
	}
	
	public ContaDespesas getContaDespesas() {
		return cj.getContaDespesas();
	}
}
