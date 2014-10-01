package br.unb.cic.poo.controlefinancas.dominio;

import java.util.Collection;

public interface IPersistenciaSubconta {
	public void criarSubconta(Subconta s, Conta c);
	
	public void editarSubconta(Subconta s, Conta c);
	
	public void excluirSubconta(Subconta s);
	public Subconta buscarSubconta(int id);
	public Collection<Subconta> listarSubconta(Usuario u);
}
