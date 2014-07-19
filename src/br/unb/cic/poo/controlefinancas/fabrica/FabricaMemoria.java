package br.unb.cic.poo.controlefinancas.fabrica;

import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaLancamentos;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaPeriodo;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario;
import br.unb.cic.poo.controlefinancas.persistencia.ContasEmMemoria;
import br.unb.cic.poo.controlefinancas.persistencia.GruposGastoEmMemoria;
import br.unb.cic.poo.controlefinancas.persistencia.LancamentosEmMemoria;
import br.unb.cic.poo.controlefinancas.persistencia.UsuarioEmMemoria;

/**
 * @author CaioYuri
 * implementacao da fabrica para persistencia em mmemoria
 */
public class FabricaMemoria extends Fabrica {

	private static Fabrica instance;
	
	static {
		instance = new FabricaMemoria();
	}
	
	/**
	 * construtor privado pois é singleton
	 */
	private FabricaMemoria()
	{
		
	}
	
	/**
	 * @return instancia singleton da fabrica
	 */
	public static Fabrica getInstance()
	{
		return instance;
	}
	
	/** 
	 * @see br.unb.cic.poo.controlefinancas.fabrica.Fabrica#criarPersistenciaConta()
	 */
	@Override
	protected IPersistenciaConta criarPersistenciaConta() {
		return new ContasEmMemoria();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.fabrica.Fabrica#criarPersistenciaGrupoGasto()
	 */
	@Override
	protected IPersistenciaGrupoGasto criarPersistenciaGrupoGasto() {
		return new GruposGastoEmMemoria();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.fabrica.Fabrica#criarPersistenciaLancamentos()
	 */
	@Override
	protected IPersistenciaLancamentos criarPersistenciaLancamentos() {
		return new LancamentosEmMemoria();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.fabrica.Fabrica#criarPersistenciaUsuario()
	 */
	@Override
	protected IPersistenciaUsuario criarPersistenciaUsuario() {
		return new UsuarioEmMemoria();
	}

	@Override
	protected IPersistenciaPeriodo criarPersistenciaPeriodo() {
		// WONTFIX nao implementado
		return null;
	}

}
