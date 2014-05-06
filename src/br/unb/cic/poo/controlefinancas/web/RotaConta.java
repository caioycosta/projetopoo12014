package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.*;
import br.unb.cic.poo.controlefinancas.negocio.NegocioContas;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerRoute;

public class RotaConta {
	public static void DefinirSubRotas(String nomeRota)
	{
		Spark.get(new FreeMarkerRoute(nomeRota + "/listar") {
	         @Override
	         public Object tempHandle(Request request, Response response) {
	        	 NegocioContas n = new NegocioContas();
	        	 
	        	 ConjuntoContas c = n.listarContas((Usuario) (request.session().attribute("usuario")));
	        	 
	        	 return modelAndView(c, "contaslistar.ftl");
	         }
	      }); 
	}
}
