/**
 * 
 */
package br.unb.cic.poo.controlefinancas.web;

import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.negocio.NegocioUsuario;
import br.unb.cic.poo.controlefinancas.negocio.ResultadoOperacao;
import br.unb.cic.poo.controlefinancas.negocio.ResultadoOperacaoG;
import spark.*;
import spark.template.freemarker.FreeMarkerRoute;

/**
 * @author CaioYuri
 *
 */
public class RotaUsuario {
	public static void DefinirSubRotas(String nomeRota)
	{
		Spark.get(new FreeMarkerRoute(nomeRota + "/cadastrar") {
	         @Override
	         public Object tempHandle(Request request, Response response) {
	        	 return modelAndView(null, "usuariocadastrar.ftl");
	         }
	      }); 
		
		Spark.get(new FreeMarkerRoute(nomeRota + "/login") {
			@Override
			public Object tempHandle(Request request, Response response) {
				return modelAndView(null, "usuariologin.ftl");
			}
		});
		
		Spark.post(new FreeMarkerRoute(nomeRota + "/login") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioUsuario ng = new NegocioUsuario();
				ResultadoOperacaoG<Usuario> r = ng.loginUsuario(request.queryParams("login"), request.queryParams("senha"));
				UsuarioViewModel vw;
				if (!r.isSucesso())
	        	 {
	        		 vw = new UsuarioViewModel(null, r.getMensagem());
	        	 }
	        	 else {
	        		 vw = new UsuarioViewModel(null, "Sucesso no login");
	        		 
	        		 request.session().attribute("usuario", r.getValor());
	        	 }				
				return modelAndView(vw, "usuariologin.ftl");
			}
		});
		
		Spark.post(new FreeMarkerRoute(nomeRota + "/cadastrar") {
	         @Override
	         public Object tempHandle(Request request, Response response) {
	        	 Usuario usr = new Usuario();
	        	 usr.setNome(request.queryParams("nome"));
	        	 usr.setLogin(request.queryParams("login"));
	        	 String senha = request.queryParams("senha");
	        	 
	        	 NegocioUsuario ng = new NegocioUsuario();
	        	 ResultadoOperacao r = ng.CadastrarUsuario(usr, senha);
	        	 UsuarioViewModel vw;
	        	 if (!r.isSucesso())
	        	 {
	        		 vw = new UsuarioViewModel(null, r.getMensagem());
	        	 }
	        	 else {
	        		 vw = new UsuarioViewModel(null, "Sucesso no cadastro. ID: " + usr.getId());
	        	 }
	        	  
	        	 return modelAndView(vw, "usuariocadastrar.ftl");
	         }
	      }); 
	}
}
