package br.unb.cic.poo.controlefinancas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.unb.cic.poo.controlefinancas.dominio.Conta;
import br.unb.cic.poo.controlefinancas.dominio.GrupoComValor;
import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.GrupoGastoDespesa;
import br.unb.cic.poo.controlefinancas.dominio.GrupoGastoReceita;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.RelatorioGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import br.unb.cic.poo.controlefinancas.dominio.interfaces.IPersistenciaRelatorios;

public class PersistenciaRelatorios implements IPersistenciaRelatorios {

	private Usuario usuario;

	public PersistenciaRelatorios(Usuario u)
	{
		this.usuario = u;
	}
	
	@Override
	public RelatorioGrupoGasto gerarRelatorioGrupoGasto(Periodo per, Conta ct) {
		ConectorSQLite c = new ConectorSQLite();
		c.Conectar();
		RelatorioGrupoGasto re = null;
		try {
			PreparedStatement p = c.preparar(
					"SELECT SUM(L.VALOR * T.MULTIPLICADOR * TC.MULTIPLICADOR) AS VALOR, G.NOME, G.ID_GRUPO_GASTO, G.ID_TIPO_GRUPO_GASTO "
					+ "FROM LANCAMENTO L, "
					+ "GRUPO_GASTO G, "
					+ "TIPO_GRUPO_GASTO T,"
					+ "CONTA C,"
					+ "TIPO_CONTA TC "
					+ "WHERE L.ID_GRUPO_GASTO = G.ID_GRUPO_GASTO AND "
					+ "G.ID_TIPO_GRUPO_GASTO = T.ID_TIPO_GRUPO_GASTO AND "
					+ "L.ID_CONTA = C.ID_CONTA AND "
					+ "C.ID_TIPO_CONTA = TC.ID_TIPO_CONTA AND "
					+ "L.ID_USUARIO = ? AND "
					+ "L.ID_CONTA = ? AND DATA >= ? "
					+ "AND DATA <= ? GROUP BY G.ID_GRUPO_GASTO;", 
					usuario.getId(), ct.getId(), per.getDataInicio().paraTime(), per.getDataFim().paraTime());
						
			ResultSet r = p.executeQuery();
			ArrayList<GrupoComValor> valores = new ArrayList<GrupoComValor>();
			
			while (r.next()) {
			    GrupoGasto g = r.getString("ID_TIPO_GRUPO_GASTO") == "D" ? new GrupoGastoDespesa() : new GrupoGastoReceita();    			    		
			    g.setId(r.getInt("ID_GRUPO_GASTO"));
			    g.setNome(r.getString("NOME"));			
			    GrupoComValor gv = new GrupoComValor();
			    gv.setGrupo(g);
			    gv.setValor(r.getInt("VALOR"));
			    valores.add(gv);
			}
			
			re = new RelatorioGrupoGasto();
			re.setGastoPorGrupo(valores);
			
			c.Fechar();
		} catch (SQLException e) {
			c.TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
				
		return re;
	}

}
