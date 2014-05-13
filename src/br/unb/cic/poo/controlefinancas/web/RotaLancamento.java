package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.*;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;

public class RotaLancamento {
	public static void DefinirSubRotas(String nomeRota)
	{
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
	        	 int idGrupoGasto = Integer.parseInt(request.queryParams("grupo"));
Usuario          usr = (Usuario) (request.session().attribute("usuario"));
	        	 
	        	 NegocioGrupoGasto g = new NegocioGrupoGasto();
	        	 
	        	 LancamentoViewModel lvw = new LancamentoViewModel();
	        	 
	        	 lvw.setGruposGasto(g.listarGruposGasto(usr));
	        	 lvw.setIdGrupo(idGrupoGasto);
	        	 
	        	 
	        	 return modelAndView(null, "lancamentocriar.ftl");
	         }
	      }); 
	}
}
