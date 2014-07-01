package br.unb.cic.poo.controlefinancas.negocio;

import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta;
import br.unb.cic.poo.controlefinancas.dominio.RelatorioPatrimonial;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

public class NegocioRelatorios {
	IPersistenciaConta pers;
	public NegocioRelatorios(IPersistenciaConta p)
	{
		pers = p;
	}
	
	public RelatorioPatrimonial gerarRelatorio(Usuario usr)
	{
		ConjuntoContas cj = pers.listarContas(usr);
		
		RelatorioPatrimonial rp = new RelatorioPatrimonial();
		rp.setSaldoAtivos(cj.getContaAtivos().getSaldo());
		rp.setSaldoPassivos(cj.getContaPassivos().getSaldo());
		
		return rp;
	}
}
