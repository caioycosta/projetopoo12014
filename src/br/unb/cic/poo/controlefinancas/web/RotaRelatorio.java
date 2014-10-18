package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;

import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.negocio.NegocioPeriodos;
import br.unb.cic.poo.controlefinancas.negocio.NegocioRelatorios;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;

public class RotaRelatorio extends Rota {

	@Override
	protected String getRotaPadrao() {
		return "/gruposgasto";
	}

	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("Relatorios");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Patrimonial");
		vwb.getListaPaginas().add("GruposGasto");
	}
	
	@Override
	protected void definirSubRotasProtected(String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/patrimonial") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				
				NegocioRelatorios nr = getFabrica().criarNegocioRelatorios(usr);
				ViewModelBase vwb =  new RelatorioPatrimonialViewModel( nr.gerarRelatorio(usr));
				
				vwb.setInfo("Veja qual o valor do seu patrimônio.");
				vwb.setTitulo("Relatório patrimonial");
				vwb.setDescricaoView("Quando você tem de patrimônio");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "patrimonial.ftl");
			}
		});
				
		Spark.post(new FreeMarkerRoute(nomeRota + "/gruposgasto") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				
				NegocioRelatorios nr = getFabrica().criarNegocioRelatorios(usr);
				NegocioPeriodos np = getFabrica().criarNegocioPeriodo();
				Periodo pS = np.BuscarPeriodo(usr,
						Integer.parseInt(request.queryParams("periodo")));
				RelatorioGruposGastoViewModel vwb = new RelatorioGruposGastoViewModel(nr.gerarRelatorioGrupoGasto(usr, pS));
				
				vwb.setInfo("Veja em que está gastando seu dinheiro.");
				vwb.setTitulo("Relatório de grupos de gasto");
				vwb.setDescricaoView("Com o que gastou");
				preencherViewModelBase(vwb);

				vwb.setPeriodoSel(pS);
				vwb.setPeriodos(np.buscarPeriodos(usr));
				
				return modelAndView(vwb, "gruposgasto.ftl");
			}
		});
		
		Spark.get(new FreeMarkerRoute(nomeRota + "/gruposgasto") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				
				NegocioRelatorios nr = getFabrica().criarNegocioRelatorios(usr);
				NegocioPeriodos np = getFabrica().criarNegocioPeriodo();
				
				RelatorioGruposGastoViewModel vwb = new RelatorioGruposGastoViewModel(nr.gerarRelatorioGrupoGasto(usr, np.buscarPeriodoCorrente(usr)));
				
				vwb.setInfo("Veja em que está gastando seu dinheiro.");
				vwb.setTitulo("Relatório de grupos de gasto");
				vwb.setDescricaoView("Com o que gastou");
				preencherViewModelBase(vwb);

				vwb.setPeriodoSel(np.buscarPeriodoCorrente(usr));
				vwb.setPeriodos(np.buscarPeriodos(usr));
				
				return modelAndView(vwb, "gruposgasto.ftl");
			}
		});
	}

}
