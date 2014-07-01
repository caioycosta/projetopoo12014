package br.unb.cic.poo.controlefinancas.web;

import java.util.ArrayList;

import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.negocio.NegocioUsuario;
import br.unb.cic.poo.controlefinancas.negocio.ResultadoOperacao;
import br.unb.cic.poo.controlefinancas.negocio.ResultadoOperacaoG;
import spark.*;
import spark.template.freemarker.FreeMarkerRoute;

/**
 * @author CaioYuri modulo de operacoes com usuario.
 */
public class RotaUsuario extends Rota {
	private void preencherViewModelBase(ViewModelBase vwb) {
		vwb.setCategoriaAtual("Usuario");
		vwb.setListaPaginas(new ArrayList<String>());
		vwb.getListaPaginas().add("Cadastrar");
		vwb.getListaPaginas().add("Login");
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.web.Rota#definirSubRotasProtected(java.lang.String)
	 */
	@Override
	protected void definirSubRotasProtected(String nomeRota) {
		Spark.get(new FreeMarkerRoute(nomeRota + "/cadastrar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				ViewModelBase vwb = new ViewModelBase();
				vwb.setInfo("Digite um nome, login e senha para o seu usuário.");
				vwb.setTitulo("Cadastro de usuário");
				vwb.setDescricaoView("Criar um novo usuário");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "usuariocadastrar.ftl");
			}
		});

		Spark.get(new FreeMarkerRoute(nomeRota + "/login") {
			@Override
			public Object tempHandle(Request request, Response response) {
				ViewModelBase vwb = new ViewModelBase();
				vwb.setInfo("Digite seu login e senha.");
				vwb.setTitulo("Login de usuário");
				vwb.setDescricaoView("Entrar com sua conta");
				preencherViewModelBase(vwb);

				return modelAndView(vwb, "usuariologin.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/login") {
			@Override
			public Object tempHandle(Request request, Response response) {
				NegocioUsuario ng = getFabrica().criarNegocioUsuario();
				ResultadoOperacaoG<Usuario> r = ng.loginUsuario(
						request.queryParams("login"),
						request.queryParams("senha"));
				UsuarioViewModel vwb;
				if (!r.isSucesso()) {
					vwb = new UsuarioViewModel(null, r.getMensagem());
				} else {
					vwb = new UsuarioViewModel(null, "Sucesso no login");

					request.session().attribute("usuario", r.getValor());
				}
				
				vwb.setInfo("Digite seu login e senha.");
				vwb.setTitulo("Login de usuário");
				vwb.setDescricaoView("Entrar com sua conta");
				preencherViewModelBase(vwb);				
				return modelAndView(vwb, "usuariologin.ftl");
			}
		});

		Spark.post(new FreeMarkerRoute(nomeRota + "/cadastrar") {
			@Override
			public Object tempHandle(Request request, Response response) {
				Usuario usr = new Usuario();
				usr.setNome(request.queryParams("nome"));
				usr.setLogin(request.queryParams("login"));
				String senha = request.queryParams("senha");

				NegocioUsuario ng = getFabrica().criarNegocioUsuario();
				ResultadoOperacao r = ng.CadastrarUsuario(usr, senha);
				UsuarioViewModel vwb;
				if (!r.isSucesso()) {
					vwb = new UsuarioViewModel(null, r.getMensagem());
				} else {
					vwb = new UsuarioViewModel(null, "Sucesso no cadastro. ID: "
							+ usr.getId());
				}

				vwb.setInfo("Digite um nome, login e senha para o seu usuário.");
				vwb.setTitulo("Cadastro de usuário");
				vwb.setDescricaoView("Criar um novo usuário");
				preencherViewModelBase(vwb);
				return modelAndView(vwb, "usuariocadastrar.ftl");
			}
		});
	}

	@Override
	protected String getRotaPadrao() {
		return "/login";
	}
}
