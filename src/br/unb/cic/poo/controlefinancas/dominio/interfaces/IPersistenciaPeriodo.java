/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio.interfaces;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

/**
 * interface para persistencia de periodos de contabilizacao
 * @author CaioYuri
 *
 */
public interface IPersistenciaPeriodo {
	public Collection<Periodo> buscarPeriodos(Usuario usr);
	public void CadastrarPeriodo(Usuario usr, Periodo p);
	public void EditarPeriodo(Usuario usr,Periodo p);
	public void ExcluirPeriodo(Usuario usr,Periodo p);
	public Periodo BuscarPeriodo(Usuario usr,int id);
	public Periodo BuscarPeriodoCorrente(Usuario usr);
}
