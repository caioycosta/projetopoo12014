package br.unb.cic.poo.controlefinancas.web;


import java.util.ArrayList;
import java.util.Collection;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;
import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.NegocioContas;
import br.unb.cic.poo.controlefinancas.negocio.NegocioGrupoGasto;

/**
 * @author CaioYuri modulo de grupo de gasto
 */
public class RotaGrupoGasto extends Rota {
	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("GrupoGasto");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Listar");
		vwb.getListaPaginas().add("Criar");
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

				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				ListaGrupoGastoViewModel vwb = new ListaGrupoGastoViewModel();
				vwb.setGruposGasto(g.listarGruposGasto(usr));

				vwb.setInfo("Veja todos os seus grupos de gasto.");
				vwb.setTitulo("Lista dos grupos de gasto");
				vwb.setDescricaoView("Seus grupos de gasto");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "grupolistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				ViewModelBase vwb = new ViewModelBase();
				vwb.setInfo("Digite os dados do novo grupo de gasto.");
				vwb.setTitulo("Criar um grupos de gasto");
				vwb.setDescricaoView("crie um novo grupo de gasto");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "grupocriar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/excluir/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				g.excluirGrupoGasto(usr,
						Integer.parseInt(request.params(":id")));

				response.redirect("/grupogasto/listar");
				halt();
				return modelAndView(null, "grupolistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				GrupoViewModel vwb = new GrupoViewModel();

				for (GrupoGasto gg : g.listarGruposGasto(usr)) {
					if (gg.getId() == Integer.parseInt(request.params(":id")))
						vwb.setGrp(gg);
				}

				vwb.setInfo("Digite os novos dados do grupo de gasto.");
				vwb.setTitulo("Alterar um grupos de gasto");
				vwb.setDescricaoView("edite um grupo de gasto existente");
				preencherViewModelBase(vwb);
				return modelAndView(vwb, "grupocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioContas n = getFabrica().criarNegocioContas();
				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				ConjuntoContas cj = n.listarContas(usr, null);

				GrupoGasto grp;
				if (request.queryParams("tipo").equals("Despesa")) {
					grp = new GrupoGastoDespesa();
				} else
					grp = new GrupoGastoReceita();

				grp.setNome(request.queryParams("nome"));

				Collection<Conta> contas = new ArrayList<Conta>();

				if (request.queryParams().contains("contaAtivos")
						&& request.queryParams("contaAtivos").equals("1")) {
					contas.add(cj.getContaAtivos());
				}

				if (request.queryParams().contains("contaPassivos")
						&& request.queryParams("contaPassivos").equals("1")) {
					contas.add(cj.getContaPassivos());
				}

				if (request.queryParams().contains("contaRendimentos")
						&& request.queryParams("contaRendimentos").equals("1")) {
					contas.add(cj.getContaRendimentos());
				}

				if (request.queryParams().contains("contaDespesas")
						&& request.queryParams("contaDespesas").equals("1")) {
					contas.add(cj.getContaDespesas());
				}

				grp.setContas(contas);
				grp.setId(Integer.parseInt(request.params(":id")));
				g.alterarGrupoGasto(usr, grp);
				ViewModelBase vwb = new GrupoViewModel(
						"Grupo editado com sucesso");
				vwb.setInfo("Digite os novos dados do grupo de gasto.");
				vwb.setTitulo("Alterar um grupos de gasto");
				vwb.setDescricaoView("edite um grupo de gasto existente");
				preencherViewModelBase(vwb);
				return modelAndView(vwb, "grupocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioContas n = getFabrica().criarNegocioContas();
				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				ConjuntoContas cj = n.listarContas(usr, null);

				GrupoGasto grp;
				if (request.queryParams("tipo").equals("Despesa")) {
					grp = new GrupoGastoDespesa();
				} else
					grp = new GrupoGastoReceita();

				grp.setNome(request.queryParams("nome"));

				Collection<Conta> contas = new ArrayList<Conta>();

				if (request.queryParams().contains("contaAtivos")
						&& request.queryParams("contaAtivos").equals("1")) {
					contas.add(cj.getContaAtivos());
				}

				if (request.queryParams().contains("contaPassivos")
						&& request.queryParams("contaPassivos").equals("1")) {
					contas.add(cj.getContaPassivos());
				}

				if (request.queryParams().contains("contaRendimentos")
						&& request.queryParams("contaRendimentos").equals("1")) {
					contas.add(cj.getContaRendimentos());
				}

				if (request.queryParams().contains("contaDespesas")
						&& request.queryParams("contaDespesas").equals("1")) {
					contas.add(cj.getContaDespesas());
				}

				grp.setContas(contas);

				g.criarGrupoGasto(usr, grp);
				ViewModelBase vwb = new GrupoViewModel(
						"Grupo criado com sucesso");
				vwb.setInfo("Digite os dados do novo grupo de gasto.");
				vwb.setTitulo("Criar um grupos de gasto");
				vwb.setDescricaoView("crie um novo grupo de gasto");
				preencherViewModelBase(vwb);
				return modelAndView(vwb, "grupocriar.ftl");
			}
		});
	}
}
