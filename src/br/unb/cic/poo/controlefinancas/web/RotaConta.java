package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.NegocioContas;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;

/**
 * @author CaioYuri modulo de contas
 */
public class RotaConta extends Rota {
	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("Contas");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Listar");
	}
	@Override
	protected String getRotaPadrao() {
		
		return "/listar";
	}
	/**
	 * @see br.unb.cic.poo.controlefinancas.web.Rota#definirSubRotasProtected(java.lang.String)
	 */
	@Override
	protected void definirSubRotasProtected(String nomeRota) {

		Spark.get(new FreeMarkerRoute(nomeRota + "/listar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioContas n = getFabrica().criarNegocioContas();

				ConjuntoContas c = n.listarContas((Usuario) (request.session()
						.attribute("usuario")));
				ViewModelBase vwb = new ContaViewModel(c);
				vwb.setInfo("Veja todas as suas contas.");
				vwb.setTitulo("Lista das contas");
				vwb.setDescricaoView("Suas contas");
				preencherViewModelBase(vwb);
				return modelAndView(vwb, "contaslistar.ftl");
			}
		});
	}
}
