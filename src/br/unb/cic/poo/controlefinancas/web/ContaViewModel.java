package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.ContaAtivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaDespesas;
import br.unb.cic.poo.controlefinancas.dominio.ContaPassivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaRendimentos;

public class ContaViewModel extends ViewModelBase {
	ConjuntoContas cj;
	public ContaViewModel(ConjuntoContas c)
	{
		cj = c;
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
