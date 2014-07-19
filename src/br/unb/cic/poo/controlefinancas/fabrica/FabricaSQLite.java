package br.unb.cic.poo.controlefinancas.fabrica;

import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaLancamentos;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaPeriodo;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario;
import br.unb.cic.poo.controlefinancas.persistencia.PersistenciaSQLite;

/**
 * @author CaioYuri
 * implementacao da fabrica para persistencia em banco sqlite
 */
public class FabricaSQLite extends Fabrica {

	private static Fabrica instance;
	
	static {
		instance = new FabricaSQLite();
	}
	
	/**
	 * construtor privado pois é singleton
	 */
	private FabricaSQLite() {
		
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
		return new PersistenciaSQLite();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.fabrica.Fabrica#criarPersistenciaGrupoGasto()
	 */
	@Override
	protected IPersistenciaGrupoGasto criarPersistenciaGrupoGasto() {
		return new PersistenciaSQLite();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.fabrica.Fabrica#criarPersistenciaLancamentos()
	 */
	@Override
	protected IPersistenciaLancamentos criarPersistenciaLancamentos() {
		return new PersistenciaSQLite();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.fabrica.Fabrica#criarPersistenciaUsuario()
	 */
	@Override
	protected IPersistenciaUsuario criarPersistenciaUsuario() {
		return new PersistenciaSQLite();
	}

	@Override
	protected IPersistenciaPeriodo criarPersistenciaPeriodo() {
		return new PersistenciaSQLite();
	}

}
