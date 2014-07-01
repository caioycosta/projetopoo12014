package br.unb.cic.poo.controlefinancas.negocio;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.*;

/**
 * @author CaioYuri
 * regras do negocio para lancamento
 */
public class NegocioLancamentos {
	private IPersistenciaLancamentos persLanc;
	private IPersistenciaGrupoGasto persGrup;
	/**
	 * @param p implementacao de persistencia
	 * cria um novo negocio lancamentos com a implementacao de persistencia fornecida
	 */
	public NegocioLancamentos(IPersistenciaLancamentos p, IPersistenciaGrupoGasto persGrup) {
		persLanc = p;
		this.persGrup = persGrup; 
	}

	/**
	 * @param l
	 * @param usr
	 * cria um novo lancamento, atualizando o saldo da conta.
	 */
	public void criarLancamento(Lancamento l, Usuario usr) {
		l.creditar();
		persLanc.criarLancamento(l, usr);
	}

	/**
	 * @param usr
	 * @param parseInt
	 * @return o lancamento ou null se nao encontrado
	 * busca um lancamento por id
	 */
	public Lancamento buscarLancamento(Usuario usr, int parseInt) {		
		Lancamento l = persLanc.buscarLancamento(usr,parseInt);
		Collection<GrupoGasto> gg = persGrup.listarGruposGasto(usr);
		for (GrupoGasto g : gg)
		{
			if (g.getId() == l.getGrupo().getId()) {
				l.setGrupo(g);
			}
		}
		return l;
	}

	/**
	 * @param l
	 * @param usr
	 * altera um lancamento, atualizando o saldo da conta
	 */
	public void alterarLancamento(Lancamento l, Usuario usr) {
		l.debitar(buscarLancamento(usr,l.getId()).getValor());
		l.creditar();		
		persLanc.alterarLancamento(l,usr);
	}

	/**
	 * @param usr
	 * @param parseInt
	 * exclui um lancamento, atualizando o saldo da conta
	 */
	public void excluirLancamento(Usuario usr, int parseInt) {
		Lancamento l = buscarLancamento(usr,parseInt);
		
		l.debitar(); 
		persLanc.excluirLancamento(usr, l);
	}
}
