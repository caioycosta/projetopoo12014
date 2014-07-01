package br.unb.cic.poo.controlefinancas.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.persistencia.PersistenciaSQLite;
import br.unb.cic.poo.controlefinancas.persistencia.UsuarioEmMemoria;

public class TestePersistenciaUsuario {

	@Test
	public void testesDeUsuario() {
		testesDeUsuario(new UsuarioEmMemoria());
		testesDeUsuario(new PersistenciaSQLite());
	}

	private void testesDeUsuario(IPersistenciaUsuario p)
	{
		Usuario usr = new Usuario();
		String no = "NOMETESTE" + System.currentTimeMillis();
		usr.setNome(no);
		String lo = "LOGINTESTE" + System.currentTimeMillis();
		usr.setLogin(lo);
		String se = "SENHATESTE" + System.currentTimeMillis();
		p.CadastrarUsuario(usr, se);
		
		Usuario u = p.loginUsuario(lo, se);
		assertNotNull(u);
		assertTrue(u.getId() != 0); 
		assertEquals(no, u.getNome());
		assertEquals(lo, u.getLogin());
		assertEquals(usr.getId(), u.getId());		
		
	}
	
}
