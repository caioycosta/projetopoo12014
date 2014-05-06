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

	static int ultimoid =0;
	
	@Override
	public void criarLancamento(Lancamento l, Usuario usr) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);

		ContasEmMemoria c = new ContasEmMemoria();

		ConjuntoContas cj = c.listarContas(u);

		l.setId(++ultimoid);
		
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
