package br.unb.cic.poo.controlefinancas.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import br.unb.cic.poo.controlefinancas.dominio.ConjuntoContas;
import br.unb.cic.poo.controlefinancas.dominio.Conta;
import br.unb.cic.poo.controlefinancas.dominio.ContaAtivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaDespesas;
import br.unb.cic.poo.controlefinancas.dominio.ContaPassivos;
import br.unb.cic.poo.controlefinancas.dominio.ContaRendimentos;
import br.unb.cic.poo.controlefinancas.dominio.GrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.GrupoGastoDespesa;
import br.unb.cic.poo.controlefinancas.dominio.GrupoGastoReceita;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaLancamentos;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario;
import br.unb.cic.poo.controlefinancas.dominio.Lancamento;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import java.sql.*;

import org.sqlite.SQLiteConfig;

/**
 * @author CaioYuri Implementacao concreta da persistencia em um banco de dados
 *         SQLite.
 */
public class PersistenciaSQLite implements IPersistenciaConta,
		IPersistenciaGrupoGasto, IPersistenciaLancamentos, IPersistenciaUsuario {

	private Connection con = null;

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto#alterarGrupoGasto(br.unb.cic.poo.controlefinancas.dominio.GrupoGasto,
	 *      br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public void alterarGrupoGasto(GrupoGasto grp, Usuario usr) {
		String q = "UPDATE GRUPO_GASTO SET NOME = ?, ID_TIPO_GRUPO_GASTO = ? WHERE ID_GRUPO_GASTO = ?;";
		String d = "DELETE FROM GRUPO_CONTA WHERE ID_GRUPO_GASTO = ?;";
		Conectar();
		try {
			PreparedStatement p = con.prepareStatement(d); p.setInt(1, grp.getId());
			p.executeUpdate();

			p = con.prepareStatement(q);
			p.setString(1, grp.getNome());
			p.setString(2, grp.getTipoId());
			p.setInt(3, grp.getId());
			p.executeUpdate();

			for (Conta c : grp.getContas()) {
				String q1 = "INSERT INTO GRUPO_CONTA (ID_CONTA, ID_GRUPO_GASTO) VALUES (?, ?);";
				PreparedStatement p1 = con.prepareStatement(q1);
				p1.setInt(1, c.getId());
				p1.setInt(2, grp.getId());
				p1.executeUpdate();
			}

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaLancamentos#alterarLancamento(br.unb.cic.poo.controlefinancas.dominio.Lancamento,
	 *      br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public void alterarLancamento(Lancamento l, Usuario usr) {
		String q = "UPDATE LANCAMENTO "
				+ "SET VALOR = ?, DESCRICAO = ?, ID_CONTA = ?, "
				+ "ID_GRUPO_GASTO = ? WHERE ID_USUARIO = ? "
				+ "AND ID_LANCAMENTO = ?;";
		Conectar();
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1, l.getValor());
			ps.setString(2, l.getDescricao());
			ps.setInt(3, l.getConta().getId());
			if (l.getGrupo() == null)
				ps.setNull(4, java.sql.Types.INTEGER);
			else
				ps.setInt(4, l.getGrupo().getId());
			ps.setInt(5, usr.getId());
			ps.setInt(6, l.getId());
			ps.executeUpdate();

			ps = con.prepareStatement("UPDATE CONTA " + "SET SALDO = ? "
					+ "WHERE ID_CONTA = ? AND ID_USUARIO = ?;");
			ps.setInt(1, l.getConta().getSaldo());
			ps.setInt(2, l.getConta().getId());
			ps.setInt(3, usr.getId());
			ps.executeUpdate();

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaLancamentos#buscarLancamento(br.unb.cic.poo.controlefinancas.dominio.Usuario,
	 *      int)
	 */
	@Override
	public Lancamento buscarLancamento(Usuario usr, int parseInt) {
		String slc = "SELECT ID_LANCAMENTO, VALOR, DESCRICAO, ID_TIPO_CONTA, "
				+ "LANCAMENTO.ID_GRUPO_GASTO, ID_TIPO_GRUPO_GASTO, CONTA.SALDO, CONTA.ID_CONTA FROM "
				+ "LANCAMENTO INNER JOIN CONTA ON LANCAMENTO.ID_CONTA = CONTA.ID_CONTA "
				+ "INNER JOIN GRUPO_GASTO ON LANCAMENTO.ID_GRUPO_GASTO = GRUPO_GASTO.ID_GRUPO_GASTO "
				+ "WHERE LANCAMENTO.ID_USUARIO = ? AND ID_LANCAMENTO = ?;";
		Conectar();
		try {
			PreparedStatement ps = con.prepareStatement(slc);
			ps.setInt(1, usr.getId());
			ps.setInt(2, parseInt);
			ResultSet rs = ps.executeQuery();
			Lancamento l = null;
			while (rs.next()) {
				l = new Lancamento();
				l.setId(rs.getInt(1));
				l.setValor(rs.getInt(2));
				l.setDescricao(rs.getString(3));
				Conta x = null;
				switch (rs.getString(4)) {
				case "A":
					x = new ContaAtivos();
					break;
				case "D":
					x = new ContaDespesas();
					break;
				case "R":
					x = new ContaRendimentos();
					break;
				case "P":
					x = new ContaPassivos();
					break;
				}
				l.setConta(x);
				x.setSaldo(rs.getInt(7));
				x.setId(rs.getInt(8));

				if (rs.getObject(6) != null) {
					GrupoGasto g = rs.getString(6).equals("D") ? new GrupoGastoDespesa()
							: new GrupoGastoReceita();
					g.setId(rs.getInt(5));
					l.setGrupo(g);
				}
			}
			Fechar();
			return l;
		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario#CadastrarUsuario(br.unb.cic.poo.controlefinancas.dominio.Usuario,
	 *      java.lang.String)
	 */
	@Override
	public void CadastrarUsuario(Usuario usr, String senha) {
		Conectar();
		try {
			String insertStmt = "INSERT INTO USUARIO (NOME, LOGIN, SENHA) VALUES (?, ?, ?);";
			PreparedStatement ins = con.prepareStatement(insertStmt);
			ins.setString(1, usr.getNome());
			ins.setString(2, usr.getLogin());
			ins.setString(3, senha);
			ins.executeUpdate();
			ResultSet rst = ExecutarQuery("SELECT last_insert_rowid();");
			while (rst.next()) {
				usr.setId(rst.getInt(1));
			}
			CriarContasNovoUsuario(usr);
			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Da commit e entao fecha a conexao
	 * 
	 * @throws SQLException
	 */
	private void CommitEFechar() throws SQLException {
		con.commit();
		Fechar();
	}

	/**
	 * Cria uma nova conexão com o banco de dados.
	 */
	private void Conectar() {
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

	/**
	 * Cria o conjunto de contas de um novo usuário
	 * 
	 * @param usr
	 *            usuario que foi criado
	 */
	private void CriarContasNovoUsuario(Usuario usr) throws SQLException {
		String q = "INSERT INTO CONTA (ID_TIPO_CONTA, ID_USUARIO, NOME, SALDO) VALUES (?,?,?,?);";
		PreparedStatement p = con.prepareStatement(q);
		p.setString(1, "A");
		p.setInt(2, usr.getId());
		p.setString(3, "Ativos");
		p.setInt(4, 0);
		p.executeUpdate();

		p = con.prepareStatement(q);
		p.setString(1, "P");
		p.setInt(2, usr.getId());
		p.setString(3, "Passivos");
		p.setInt(4,0);
		p.executeUpdate();

		p = con.prepareStatement(q);
		p.setString(1, "R");
		p.setInt(2, usr.getId());
		p.setString(3, "Rendimentos");
		p.setInt(4, 0);
		p.executeUpdate();

		p = con.prepareStatement(q);
		p.setString(1, "D");
		p.setInt(2, usr.getId());
		p.setString(3, "Despesas");
		p.setInt(4, 0);
		p.executeUpdate();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto#criarGrupoGasto(br.unb.cic.poo.controlefinancas.dominio.Usuario,
	 *      br.unb.cic.poo.controlefinancas.dominio.GrupoGasto)
	 */
	@Override
	public void criarGrupoGasto(Usuario usr, GrupoGasto grp) {
		Conectar();
		try {
			String insertStmt = "INSERT INTO GRUPO_GASTO (NOME, ID_TIPO_GRUPO_GASTO, ID_USUARIO) VALUES (?, ?, ?);";
			PreparedStatement ins = con.prepareStatement(insertStmt);
			ins.setString(1, grp.getNome());
			ins.setString(2, grp.getTipoId());
			ins.setInt(3, usr.getId());
			ins.executeUpdate();
			ResultSet rst = ExecutarQuery("SELECT last_insert_rowid();");
			while (rst.next()) {
				grp.setId(rst.getInt(1));
			}

			for (Conta c : grp.getContas()) {
				String q = "INSERT INTO GRUPO_CONTA (ID_CONTA, ID_GRUPO_GASTO) VALUES (?, ?);";
				PreparedStatement p = con.prepareStatement(q);
				p.setInt(1, c.getId());
				p.setInt(2, grp.getId());
				p.executeUpdate();
			}

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaLancamentos#criarLancamento(br.unb.cic.poo.controlefinancas.dominio.Lancamento,
	 *      br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public void criarLancamento(Lancamento l, Usuario usr) {
		Conectar();
		String s = "INSERT INTO LANCAMENTO (DESCRICAO, VALOR, ID_CONTA, ID_GRUPO_GASTO, ID_USUARIO) VALUES (?,?,?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(s);
			ps.setString(1, l.getDescricao());
			ps.setInt(2, l.getValor());
			ps.setInt(3, l.getConta().getId());
			ps.setInt(5,usr.getId());
			if (l.getGrupo() == null)
				ps.setNull(4, java.sql.Types.INTEGER);
			else
				ps.setInt(4, l.getGrupo().getId());
			ps.executeUpdate();
			ps = con.prepareStatement("UPDATE CONTA " + "SET SALDO = ? "
					+ "WHERE ID_CONTA = ? AND ID_USUARIO = ?;");
			ps.setInt(1, l.getConta().getSaldo());
			ps.setInt(2, l.getConta().getId());
			ps.setInt(3, usr.getId());
			ps.executeUpdate();

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}

	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto#excluirGrupoGasto(br.unb.cic.poo.controlefinancas.dominio.Usuario,
	 *      int)
	 */
	@Override
	public void excluirGrupoGasto(Usuario usr, int parseInt) {
		String q = "DELETE FROM GRUPO_GASTO WHERE ID_USUARIO = ? AND ID_GRUPO_GASTO = ?;";
		Conectar();
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1, usr.getId());
			ps.setInt(2, parseInt);
			ps.executeUpdate();
			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param q
	 *            a query a ser executada
	 * @return ResultSet da consulta
	 * @throws SQLException
	 */
	private ResultSet ExecutarQuery(String q) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet rst = stmt.executeQuery(q);
		return rst;
	}

	/**
	 * Fecha a conexao
	 * 
	 * @throws SQLException
	 */
	private void Fechar() throws SQLException {
		con.setAutoCommit(true);
		con.close();
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaConta#listarContas(br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public ConjuntoContas listarContas(Usuario usr) {
		Conectar();
		try {
			ConjuntoContas cj = new ConjuntoContas();

			PreparedStatement ps = con
					.prepareStatement("SELECT ID_TIPO_CONTA, SALDO, ID_CONTA FROM CONTA WHERE ID_USUARIO = ?;");
			ps.setInt(1, usr.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				switch (rs.getString(1)) {
				case "A":
					ContaAtivos ca = new ContaAtivos();
					cj.setContaAtivos(ca);
					ca.setId(rs.getInt(3));
					ca.setSaldo(rs.getInt(2));
					break;
				case "P":
					ContaPassivos cp = new ContaPassivos();
					cj.setContaPassivos(cp);
					cp.setId(rs.getInt(3));
					cp.setSaldo(rs.getInt(2));
					break;
				case "D":
					ContaDespesas cd = new ContaDespesas();
					cj.setContaDespesas(cd);
					cd.setId(rs.getInt(3));
					cd.setSaldo(rs.getInt(2));
					break;
				case "R":
					ContaRendimentos cr = new ContaRendimentos();
					cj.setContaRendimentos(cr);
					cr.setId(rs.getInt(3));
					cr.setSaldo(rs.getInt(2));
					break;
				}
			}

			Conta x;

			x = cj.getContaRendimentos();
			preencherLancamentos(usr, x);
			x = cj.getContaDespesas();
			preencherLancamentos(usr, x);
			x = cj.getContaAtivos();
			preencherLancamentos(usr, x);
			x = cj.getContaPassivos();
			preencherLancamentos(usr, x);

			Fechar();

			return cj;

		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaGrupoGasto#listarGruposGasto(br.unb.cic.poo.controlefinancas.dominio.Usuario)
	 */
	@Override
	public Collection<GrupoGasto> listarGruposGasto(Usuario usr) {
		String p = "SELECT GRUPO_GASTO.ID_GRUPO_GASTO, GRUPO_GASTO.NOME, "
				+ "GRUPO_GASTO.ID_TIPO_GRUPO_GASTO, CONTA.ID_TIPO_CONTA, "
				+ "CONTA.ID_CONTA FROM "
				+ "GRUPO_GASTO LEFT JOIN GRUPO_CONTA ON GRUPO_GASTO.ID_GRUPO_GASTO = "
				+ "GRUPO_CONTA.ID_GRUPO_GASTO"
				+ " LEFT JOIN CONTA ON CONTA.ID_CONTA = GRUPO_CONTA.ID_CONTA "
				+ "WHERE GRUPO_GASTO.ID_USUARIO = ? ORDER BY GRUPO_GASTO.ID_GRUPO_GASTO;";
		Conectar();
		PreparedStatement ps;

		try {

			ps = con.prepareStatement(p);
			ps.setInt(1, usr.getId());
			ResultSet rs = ps.executeQuery();
			ArrayList<GrupoGasto> gg = new ArrayList<GrupoGasto>();

			ArrayList<Integer> gruposAdicionados = new ArrayList<Integer>();

			GrupoGasto g = null;
			while (rs.next()) {
				if (!gruposAdicionados.contains(rs.getInt(1))) {
					gruposAdicionados.add(rs.getInt(1));
					g = rs.getString(3).equals("D") ? new GrupoGastoDespesa()
							: new GrupoGastoReceita();
					g.setId(rs.getInt(1));
					g.setNome(rs.getString(2));
					gg.add(g);
				}
				if (rs.getObject(5) != null) {
					Conta c = rs.getString(4).equals("A") ? new ContaAtivos()
							: rs.getString(4).equals("P") ? new ContaPassivos()
									: rs.getString(4).equals("R") ? new ContaRendimentos()
											: new ContaDespesas();
					g.getContas().add(c);
					c.setId(rs.getInt(5));
				}
			}

			Fechar();

			return gg;
		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario#loginUsuario(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Usuario loginUsuario(String login, String senha) {
		Usuario usr = null;
		Conectar();
		try {
			String stmt = "SELECT ID_USUARIO, NOME, LOGIN FROM USUARIO WHERE LOGIN = ? AND SENHA = ?;";
			PreparedStatement ins = con.prepareStatement(stmt);
			ins.setString(1, login);
			ins.setString(2, senha);
			ResultSet rst = ins.executeQuery();

			while (rst.next()) {
				usr = new Usuario();
				usr.setId(rst.getInt(1));
				usr.setLogin(rst.getString(3));
				usr.setNome(rst.getString(2));
			}
			Fechar();
		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
		return usr;
	}

	/**
	 * @param usr
	 * @param x
	 * @throws SQLException
	 *             busca os lancamentos de uma conta
	 */
	private void preencherLancamentos(Usuario usr, Conta x) throws SQLException {
		String buscaLancto = "SELECT ID_LANCAMENTO, DESCRICAO, VALOR FROM LANCAMENTO WHERE ID_USUARIO = ? AND ID_CONTA = ?;";
		PreparedStatement ps1 = con.prepareStatement(buscaLancto);
		ps1.setInt(1, usr.getId());
		ps1.setInt(2, x.getId());
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			Lancamento l = new Lancamento();
			l.setId(rs1.getInt(1));
			l.setDescricao(rs1.getString(2));
			l.setValor(rs1.getInt(3));
			l.setConta(x);
			x.getLancamentos().add(l);
		}
	}

	/**
	 * Da rollback e entao fecha a conexao.
	 */
	private void RollbackEFechar() {
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
	private void TentarFecharDeNovo() {
		try {
			Fechar();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see br.unb.cic.poo.controlefinancas.dominio.IPersistenciaLancamentos#excluirLancamento(br.unb.cic.poo.controlefinancas.dominio.Usuario,
	 *      br.unb.cic.poo.controlefinancas.dominio.Lancamento)
	 */
	@Override
	public void excluirLancamento(Usuario usr, Lancamento l) {
		Conectar();
		try {
			PreparedStatement ps = con
					.prepareStatement("DELETE FROM LANCAMENTO "
							+ "WHERE ID_USUARIO = ? AND "
							+ "ID_LANCAMENTO = ?;");
			ps.setInt(1, usr.getId());
			ps.setInt(2, l.getId());
			ps.executeUpdate();

			ps = con.prepareStatement("UPDATE CONTA " + "SET SALDO = ? "
					+ "WHERE ID_CONTA = ? AND ID_USUARIO = ?;");
			ps.setInt(1, l.getConta().getSaldo());
			ps.setInt(2, l.getConta().getId());
			ps.setInt(3, usr.getId());
			ps.executeUpdate();

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

}
