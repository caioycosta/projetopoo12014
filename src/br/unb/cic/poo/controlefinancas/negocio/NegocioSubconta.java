package br.unb.cic.poo.controlefinancas.negocio;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.Conta;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaSubconta;
import br.unb.cic.poo.controlefinancas.dominio.Subconta;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

public class NegocioSubconta {

	private IPersistenciaSubconta p;
	
	public NegocioSubconta(IPersistenciaSubconta p) {
		this.p = p;
	}

	public void criarSubconta(Subconta s, Conta c) {
		p.criarSubconta(s, c);
	}

	public void editarSubconta(Subconta s, Conta c) {
		p.editarSubconta(s, c);
	}

	public void excluirSubconta(Subconta s) {
		p.excluirSubconta(s);
		
	}

	public Subconta buscarSubconta(int id) {
		return p.buscarSubconta(id);
	}

	public Collection<Subconta> listarSubconta(Usuario u) {
		return p.listarSubconta(u);
	}

}
