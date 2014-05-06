package br.unb.cic.poo.controlefinancas.web;

import java.util.*;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;
import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.NegocioContas;
import br.unb.cic.poo.controlefinancas.negocio.NegocioGrupoGasto;

public class RotaGrupoGasto {
	public static void DefinirSubRotas(String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				return modelAndView(null, "grupocriar.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/criar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioContas n = new NegocioContas();
				NegocioGrupoGasto g = new NegocioGrupoGasto();

				Usuario usr = (Usuario) (request.session().attribute("usuario"));

				ConjuntoContas cj = n.listarContas(usr);

				GrupoGasto grp;
				if (request.queryParams("tipo").equals("Despesa")) {
					grp = new GrupoGastoDespesa();
				} else
					grp = new GrupoGastoReceita();

				grp.setNome(request.queryParams("nome"));

				Collection<Conta> contas = new ArrayList<Conta>();

				if (request.queryParams().contains("contaAtivos") &&
						request.queryParams("contaAtivos").equals("1")) {
						contas.add(cj.getContaAtivos());
				}

				if (request.queryParams().contains("contaPassivos") &&
						request.queryParams("contaPassivos").equals("1")) {
					contas.add(cj.getContaPassivos());
				}
				
				if (request.queryParams().contains("contaRendimentos") &&
						request.queryParams("contaRendimentos").equals("1")) {
					contas.add(cj.getContaRendimentos());
				}
				
				if (request.queryParams().contains("contaDespesas") &&
						request.queryParams("contaDespesas").equals("1")) {
					contas.add(cj.getContaDespesas());
				}
				
				grp.setContas(contas);
				
				g.criarGrupoGasto(usr, grp);
				
				return modelAndView(new GrupoViewModel("Grupo criado com sucesso"), "grupocriar.ftl");
			}
		});
	}
}
