package br.unb.cic.poo.controlefinancas.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.unb.cic.poo.controlefinancas.dominio.RelatorioPatrimonial;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.negocio.NegocioRelatorios;

public class TesteNegocioRelatorio {

	@Test
	public void testGerarRelatorio() {
		StubPersistenciaConta st = new StubPersistenciaConta(
				30, 20);
		NegocioRelatorios nr = new NegocioRelatorios(st);
		
		Usuario usr = new Usuario();
		usr.setNome("umnome");
		usr.setLogin("umlogin");
		usr.setId(999);
		
		RelatorioPatrimonial rp = nr.gerarRelatorio(usr);
		
		assertSame(usr, st.usuarioFornecido);
		assertEquals("umnome", st.usuarioFornecido.getNome());
		assertEquals("umlogin", st.usuarioFornecido.getLogin());
		assertEquals(999, st.usuarioFornecido.getId());
		
		assertEquals(30, rp.getSaldoAtivos());
		assertEquals(20, rp.getSaldoPassivos());
		assertEquals(10, rp.getPatrimonioLiquido());
	}

}
