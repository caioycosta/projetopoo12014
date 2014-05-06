/**
 * 
 */
package br.unb.cic.poo.controlefinancas.web;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 * @author CaioYuri
 *
 */
public class FiltroAutenticacao extends Filter {
		    @Override
		    public void handle(Request request, Response response) {
		    	
		    	if (request.session().attribute("usuario") == null
		    			&& !request.pathInfo().equals( "/usuario/login")
		    			&& !request.pathInfo().equals("/usuario/cadastrar")) {
		            {
		            	response.redirect("/usuario/login");
		            	halt();
		            }
		    	}
		    }
}
