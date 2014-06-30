package br.unb.cic.poo.controlefinancas.persistencia;

import java.util.HashMap;

import br.unb.cic.poo.controlefinancas.dominio.*;

/**
 * @author CaioYuri
 * persiste contas em memoria
 */
public class ContasEmMemoria implements IPersistenciaConta {

	private static HashMap<Usuario, ConjuntoContas> contasPorUsuario;
	private static int ultimoId;

	static {
		contasPorUsuario = new HashMap<Usuario, ConjuntoContas>();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta#listarContas(br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
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
			
			cj.getContaAtivos().setId(++ultimoId);
			cj.getContaPassivos().setId(++ultimoId);
			cj.getContaDespesas().setId(++ultimoId);
			cj.getContaRendimentos().setId(++ultimoId);
			
			contasPorUsuario.put(u, cj);
		}

		return contasPorUsuario.get(u);
	}

	
}
