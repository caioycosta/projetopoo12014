package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;


import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;
import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;

import br.unb.cic.poo.controlefinancas.dominio.MinhaClasseData;
import br.unb.cic.poo.controlefinancas.dominio.Subconta;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.negocio.NegocioContas;
import br.unb.cic.poo.controlefinancas.negocio.NegocioPeriodos;
import br.unb.cic.poo.controlefinancas.negocio.NegocioSubconta;

public class RotaSubconta extends Rota {

	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("Subconta");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Criar");
		vwb.getListaPaginas().add("Listar");
	}
	
	@Override
	protected String getRotaPadrao() {
		return "/listar";
	}

	@Override
	protected void definirSubRotasProtected(final String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/listar") {			
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioSubconta p = getFabrica().criarNegocioSubconta();
				SubcontaViewModel vwb = new SubcontaViewModel();
				vwb.setLista(p.listarSubconta(usr));
				preencherViewModelBase(vwb);
				
				vwb.setInfo("Veja todas as suas subcontas.");
				vwb.setTitulo("Listagem de subcontas");
				vwb.setDescricaoView("Listar as subcontas existentes.");
				
				return modelAndView(vwb, "subcontalistar.ftl");
			}
		});
		
		Spark.get(new FreeMarkerRoute(nomeRota + "/excluir/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioSubconta p = getFabrica().criarNegocioSubconta();
				Subconta objp = p.buscarSubconta(Integer.parseInt(request.params(":id")));
				p.excluirSubconta(objp);

				response.redirect(nomeRota + "/listar");

				halt();
				return modelAndView(null, "subcontalistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				
				NegocioSubconta p = getFabrica().criarNegocioSubconta();
				
				Subconta objp = p.buscarSubconta(Integer.parseInt(request.params(":id")));
				
				SubcontaViewModel vwb = new SubcontaViewModel();
				vwb.setSubconta(objp);
				
				preencherViewModelBase(vwb);
				
				vwb.setInfo("Edite sua subcontas.");
				vwb.setTitulo("Edicao de subcontas");
				vwb.setDescricaoView("Editar as subcontas existentes.");

				return modelAndView(vwb, "subcontacriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioSubconta np = getFabrica().criarNegocioSubconta();

				Subconta p = np.buscarSubconta(
						Integer.parseInt(request.params(":id")));
				p.setNome(request.queryParams("nome"));
				p.setDataInicio(new MinhaClasseData(Integer.parseInt(request
						.queryParams("anoi")) - 1900, Integer.parseInt(request
						.queryParams("mesi")) - 1, Integer.parseInt(request
						.queryParams("diai"))));
				p.setDataFim(new MinhaClasseData(Integer.parseInt(request
						.queryParams("anof")) - 1900, Integer.parseInt(request
						.queryParams("mesf")) - 1, Integer.parseInt(request
						.queryParams("diaf"))));
				
				NegocioContas nc = getFabrica().criarNegocioContas();
				NegocioPeriodos npd = getFabrica().criarNegocioPeriodo();
				
				ConjuntoContas cj = nc.listarContas(usr, npd.buscarPeriodoCorrente(usr));
				
				
				switch (request.queryParams("tipoc"))
				{
				case "A" :
					np.editarSubconta(p, cj.getContaAtivos());
					break;
				case "P" :
					np.editarSubconta(p, cj.getContaPassivos());
					break;
				case "D" :
					np.editarSubconta(p, cj.getContaDespesas());
					break;
				case "R" :
					np.editarSubconta(p, cj.getContaRendimentos());
					break;
				}
								
				
				response.redirect(nomeRota + "/listar");

				halt();
				return modelAndView(null, "subcontalistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				LancamentoViewModel vwb = new LancamentoViewModel();

				vwb.setInfo("Digite os dados do novo subconta.");
				vwb.setTitulo("Criação de subconta");
				vwb.setDescricaoView("Criar um novo subconta");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "subcontacriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/criar") {			
			@Override
			public Object tempHandle(Request request, Response response) {

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioSubconta np = getFabrica().criarNegocioSubconta();

				Subconta p = new Subconta();
				p.setNome(request.queryParams("nome"));
				p.setDataInicio(new MinhaClasseData(Integer.parseInt(request
						.queryParams("anoi")) - 1900, Integer.parseInt(request
						.queryParams("mesi")) - 1, Integer.parseInt(request
						.queryParams("diai"))));
				p.setDataFim(new MinhaClasseData(Integer.parseInt(request
						.queryParams("anof")) - 1900, Integer.parseInt(request
						.queryParams("mesf")) - 1, Integer.parseInt(request
						.queryParams("diaf"))));
				
				NegocioContas nc = getFabrica().criarNegocioContas();
				NegocioPeriodos npd = getFabrica().criarNegocioPeriodo();
				
				ConjuntoContas cj = nc.listarContas(usr, npd.buscarPeriodoCorrente(usr));
				
				
				switch (request.queryParams("tipoc"))
				{
				case "A" :
					np.criarSubconta(p, cj.getContaAtivos());
					break;
				case "P" :
					np.criarSubconta(p, cj.getContaPassivos());
					break;
				case "D" :
					np.criarSubconta(p, cj.getContaDespesas());
					break;
				case "R" :
					np.criarSubconta(p, cj.getContaRendimentos());
					break;
				}
						
				response.redirect(nomeRota + "/listar");

				halt();
				return modelAndView(null, "subcontalistar.ftl");
			}
		});
	}

}
