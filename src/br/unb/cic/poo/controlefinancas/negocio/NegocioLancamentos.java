package br.unb.cic.poo.controlefinancas.negocio;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.persistencia.*;

public class NegocioLancamentos {
	private IPersistenciaLancamentos persLanc;
	private IPersistenciaConta persCon;

	public NegocioLancamentos() {
		persLanc = new LancamentosEmMemoria();
		persCon = new ContasEmMemoria();

	}

	public void criarLancamento(Lancamento l, Usuario usr) {
		persLanc.criarLancamento(l, usr);

		ConjuntoContas cj = persCon.listarContas(usr);
		Conta x = null;
		if (l.getConta() instanceof ContaAtivos) {
			x = cj.getContaAtivos();
		} else if (l.getConta() instanceof ContaPassivos) {
			x = cj.getContaPassivos();
		} else if (l.getConta() instanceof ContaDespesas) {
			x = cj.getContaDespesas();
		} else if (l.getConta() instanceof ContaRendimentos) {
			x = cj.getContaRendimentos();
		}

		x.setSaldo(x.getSaldo()
				+ (l.getValor() * l.getGrupo().getMultiplicador() * l
						.getConta().getMultiplicador()));
	}
}
