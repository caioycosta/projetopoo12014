package br.unb.cic.poo.controlefinancas.dominio;

/**
 * @author CaioYuri
 * representa um usuario do sistema
 */
public class Usuario {
	private String nome;	
	private String login;
	/**
	 * @return o login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login o login para setar
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	private int id;
	
	
	

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 *  constroi um novo obj usuario
	 */
	public Usuario()
	{
		
	}
	
	/**
	 * @param nome
	 * @param id
	 * constroi um novo obj usuario
	 */
	public Usuario(String nome, int id)
	{
		setId(id);
		setNome(nome);
	}
	
	/**
	 * @return o id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id o id para setar
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return o name
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome o name para setar
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
