package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.*;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;

/**
 * @author CaioYuri
 * modulo de lancamentos
 */
public class RotaLancamento extends Rota {
	/**
	 * @see br.unb.cic.poo.controlefinancas.web.Rota#DefinirSubRotas(java.lang.String)
	 */
	@Override
	public void DefinirSubRotas(String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/excluir/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				NegocioLancamentos g = getFabrica().criarNegocioLancamentos();

				g.excluirLancamento(usr,
						Integer.parseInt(request.params(":id")));

				response.redirect("/contas/listar");
				halt();
				return modelAndView(null, "contaslistar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioGrupoGasto ng = getFabrica().criarNegocioGrupoGasto();
				NegocioLancamentos nl = getFabrica().criarNegocioLancamentos();

				Lancamento l = nl.buscarLancamento(usr,
						Integer.parseInt(request.params(":id")));
				LancamentoViewModel lvw = new LancamentoViewModel();

				lvw.setGruposGasto(ng.listarGruposGasto(usr));
				lvw.setContas(l.getGrupo().getContas());
				lvw.setIdGrupo(l.getGrupo().getId());
				lvw.setLancamento(l);

				return modelAndView(lvw, "lancamentocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				int idGrupoGasto = Integer.parseInt(request
						.queryParams("grupo"));
				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();
				NegocioContas c = getFabrica().criarNegocioContas();

				LancamentoViewModel lancVwModel = new LancamentoViewModel();

				lancVwModel.setGruposGasto(g.listarGruposGasto(usr));
				lancVwModel.setIdGrupo(idGrupoGasto);
				GrupoGasto gruposelecionado = null;
				for (GrupoGasto grp : lancVwModel.getGruposGasto()) {
					if (grp.getId() == idGrupoGasto) {
						lancVwModel.setContas(grp.getContas());
						gruposelecionado = grp;
					}
				}
				Conta contaselecionada;
				if (request.queryParams().contains("conta")) {
					if (request.queryParams("conta").equals("Ativos")) {
						contaselecionada = c.listarContas(usr).getContaAtivos();
					} else if (request.queryParams("conta").equals("Passivos")) {
						contaselecionada = c.listarContas(usr)
								.getContaPassivos();
					} else if (request.queryParams("conta").equals("Despesas")) {
						contaselecionada = c.listarContas(usr)
								.getContaDespesas();
					} else if (request.queryParams("conta").equals(
							"Rendimentos")) {
						contaselecionada = c.listarContas(usr)
								.getContaRendimentos();
					} else {
						throw new RuntimeException("deu pobrema");
					}

					Lancamento l = new Lancamento();
					l.setConta(contaselecionada);
					l.setDescricao(request.queryParams("descricao"));
					l.setGrupo(gruposelecionado);
					l.setValor(Integer.parseInt(request.queryParams("valor")));
					l.setId(Integer.parseInt(request.params(":id")));

					NegocioLancamentos nl = getFabrica()
							.criarNegocioLancamentos();

					nl.alterarLancamento(l, usr);

					lancVwModel.setMensagem("Lancamento cadastro OK");
				}

				return modelAndView(lancVwModel, "lancamentocriar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				LancamentoViewModel lvw = new LancamentoViewModel();

				lvw.setGruposGasto(g.listarGruposGasto(usr));

				return modelAndView(lvw, "lancamentocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				int idGrupoGasto = Integer.parseInt(request
						.queryParams("grupo"));
				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();
				NegocioContas c = getFabrica().criarNegocioContas();

				LancamentoViewModel lancVwModel = new LancamentoViewModel();

				lancVwModel.setGruposGasto(g.listarGruposGasto(usr));
				lancVwModel.setIdGrupo(idGrupoGasto);
				GrupoGasto gruposelecionado = null;
				for (GrupoGasto grp : lancVwModel.getGruposGasto()) {
					if (grp.getId() == idGrupoGasto) {
						lancVwModel.setContas(grp.getContas());
						gruposelecionado = grp;
					}
				}
				Conta contaselecionada;
				if (request.queryParams().contains("conta")) {
					if (request.queryParams("conta").equals("Ativos")) {
						contaselecionada = c.listarContas(usr).getContaAtivos();
					} else if (request.queryParams("conta").equals("Passivos")) {
						contaselecionada = c.listarContas(usr)
								.getContaPassivos();
					} else if (request.queryParams("conta").equals("Despesas")) {
						contaselecionada = c.listarContas(usr)
								.getContaDespesas();
					} else if (request.queryParams("conta").equals(
							"Rendimentos")) {
						contaselecionada = c.listarContas(usr)
								.getContaRendimentos();
					} else {
						throw new RuntimeException("deu pobrema");
					}

					Lancamento l = new Lancamento();
					l.setConta(contaselecionada);
					l.setDescricao(request.queryParams("descricao"));
					l.setGrupo(gruposelecionado);
					l.setValor(Integer.parseInt(request.queryParams("valor")));

					NegocioLancamentos nl = getFabrica()
							.criarNegocioLancamentos();
					nl.criarLancamento(l, usr);

					lancVwModel.setMensagem("Lancamento cadastro OK");
				}

				return modelAndView(lancVwModel, "lancamentocriar.ftl");
			}
		});
	}
}
