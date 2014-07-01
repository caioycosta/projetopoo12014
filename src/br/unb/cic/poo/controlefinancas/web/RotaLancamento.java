package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;

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
	
	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("Lancamento");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Criar");
	}
	
	/**
	 * @see br.unb.cic.poo.controlefinancas.web.Rota#definirSubRotasProtected(java.lang.String)
	 */
	@Override
	protected void definirSubRotasProtected(String nomeRota) {
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
				LancamentoViewModel vwb = new LancamentoViewModel();

				vwb.setGruposGasto(ng.listarGruposGasto(usr));
				vwb.setContas(l.getGrupo().getContas());
				vwb.setIdGrupo(l.getGrupo().getId());
				vwb.setLancamento(l);

				vwb.setInfo("Digite os novos dados do lan�amento.");
				vwb.setTitulo("Edi��o de lan�amento");
				vwb.setDescricaoView("Alterar um lan�amento existente");
				preencherViewModelBase(vwb);
				
				return modelAndView(vwb, "lancamentocriar.ftl");
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

				LancamentoViewModel vwb = new LancamentoViewModel();

				vwb.setGruposGasto(g.listarGruposGasto(usr));
				vwb.setIdGrupo(idGrupoGasto);
				GrupoGasto gruposelecionado = null;
				for (GrupoGasto grp : vwb.getGruposGasto()) {
					if (grp.getId() == idGrupoGasto) {
						vwb.setContas(grp.getContas());
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

					vwb.setMensagem("Lancamento cadastro OK");
				}

				
				vwb.setInfo("Digite os novos dados do lan�amento.");
				vwb.setTitulo("Edi��o de lan�amento");
				vwb.setDescricaoView("Alterar um lan�amento existente");
				preencherViewModelBase(vwb);
				return modelAndView(vwb, "lancamentocriar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				NegocioGrupoGasto g = getFabrica().criarNegocioGrupoGasto();

				LancamentoViewModel vwb = new LancamentoViewModel();

				vwb.setGruposGasto(g.listarGruposGasto(usr));

				
				vwb.setInfo("Digite os dados do novo lan�amento.");
				vwb.setTitulo("Cria��o de lan�amento");
				vwb.setDescricaoView("Criar um novo lan�amento");
				preencherViewModelBase(vwb);
				
				return modelAndView(vwb, "lancamentocriar.ftl");
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

				LancamentoViewModel vwb = new LancamentoViewModel();

				vwb.setGruposGasto(g.listarGruposGasto(usr));
				vwb.setIdGrupo(idGrupoGasto);
				GrupoGasto gruposelecionado = null;
				for (GrupoGasto grp : vwb.getGruposGasto()) {
					if (grp.getId() == idGrupoGasto) {
						vwb.setContas(grp.getContas());
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

					vwb.setMensagem("Lancamento cadastro OK");
				}

				vwb.setInfo("Digite os dados do novo lan�amento.");
				vwb.setTitulo("Cria��o de lan�amento");
				vwb.setDescricaoView("Criar um novo lan�amento");
				preencherViewModelBase(vwb);
				return modelAndView(vwb, "lancamentocriar.ftl");
			}
		});
	}

	@Override
	protected String getRotaPadrao() {
		
		return "/criar";
	}
}
