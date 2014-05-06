/**
 * 
 */
package br.unb.cic.poo.controlefinancas.persistencia;

import java.util.HashMap;

import br.unb.cic.poo.controlefinancas.dominio.*;

/**
 * @author CaioYuri
 * 
 */
public class ContasEmMemoria implements IPersistenciaConta {

	private HashMap<Usuario, ConjuntoContas> contasPorUsuario;

	/**
	 * @param contasPorUsuario
	 */
	public ContasEmMemoria() {
		this.contasPorUsuario = new HashMap<Usuario, ConjuntoContas>();
	}

	@Override
	public ConjuntoContas listarContas(Usuario usr) {
		// obter a referencia ao usuario da persistencia

		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);
		if (!contasPorUsuario.containsKey(u)) {
			ConjuntoContas cj = new ConjuntoContas();
			cj.setContaAtivos(new ContaAtivos());
			cj.setContaPassivos(new ContaPassivos());
			cj.setContaDespesas(new ContaDespesas());
			cj.setContaRendimentos(new ContaRendimentos());
			contasPorUsuario.put(u, cj);
		}

		return contasPorUsuario.get(u);
	}

	@Override
	public void atualizarConta(Conta c, Usuario usr) {
		Usuario u = UsuarioEmMemoria.obterObjetoUsuario(usr);
		ConjuntoContas cj = contasPorUsuario.get(u);
		
	
		if (c instanceof ContaAtivos) {
			c.setLancamentos(cj.getContaAtivos().getLancamentos());
			cj.setContaAtivos((ContaAtivos) c);
		}
		else if (c instanceof ContaPassivos) {
			c.setLancamentos(cj.getContaPassivos().getLancamentos());
			cj.setContaPassivos((ContaPassivos) c);
		}
		else if (c instanceof ContaRendimentos) {
			c.setLancamentos(cj.getContaDespesas().getLancamentos());
			cj.setContaDespesas((ContaDespesas) c);
		}
		else if (c instanceof ContaDespesas) {
			c.setLancamentos(cj.getContaRendimentos().getLancamentos());
			cj.setContaRendimentos((ContaRendimentos) c);
		}
	}

}
