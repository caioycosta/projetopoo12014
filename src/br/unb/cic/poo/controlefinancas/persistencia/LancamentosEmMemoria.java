package br.unb.cic.poo.controlefinancas.persistencia;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaLancamentos;

/**
 * @author CaioYuri
 * persiste lancamentos em memoria
 */
public class LancamentosEmMemoria implements IPersistenciaLancamentos {

	static int ultimoid = 0;

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaLancamentos#criarLancamento(br.unb.cic.poo.controlefinancas.dominio.Lancamento, br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public void criarLancamento(Lancamento l, Usuario usr) {
		l.setId(++ultimoid);
		
		criarLancamentoId(l, usr);
		
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaLancamentos#buscarLancamento(br.unb.cic.poo.controlefinancas.dominio.Usuario, int)
	 */
	@Override
	public Lancamento buscarLancamento(Usuario usr, int parseInt) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);

		ContasEmMemoria c = new ContasEmMemoria();

		ConjuntoContas cj = c.listarContas(u, null);

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

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaLancamentos#alterarLancamento(br.unb.cic.poo.controlefinancas.dominio.Lancamento, br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public void alterarLancamento(Lancamento l, Usuario usr) {
		excluirLancamento(usr, l);
		criarLancamentoId(l, usr);
	}

	/**
	 * @param l
	 * @param usr
	 * cia um lancamento reaproveitando o id ja configurado no obj
	 */
	private void criarLancamentoId(Lancamento l, Usuario usr) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);

		ContasEmMemoria c = new ContasEmMemoria();

		ConjuntoContas cj = c.listarContas(u, null);

		

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

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaLancamentos#excluirLancamento(br.unb.cic.poo.controlefinancas.dominio.Usuario, br.unb.cic.poo.controlefinancas.dominio.Lancamento)
	 */
	@Override
	public void excluirLancamento(Usuario u, Lancamento l) {
		ContasEmMemoria c = new ContasEmMemoria();
		ConjuntoContas cj = c.listarContas(u, null);

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
		
	}
}
