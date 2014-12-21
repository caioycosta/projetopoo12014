package br.unb.cic.poo.controlefinancas.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class ConectorSQLite {
	private Connection con = null;
	
	public PreparedStatement preparar(String query, Object... parms) throws SQLException
	{
		PreparedStatement p1 = con.prepareStatement(query);
		
		for (int i = 0; i < parms.length; i++)
		{
			if (parms[i] == null) 
			{
				p1.setNull(i+1, java.sql.Types.VARCHAR);				
			} else if (parms[i] instanceof String)
			{
				p1.setString(i+1, (String)parms[i]);
			} else if (parms[i] instanceof Integer)
			{
				p1.setInt(i+1, (Integer)parms[i]);
			} else if (parms[i] instanceof Long)
			{
				p1.setLong(i+1, (Long)parms[i]);
			} else {
				throw new SQLException("Tipo nao preparavel");
			}
		}
		
		return p1;
	}
	
	/**
	 * Da commit e entao fecha a conexao
	 * 
	 * @throws SQLException
	 */
	public void CommitEFechar() throws SQLException {
		con.commit();
		Fechar();
	}

	/**
	 * Fecha a conexao
	 * 
	 * @throws SQLException
	 */
	public void Fechar() throws SQLException {
		con.setAutoCommit(true);
		con.close();
	}
	

	/**
	 * Da rollback e entao fecha a conexao.
	 */
	public void RollbackEFechar() {
		try {
			con.rollback();
			Fechar();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * TentaFechar a conexao sem soltar excecoes. Para uso em Catchs.
	 */
	public void TentarFecharDeNovo() {
		try {
			Fechar();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Cria uma nova conexão com o banco de dados.
	 */
	public void Conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig cfg = new SQLiteConfig();
			cfg.enforceForeignKeys(true);
			con = DriverManager.getConnection("jdbc:sqlite:test.s3db",
					cfg.toProperties());
			con.setAutoCommit(false);
		} catch (Exception e) {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					throw new RuntimeException(e);
				}
			}
			throw new RuntimeException(e);
		}
	}

}
