package br.unb.cic.poo.controlefinancas.negocio;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.persistencia.*;

public class NegocioContas {
	private IPersistenciaConta persistencia;

	public NegocioContas() {
		// TODO Auto-generated constructor stub
		persistencia = new ContasEmMemoria();
	}

	public NegocioContas(IPersistenciaConta pers) {
		this.persistencia = pers;
	}

	public ConjuntoContas listarContas(Usuario usr) {
		return persistencia.listarContas(usr);
	}

	public void atualizarConta(Conta c, Usuario usr) {
		atualizarConta(c, usr);
	}

}
