package br.unb.cic.poo.controlefinancas.web;

import java.util.*;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;
import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.NegocioContas;
import br.unb.cic.poo.controlefinancas.negocio.NegocioGrupoGasto;

/**
 * @author CaioYuri
 * modulo de grupo de gasto
 */
public class RotaGrupoGasto extends Rota {
	/**
	 * @see br.unb.cic.poo.controlefinancas.web.Rota#DefinirSubRotas(java.lang.String)
	 */
	@Override
	public void DefinirSubRotas(String nomeRota)
	{
		Spark.get(new FreeMarkerRoute(nomeRota + "/listar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				ListaGrupoGastoViewModel mod = new ListaGrupoGastoViewModel();
				mod.setGruposGasto(g.listarGruposGasto(usr));

				return modelAndView(mod, "grupolistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				return modelAndView(null, "grupocriar.ftl");
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

				GrupoViewModel gv = new GrupoViewModel();

				for (GrupoGasto gg : g.listarGruposGasto(usr)) {
					if (gg.getId() == Integer.parseInt(request.params(":id")))
						gv.setGrp(gg);
				}

				return modelAndView(gv, "grupocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioContas n = getFabrica().criarNegocioContas();
				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				ConjuntoContas cj = n.listarContas(usr);

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

				return modelAndView(new GrupoViewModel(
						"Grupo editado com sucesso"), "grupocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioContas n = getFabrica().criarNegocioContas();
				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				ConjuntoContas cj = n.listarContas(usr);

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

				return modelAndView(new GrupoViewModel(
						"Grupo criado com sucesso"), "grupocriar.ftl");
			}
		});
	}
}
