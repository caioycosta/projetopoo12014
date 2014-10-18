package br.unb.cic.poo.controlefinancas.dominio;

public class GrupoComValor {
	GrupoGasto grupo;
	public GrupoGasto getGrupo() {
		return grupo;
	}
	public void setGrupo(GrupoGasto grupo) {
		this.grupo = grupo;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	int valor;
}
