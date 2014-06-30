package br.unb.cic.poo.controlefinancas.fabrica;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.*;

/**
 * @author CaioYuri
 * classe base para as fabricas
 */
public abstract class Fabrica {
	/**
	 * @return uma instancia de negociousuario
	 * cria um negociousuario
	 */
	public NegocioUsuario criarNegocioUsuario() {
		return new NegocioUsuario(criarPersistenciaUsuario());
	}

	/**
	 * @return uma instancia de NegocioGrupoGasto
	 * cria um NegocioGrupoGasto
	 */
	public NegocioGrupoGasto criarNegocioGrupoGasto() {
		return new NegocioGrupoGasto(criarPersistenciaGrupoGasto());
	}

	/**
	 * @return uma instancia de NegocioLancamentos
	 * cria um NegocioLancamentos
	 */
	public NegocioLancamentos criarNegocioLancamentos() {
		return new NegocioLancamentos(criarPersistenciaLancamentos());
	}

	/**
	 * @return uma instancia de NegocioContas
	 * cria um NegocioContas
	 */
	public NegocioContas criarNegocioContas() {
		return new NegocioContas(criarPersistenciaConta());
	}

	/**
	 * @return uma instancia de persistencia de contas
	 * cria um persistencia de contas
	 */
	protected abstract IPersistenciaConta criarPersistenciaConta();

	/**
	 * @return uma instancia de persistencia de GrupoGasto
	 * cria um persistencia de GrupoGasto
	 */
	protected abstract IPersistenciaGrupoGasto criarPersistenciaGrupoGasto();
	
	/**
	 * @return uma instancia de persistencia de Lancamentos
	 * cria um persistencia de Lancamentos
	 */
	protected abstract IPersistenciaLancamentos criarPersistenciaLancamentos();

	/**
	 * @return uma instancia de persistencia de Usuario
	 * cria um persistencia de Usuario
	 */
	protected abstract IPersistenciaUsuario criarPersistenciaUsuario();

}
