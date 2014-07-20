/**
 * 
 */
package br.unb.cic.poo.controlefinancas.dominio;

import java.util.Collection;

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
