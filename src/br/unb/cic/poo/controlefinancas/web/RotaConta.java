package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.NegocioContas;
import br.unb.cic.poo.controlefinancas.negocio.NegocioPeriodos;
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
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioContas n = getFabrica().criarNegocioContas();
				NegocioPeriodos np = getFabrica().criarNegocioPeriodo();
				Periodo pS = np.buscarPeriodoCorrente(usr);
				
				ConjuntoContas c = n.listarContas((Usuario) (request.session()
						.attribute("usuario")), pS);
				
				ContaViewModel vwb = new ContaViewModel(c);
				vwb.setInfo("Veja todas as suas contas. Não esqueça" +
						" de criar períodos antes de utilizar o sistema.");
				vwb.setTitulo("Lista das contas");
				vwb.setDescricaoView("Suas contas");
				preencherViewModelBase(vwb);
								
				vwb.setPeriodoSel(pS);
				vwb.setPeriodos(np.buscarPeriodos(usr));
				
				return modelAndView(vwb, "contaslistar.ftl");
			}
		});
		
		Spark.post(new FreeMarkerRoute(nomeRota + "/listar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioContas n = getFabrica().criarNegocioContas();
				NegocioPeriodos np = getFabrica().criarNegocioPeriodo();
				Periodo pS = np.BuscarPeriodo(usr,
						Integer.parseInt(request.queryParams("periodo")));
				
				ConjuntoContas c = n.listarContas((Usuario) (request.session()
						.attribute("usuario")), pS);
				
				ContaViewModel vwb = new ContaViewModel(c);
				vwb.setInfo("Veja todas as suas contas. Não esqueça" +
						" de criar períodos antes de utilizar o sistema.");
				vwb.setTitulo("Lista das contas");
				vwb.setDescricaoView("Suas contas");
				preencherViewModelBase(vwb);
				
				vwb.setPeriodoSel(pS);
				vwb.setPeriodos(np.buscarPeriodos(usr));
				
				return modelAndView(vwb, "contaslistar.ftl");
			}
		});
	}
}
