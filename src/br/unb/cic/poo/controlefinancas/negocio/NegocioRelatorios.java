package br.unb.cic.poo.controlefinancas.negocio;

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
		// TODO refazer o relatorio patrimonial
		RelatorioPatrimonial rp = new RelatorioPatrimonial();
		
		return rp;
	}
}
