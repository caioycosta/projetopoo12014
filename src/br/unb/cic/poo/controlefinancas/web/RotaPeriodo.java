/**
 * 
 */
package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;
import java.util.Date;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.negocio.NegocioPeriodos;

/**
 * @author CaioYuri
 * 
 */
public class RotaPeriodo extends Rota {

	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("Periodo");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Criar");
		vwb.getListaPaginas().add("Listar");
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.web.Rota#getRotaPadrao()
	 */
	@Override
	protected String getRotaPadrao() {
		return "/listar";
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.web.Rota#definirSubRotasProtected(java.lang.String)
	 */
	@Override
	protected void definirSubRotasProtected(final String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/listar") {			
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioPeriodos p = getFabrica().criarNegocioPeriodo();
				PeriodoViewModel vwb = new PeriodoViewModel();
				vwb.setLista(p.buscarPeriodos(usr));
				preencherViewModelBase(vwb);
				
				vwb.setInfo("Veja todos os seus per�odos.");
				vwb.setTitulo("Listagem de per�dos");
				vwb.setDescricaoView("Listar os per�odos existentes.");
				
				return modelAndView(vwb, "periodolistar.ftl");
			}
		});
		
		Spark.get(new FreeMarkerRoute(nomeRota + "/excluir/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioPeriodos p = getFabrica().criarNegocioPeriodo();
				Periodo objp = new Periodo();
				objp.setIdPeriodo(Integer.parseInt(request.params(":id")));
				p.ExcluirPeriodo(usr, objp);

				response.redirect(nomeRota + "/listar");

				halt();
				return modelAndView(null, "periodolistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioPeriodos np = getFabrica().criarNegocioPeriodo();

				Periodo p = np.BuscarPeriodo(usr,
						Integer.parseInt(request.params(":id")));

				PeriodoViewModel vwb = new PeriodoViewModel();

				vwb.setPeriodo(p);

				vwb.setInfo("Digite os novos dados do per�odo.");
				vwb.setTitulo("Edi��o de lan�amento");
				vwb.setDescricaoView("Alterar um per�odo existente");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "periodocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@SuppressWarnings("deprecation")
			@Override
			public Object tempHandle(Request request, Response response) {

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioPeriodos np = getFabrica().criarNegocioPeriodo();

				Periodo p = np.BuscarPeriodo(usr,
						Integer.parseInt(request.params(":id")));
				p.setDataInicio(new Date(Integer.parseInt(request
						.queryParams("anoi")) - 1900, Integer.parseInt(request
						.queryParams("mesi")) - 1, Integer.parseInt(request
						.queryParams("diai"))));
				p.setDataFim(new Date(Integer.parseInt(request
						.queryParams("anof")) - 1900, Integer.parseInt(request
						.queryParams("mesf")) - 1, Integer.parseInt(request
						.queryParams("diaf"))));
				np.EditarPeriodo(usr, p);
				response.redirect(nomeRota + "/listar");

				halt();
				return modelAndView(null, "periodolistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				LancamentoViewModel vwb = new LancamentoViewModel();

				vwb.setInfo("Digite os dados do novo per�odo.");
				vwb.setTitulo("Cria��o de per�odo");
				vwb.setDescricaoView("Criar um novo per�odo");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "periodocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/criar") {			
			@Override
			@SuppressWarnings("deprecation")
			public Object tempHandle(Request request, Response response) {

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioPeriodos np = getFabrica().criarNegocioPeriodo();

				Periodo p = new Periodo(); 
				p.setDataInicio(new Date(Integer.parseInt(request
						.queryParams("anoi")) - 1900, Integer.parseInt(request
						.queryParams("mesi")) - 1, Integer.parseInt(request
						.queryParams("diai"))));
				p.setDataFim(new Date(Integer.parseInt(request
						.queryParams("anof")) - 1900, Integer.parseInt(request
						.queryParams("mesf")) - 1, Integer.parseInt(request
						.queryParams("diaf"))));

				np.CadastrarPeriodo(usr, p);
				
				response.redirect(nomeRota + "/listar");

				halt();
				return modelAndView(null, "periodolistar.ftl");
			}
		});
	}

}
