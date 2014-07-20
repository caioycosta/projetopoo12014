package br.unb.cic.poo.controlefinancas.testes;

import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.ContaAtivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaPassivos;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

public class StubPersistenciaConta implements IPersistenciaConta {

	public Usuario usuarioFornecido = null;
	
	
	public StubPersistenciaConta(int ativo, int passivo)
	{
		
	}
	
	@Override
	public ConjuntoContas listarContas(Usuario usr, Periodo p) {
		usuarioFornecido = usr;
		
		ContaAtivos ca = new ContaAtivos();
		ContaPassivos cp = new ContaPassivos();
		
		ConjuntoContas cj = new ConjuntoContas();
		cj.setContaAtivos(ca);
		cj.setContaPassivos(cp);
		
		
		return cj;
	}

}
