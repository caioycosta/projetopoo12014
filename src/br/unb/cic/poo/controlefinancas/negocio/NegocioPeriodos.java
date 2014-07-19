/**
 * 
 */
package br.unb.cic.poo.controlefinancas.negocio;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaPeriodo;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

/**
 * @author CaioYuri
 * 
 */
public class NegocioPeriodos {
	private IPersistenciaPeriodo persPer;

	/**
	 * @param p
	 *            implementacao de persistencia cria um novo negocio lancamentos
	 *            com a implementacao de persistencia fornecida
	 */
	public NegocioPeriodos(IPersistenciaPeriodo p) {
		persPer = p;
	}

	public Collection<Periodo> buscarPeriodos(Usuario usr) {
		return persPer.buscarPeriodos(usr);
	}

	public void CadastrarPeriodo(Usuario usr, Periodo p) {
		persPer.CadastrarPeriodo(usr, p);
	}

	public void EditarPeriodo(Usuario usr, Periodo p) {
		persPer.EditarPeriodo(usr, p);
	}

	public void ExcluirPeriodo(Usuario usr, Periodo p) {
		persPer.ExcluirPeriodo(usr, p);
	}

	public Periodo BuscarPeriodo(Usuario usr, int id) {
		return persPer.BuscarPeriodo(usr, id);
	}
}
