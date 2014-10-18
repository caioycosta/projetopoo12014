package br.unb.cic.poo.controlefinancas.dominio.interfaces;

import java.util.Collection;

import br.unb.cic.poo.controlefinancas.dominio.Conta;
import br.unb.cic.poo.controlefinancas.dominio.Subconta;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;

public interface IPersistenciaSubconta {
	public void criarSubconta(Subconta s, Conta c);
	
	public void editarSubconta(Subconta s, Conta c);
	
	public void excluirSubconta(Subconta s);
	public Subconta buscarSubconta(int id);
	public Collection<Subconta> listarSubconta(Usuario u);
}
