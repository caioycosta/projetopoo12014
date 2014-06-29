/**
 * 
 */
package br.unb.cic.poo.controlefinancas.persistencia;

import br.unb.cic.poo.controlefinancas.dominio.*;

/**
 * @author CaioYuri
 * 
 */
public class LancamentosEmMemoria implements IPersistenciaLancamentos {

	static int ultimoid = 0;

	@Override
	public void criarLancamento(Lancamento l, Usuario usr) {
		l.setId(++ultimoid);
		
		criarLancamentoId(l, usr);
		
	}

	@Override
	public Lancamento buscarLancamento(Usuario usr, int parseInt) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);

		ContasEmMemoria c = new ContasEmMemoria();

		ConjuntoContas cj = c.listarContas(u);

		Lancamento l = null;

		for (Lancamento ls : cj.getContaAtivos().getLancamentos()) {
			if (ls.getId() == parseInt)
				l = ls;
		}

		for (Lancamento ls : cj.getContaPassivos().getLancamentos()) {
			if (ls.getId() == parseInt)
				l = ls;
		}

		for (Lancamento ls : cj.getContaRendimentos().getLancamentos()) {
			if (ls.getId() == parseInt)
				l = ls;
		}

		for (Lancamento ls : cj.getContaDespesas().getLancamentos()) {
			if (ls.getId() == parseInt)
				l = ls;
		}

		return l;
	}

	@Override
	public void alterarLancamento(Lancamento l, Usuario usr) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);

		ContasEmMemoria c = new ContasEmMemoria();

		ConjuntoContas cj = c.listarContas(u);

		Conta cn = null;
		Lancamento lc = null;

		for (Lancamento ls : cj.getContaAtivos().getLancamentos()) {
			if (ls.getId() == l.getId()) {
				cn = cj.getContaAtivos();
				lc = ls;
			}
		}

		for (Lancamento ls : cj.getContaPassivos().getLancamentos()) {
			if (ls.getId() == l.getId()) {
				cn = cj.getContaPassivos();
				lc = ls;
			}
		}

		for (Lancamento ls : cj.getContaRendimentos().getLancamentos()) {
			if (ls.getId() == l.getId()) {
				cn = cj.getContaRendimentos();
				lc = ls;
			}
		}

		for (Lancamento ls : cj.getContaDespesas().getLancamentos()) {
			if (ls.getId() == l.getId()) {
				cn = cj.getContaDespesas();
				lc = ls;
			}
		}
		
		cn.getLancamentos().remove(lc);
		criarLancamentoId(l, usr);
	}

	private void criarLancamentoId(Lancamento l, Usuario usr) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);

		ContasEmMemoria c = new ContasEmMemoria();

		ConjuntoContas cj = c.listarContas(u);

		

		if (l.getConta() instanceof ContaAtivos) {
			cj.getContaAtivos().getLancamentos().add(l);
		} else if (l.getConta() instanceof ContaPassivos) {
			cj.getContaPassivos().getLancamentos().add(l);
		} else if (l.getConta() instanceof ContaDespesas) {
			cj.getContaDespesas().getLancamentos().add(l);
		} else if (l.getConta() instanceof ContaRendimentos) {
			cj.getContaRendimentos().getLancamentos().add(l);
		}
	}
}
