package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;

import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.negocio.NegocioRelatorios;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;

public class RotaRelatorio extends Rota {

	@Override
	protected String getRotaPadrao() {
		return "/patrimonial";
	}

	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("Relatorios");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Patrimonial");
	}
	
	@Override
	protected void definirSubRotasProtected(String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/patrimonial") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				
				NegocioRelatorios nr = getFabrica().criarNegocioRelatorios();
				ViewModelBase vwb =  new RelatorioPatrimonialViewModel( nr.gerarRelatorio(usr));
				
				vwb.setInfo("Veja qual o valor do seu patrimônio.");
				vwb.setTitulo("Relatório patrimonial");
				vwb.setDescricaoView("Quando você tem de patrimônio");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "patrimonial.ftl");
			}
		});
	}

}
