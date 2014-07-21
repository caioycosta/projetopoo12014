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
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaPeriodo;
import br.unb.cic.poo.controlefinancas.dominio.IPersistenciaUsuario;
import br.unb.cic.poo.controlefinancas.dominio.Lancamento;
import br.unb.cic.poo.controlefinancas.dominio.Periodo;
import br.unb.cic.poo.controlefinancas.dominio.Usuario;
import java.sql.*;

import org.sqlite.SQLiteConfig;

/**
 * @author CaioYuri Implementacao concreta da persistencia em um banco de dados
 *         SQLite.
 */
public class PersistenciaSQLite implements IPersistenciaConta,
		IPersistenciaGrupoGasto, IPersistenciaLancamentos,
		IPersistenciaUsuario, IPersistenciaPeriodo {

	@SuppressWarnings("deprecation")
	private java.sql.Date soD(java.sql.Date d)
	{
		/*d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		d.setTime(d.getTime() - (d.getTime() % 1000)); */
		return new java.sql.Date(d.getYear(), d.getMonth(), d.getDate());
	}
	
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
			PreparedStatement p = con.prepareStatement(d);
			p.setInt(1, grp.getId());
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
				+ "ID_GRUPO_GASTO = ?, DATA = ? WHERE ID_USUARIO = ? "
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

			// Monta objeto java.sql.Date a partir de java.util.Date
			ps.setDate(5, new java.sql.Date(l.getData().getTime()));

			ps.setInt(6, usr.getId());
			ps.setInt(7, l.getId());

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
				+ "LANCAMENTO.ID_GRUPO_GASTO, ID_TIPO_GRUPO_GASTO, CONTA.ID_CONTA AS ID_CONTA, "
				+ "DATA FROM "
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

				// converte objeto java.sql.Date para java.util.Date
				// e preenche o lan�amento com a informa��o
				l.setData(new java.util.Date(rs.getDate("DATA").getTime()));

				x.setId(rs.getInt("ID_CONTA"));

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
	 * Cria uma nova conex�o com o banco de dados.
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
	 * Cria o conjunto de contas de um novo usu�rio
	 * 
	 * @param usr
	 *            usuario que foi criado
	 */
	private void CriarContasNovoUsuario(Usuario usr) throws SQLException {
		String q = "INSERT INTO CONTA (ID_TIPO_CONTA, ID_USUARIO, NOME) VALUES (?,?,?);";
		PreparedStatement p = con.prepareStatement(q);
		p.setString(1, "A");
		p.setInt(2, usr.getId());
		p.setString(3, "Ativos");

		p.executeUpdate();

		p = con.prepareStatement(q);
		p.setString(1, "P");
		p.setInt(2, usr.getId());
		p.setString(3, "Passivos");

		p.executeUpdate();

		p = con.prepareStatement(q);
		p.setString(1, "R");
		p.setInt(2, usr.getId());
		p.setString(3, "Rendimentos");

		p.executeUpdate();

		p = con.prepareStatement(q);
		p.setString(1, "D");
		p.setInt(2, usr.getId());
		p.setString(3, "Despesas");

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
		String s = "INSERT INTO LANCAMENTO (DESCRICAO, VALOR, ID_CONTA, ID_GRUPO_GASTO, ID_USUARIO, DATA) VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(s);
			ps.setString(1, l.getDescricao());
			ps.setInt(2, l.getValor());
			ps.setInt(3, l.getConta().getId());
			ps.setInt(5, usr.getId());

			// Monta objeto java.sql.Date a partir de java.util.Date
			ps.setDate(6, new java.sql.Date(l.getData().getTime()));

			if (l.getGrupo() == null)
				ps.setNull(4, java.sql.Types.INTEGER);
			else
				ps.setInt(4, l.getGrupo().getId());
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
	public ConjuntoContas listarContas(Usuario usr, Periodo p) {
		Conectar();
		try {
			ConjuntoContas cj = new ConjuntoContas();

			PreparedStatement ps = con
					.prepareStatement("SELECT ID_TIPO_CONTA, ID_CONTA FROM CONTA WHERE ID_USUARIO = ?;");
			ps.setInt(1, usr.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				switch (rs.getString(1)) {
				case "A":
					ContaAtivos ca = new ContaAtivos();
					cj.setContaAtivos(ca);
					ca.setId(rs.getInt("ID_CONTA"));

					break;
				case "P":
					ContaPassivos cp = new ContaPassivos();
					cj.setContaPassivos(cp);
					cp.setId(rs.getInt("ID_CONTA"));

					break;
				case "D":
					ContaDespesas cd = new ContaDespesas();
					cj.setContaDespesas(cd);
					cd.setId(rs.getInt("ID_CONTA"));

					break;
				case "R":
					ContaRendimentos cr = new ContaRendimentos();
					cj.setContaRendimentos(cr);
					cr.setId(rs.getInt("ID_CONTA"));

					break;
				}
			}

			Conta x;
			if (p != null) {
				x = cj.getContaRendimentos();
				preencherLancamentos(usr, x, p);
				x = cj.getContaDespesas();
				preencherLancamentos(usr, x, p);
				x = cj.getContaAtivos();
				preencherLancamentos(usr, x, p);
				x = cj.getContaPassivos();
				preencherLancamentos(usr, x, p);

				preencherSaldo(cj, p);
			}

			Fechar();

			return cj;

		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
	}

	private void preencherSaldo(ConjuntoContas cj, Periodo p)
	throws SQLException {
		Conta x = cj.getContaRendimentos();		
		int totalRenda = getTotalAcumulado(p, x);		
		x = cj.getContaDespesas();		
		int totalDespesa = getTotalAcumulado(p, x);		
		cj.setSaldo(totalRenda - totalDespesa);
		
		
		ContaAtivos ca = cj.getContaAtivos();
		ContaPassivos cp = cj.getContaPassivos();
		
		ca.setSaldoAcumulado(getTotalAcumulado(p, ca));
		cp.setSaldoAcumulado(getTotalAcumulado(p, cp));
	}

	/**
	 * @param p
	 * @param x
	 * @throws SQLException
	 */
	private int getTotalAcumulado(Periodo p, Conta x) throws SQLException {
		String sumQ = 
				"SELECT SUM(VALOR * TIPO_GRUPO_GASTO.MULTIPLICADOR * TIPO_CONTA.MULTIPLICADOR) AS TOTAL" +
				" FROM LANCAMENTO INNER JOIN CONTA ON CONTA.ID_CONTA = LANCAMENTO.ID_CONTA " +
				" INNER JOIN GRUPO_GASTO ON GRUPO_GASTO.ID_GRUPO_GASTO = LANCAMENTO.ID_GRUPO_GASTO " +
				" INNER JOIN TIPO_CONTA ON CONTA.ID_TIPO_CONTA = TIPO_CONTA.ID_TIPO_CONTA" +
				" INNER JOIN TIPO_GRUPO_GASTO ON TIPO_GRUPO_GASTO.ID_TIPO_GRUPO_GASTO = GRUPO_GASTO.ID_TIPO_GRUPO_GASTO" +
				"" +
				" WHERE LANCAMENTO.ID_CONTA = ?" +
				" AND DATA <= ?" +
				" ORDER BY DATA;";
		PreparedStatement ps1 = con.prepareStatement(sumQ);
		
		ps1.setInt(1, x.getId());
		ps1.setDate(2, new java.sql.Date(p.getDataFim().getTime()));
		
		ResultSet rs1 = ps1.executeQuery();
		int total = 0;
		while (rs1.next()) {
			total = (rs1.getInt(1));
		}
		return total;
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
	 * @param p
	 * @throws SQLException
	 *             busca os lancamentos de uma conta
	 */
	private void preencherLancamentos(Usuario usr, Conta x, Periodo p)
			throws SQLException {
		String buscaLancto = "SELECT ID_LANCAMENTO, DESCRICAO, VALOR, DATA FROM LANCAMENTO WHERE" +
				" ID_USUARIO = ? AND ID_CONTA = ?" +
				" AND DATA >= ? AND DATA <= ?" +
				" ORDER BY DATA;";
		PreparedStatement ps1 = con.prepareStatement(buscaLancto);
		ps1.setInt(1, usr.getId());
		ps1.setInt(2, x.getId());

		ps1.setDate(3, new java.sql.Date(p.getDataInicio().getTime()));
		ps1.setDate(4, new java.sql.Date(p.getDataFim().getTime()));
		ResultSet rs1 = ps1.executeQuery();

		while (rs1.next()) {
			Lancamento l = new Lancamento();
			l.setId(rs1.getInt(1));
			l.setDescricao(rs1.getString(2));
			l.setValor(rs1.getInt(3));
			l.setConta(x);

			// converte objeto java.sql.Date para java.util.Date
			// e preenche o lan�amento com a informa��o
			l.setData(new java.util.Date(rs1.getDate("DATA").getTime()));

			x.getLancamentos().add(l);
		}
		
		String sumQ = 
				"SELECT SUM(VALOR * TIPO_GRUPO_GASTO.MULTIPLICADOR * TIPO_CONTA.MULTIPLICADOR) AS TOTAL" +
				" FROM LANCAMENTO INNER JOIN CONTA ON CONTA.ID_CONTA = LANCAMENTO.ID_CONTA " +
				" INNER JOIN GRUPO_GASTO ON GRUPO_GASTO.ID_GRUPO_GASTO = LANCAMENTO.ID_GRUPO_GASTO " +
				" INNER JOIN TIPO_CONTA ON CONTA.ID_TIPO_CONTA = TIPO_CONTA.ID_TIPO_CONTA" +
				" INNER JOIN TIPO_GRUPO_GASTO ON TIPO_GRUPO_GASTO.ID_TIPO_GRUPO_GASTO = GRUPO_GASTO.ID_TIPO_GRUPO_GASTO" +
				"" +
				" WHERE LANCAMENTO.ID_USUARIO = ? AND LANCAMENTO.ID_CONTA = ?" +
				" AND DATA >= ? AND DATA <= ?" +
				" ORDER BY DATA;";
		ps1 = con.prepareStatement(sumQ);
		
		ps1.setInt(1, usr.getId());
		ps1.setInt(2, x.getId());
		ps1.setDate(3, new java.sql.Date(p.getDataInicio().getTime()));
		ps1.setDate(4, new java.sql.Date(p.getDataFim().getTime()));
		
		rs1 = ps1.executeQuery();
		while (rs1.next()) {
			x.setTotal(rs1.getInt(1));
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

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Collection<Periodo> buscarPeriodos(Usuario usr) {
		ArrayList<Periodo> p = new ArrayList<Periodo>();
		Conectar();
		try {
			String stmt = " SELECT ID_PERIODO, DATA_INICIO, DATA_FIM "
					+ "FROM PERIODOS_CONTABILIZACAO WHERE ID_USUARIO = ? ORDER BY DATA_INICIO"
					+ ";";
			PreparedStatement ins = con.prepareStatement(stmt);
			ins.setInt(1, usr.getId());
			ResultSet rst = ins.executeQuery();

			while (rst.next()) {
				Periodo m = new Periodo();
				m.setIdPeriodo(rst.getInt(1));
				m.setDataFim(new Date(rst.getDate(3).getTime()));
				m.setDataInicio(new Date(rst.getDate(2).getTime()));
				p.add(m);
			}
			Fechar();
		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
		return p;
	}

	@Override
	public void CadastrarPeriodo(Usuario usr, Periodo p) {
		Conectar();
		String s = "INSERT INTO PERIODOS_CONTABILIZACAO (DATA_INICIO, DATA_FIM, ID_USUARIO) VALUES (?,?,?);";
		try {
			PreparedStatement ps = con.prepareStatement(s);
			ps.setDate(1, new java.sql.Date(p.getDataInicio().getTime()));
			ps.setDate(2, new java.sql.Date(p.getDataFim().getTime()));
			ps.setInt(3, usr.getId());

			ps.executeUpdate();

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void EditarPeriodo(Usuario usr, Periodo p) {
		Conectar();
		String s = "UPDATE PERIODOS_CONTABILIZACAO SET "
				+ "DATA_INICIO = ?, DATA_FIM = ?, "
				+ "ID_USUARIO = ? WHERE ID_PERIODO = ?;";
		try {
			PreparedStatement ps = con.prepareStatement(s);
			ps.setDate(1, new java.sql.Date(p.getDataInicio().getTime()));
			ps.setDate(2, new java.sql.Date(p.getDataFim().getTime()));
			ps.setInt(3, usr.getId());
			ps.setInt(4, p.getIdPeriodo());

			ps.executeUpdate();

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void ExcluirPeriodo(Usuario usr, Periodo p) {
		Conectar();
		try {
			PreparedStatement ps = con
					.prepareStatement("DELETE FROM PERIODOS_CONTABILIZACAO "
							+ "WHERE ID_USUARIO = ? AND " + "ID_PERIODO = ?;");
			ps.setInt(1, usr.getId());
			ps.setInt(2, p.getIdPeriodo());
			ps.executeUpdate();

			CommitEFechar();
		} catch (SQLException e) {
			RollbackEFechar();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Periodo BuscarPeriodo(Usuario usr, int id) {
		Periodo p = null;
		Conectar();
		try {
			String stmt = " SELECT ID_PERIODO, DATA_INICIO, DATA_FIM "
					+ "FROM PERIODOS_CONTABILIZACAO WHERE ID_PERIODO = ? AND ID_USUARIO = ?"
					+ ";";
			PreparedStatement ins = con.prepareStatement(stmt);
			ins.setInt(1, id);
			ins.setInt(2, usr.getId());
			ResultSet rst = ins.executeQuery();

			while (rst.next()) {
				p = new Periodo();
				p.setIdPeriodo(id);
				p.setDataFim(new Date(rst.getDate(3).getTime()));
				p.setDataInicio(new Date(rst.getDate(2).getTime()));
			}
			Fechar();
		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
		return p;
	}

	@Override
	public Periodo BuscarPeriodoCorrente(Usuario usr) {
		java.util.Date d = new java.util.Date();
		Periodo p = null;
		Conectar();
		try {
			String stmt = " SELECT ID_PERIODO, DATA_INICIO, DATA_FIM "
					+ "FROM PERIODOS_CONTABILIZACAO WHERE ID_USUARIO = ?"
					+ " AND DATA_INICIO <= ? AND DATA_FIM >= ?;";
			PreparedStatement ins = con.prepareStatement(stmt);
			ins.setInt(1, usr.getId());
			ins.setDate(2, soD(new java.sql.Date(d.getTime())));
			ins.setDate(3, soD(new java.sql.Date(d.getTime())));
			ResultSet rst = ins.executeQuery();

			while (rst.next()) {
				p = new Periodo();
				p.setIdPeriodo(rst.getInt("ID_PERIODO"));
				p.setDataFim(new Date(rst.getDate("DATA_FIM").getTime()));
				p.setDataInicio(new Date(rst.getDate("DATA_INICIO").getTime()));
			}
			Fechar();
		} catch (SQLException e) {
			TentarFecharDeNovo();
			throw new RuntimeException(e);
		}
		return p;
	}

}
