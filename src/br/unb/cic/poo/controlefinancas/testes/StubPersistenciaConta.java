package br.unb.cic.poo.controlefinancas.testes;

import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.ContaAtivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaPassivos;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

public class StubPersistenciaConta implements IPersistenciaConta {

	public Usuario usuarioFornecido = null;
	
	private int saldoAtivo = 0;
	private int saldoPassivo = 0;
	
	public StubPersistenciaConta(int ativo, int passivo)
	{
		saldoAtivo = ativo;
		saldoPassivo = passivo;
	}
	
	@Override
	public ConjuntoContas listarContas(Usuario usr) {
		usuarioFornecido = usr;
		
		ContaAtivos ca = new ContaAtivos();
		ContaPassivos cp = new ContaPassivos();
		
		ConjuntoContas cj = new ConjuntoContas();
		cj.setContaAtivos(ca);
		cj.setContaPassivos(cp);
		
		ca.setSaldo(saldoAtivo);
		cp.setSaldo(saldoPassivo);
		
		return cj;
	}

}
