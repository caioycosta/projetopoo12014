package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.*;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;

public class RotaLancamento {
	public static void DefinirSubRotas(String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/editar/:id") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = (Usuario) (request.session().attribute("usuario"));
				
				NegocioGrupoGasto ng = new NegocioGrupoGasto();
				NegocioLancamentos nl = new NegocioLancamentos();
				
				Lancamento l = nl.buscarLancamento(usr, Integer.parseInt(request.params(":id")));				
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

				NegocioGrupoGasto g = new NegocioGrupoGasto();
				NegocioContas c = new NegocioContas();
				
				LancamentoViewModel lancVwModel = new LancamentoViewModel();

				lancVwModel.setGruposGasto(g.listarGruposGasto(usr));
				lancVwModel.setIdGrupo(idGrupoGasto);
								GrupoGasto gruposelecionado = null;
				for (GrupoGasto grp : lancVwModel.getGruposGasto())
				{
					if (grp.getId() == idGrupoGasto)
					{
						lancVwModel.setContas(grp.getContas());
						gruposelecionado = grp;
					}
				}
				Conta contaselecionada;
				if (request.queryParams().contains("conta"))
				{
					if (request.queryParams("conta").equals("Ativos"))
					{
						contaselecionada = c.listarContas(usr).getContaAtivos();
					} else if (request.queryParams("conta").equals("Passivos"))
					{
						contaselecionada = c.listarContas(usr).getContaPassivos();
					} else if (request.queryParams("conta").equals("Despesas"))
					{
						contaselecionada = c.listarContas(usr).getContaDespesas();
					} else if (request.queryParams("conta").equals("Rendimentos"))
					{
						contaselecionada = c.listarContas(usr).getContaRendimentos();
					} else {
						throw new RuntimeException("deu pobrema");
					}
					
					Lancamento l = new Lancamento();
					l.setConta(contaselecionada);
					l.setDescricao(request.queryParams("descricao"));
					l.setGrupo(gruposelecionado);
					l.setValor(Integer.parseInt(request.queryParams("valor")));
					l.setId(Integer.parseInt(request.params(":id")));
					
					NegocioLancamentos nl = new NegocioLancamentos();
					
					
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

				NegocioGrupoGasto g = new NegocioGrupoGasto();

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

				NegocioGrupoGasto g = new NegocioGrupoGasto();
				NegocioContas c = new NegocioContas();
				
				LancamentoViewModel lancVwModel = new LancamentoViewModel();

				lancVwModel.setGruposGasto(g.listarGruposGasto(usr));
				lancVwModel.setIdGrupo(idGrupoGasto);
								GrupoGasto gruposelecionado = null;
				for (GrupoGasto grp : lancVwModel.getGruposGasto())
				{
					if (grp.getId() == idGrupoGasto)
					{
						lancVwModel.setContas(grp.getContas());
						gruposelecionado = grp;
					}
				}
				Conta contaselecionada;
				if (request.queryParams().contains("conta"))
				{
					if (request.queryParams("conta").equals("Ativos"))
					{
						contaselecionada = c.listarContas(usr).getContaAtivos();
					} else if (request.queryParams("conta").equals("Passivos"))
					{
						contaselecionada = c.listarContas(usr).getContaPassivos();
					} else if (request.queryParams("conta").equals("Despesas"))
					{
						contaselecionada = c.listarContas(usr).getContaDespesas();
					} else if (request.queryParams("conta").equals("Rendimentos"))
					{
						contaselecionada = c.listarContas(usr).getContaRendimentos();
					} else {
						throw new RuntimeException("deu pobrema");
					}
					
					Lancamento l = new Lancamento();
					l.setConta(contaselecionada);
					l.setDescricao(request.queryParams("descricao"));
					l.setGrupo(gruposelecionado);
					l.setValor(Integer.parseInt(request.queryParams("valor")));
					
					NegocioLancamentos nl = new NegocioLancamentos();
					nl.criarLancamento(l, usr);
					
					lancVwModel.setMensagem("Lancamento cadastro OK");
				}
				
				
				return modelAndView(lancVwModel, "lancamentocriar.ftl");
			}
		});
	}
}
