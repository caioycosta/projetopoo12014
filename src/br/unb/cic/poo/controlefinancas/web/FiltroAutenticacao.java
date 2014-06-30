package br.unb.cic.poo.controlefinancas.web;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * @author CaioYuri
 * filtro de autenticacao. executa antes de toda
 * requisicao para determinar se o usuario esta autorizado.
 */
public class FiltroAutenticacao  extends Filter {
		    @Override
		    public void handle(Request request, Response response) {
		    	
		    	if (request.session().attribute("usuario") == null
		    			&& !request.pathInfo().equals( "/usuario/login")
		    			&& !request.pathInfo().startsWith("/static")
		    			&& !request.pathInfo().equals("/usuario/cadastrar")) {
		            {
		            	response.redirect("/usuario/login");
		            	halt();
		            }
		    	}
		    }
}
