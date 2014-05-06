package br.unb.cic.poo.controlefinancas.dominio;

public class ContaAtivos extends Conta {

	public ContaAtivos() {
	}

	@Override
	public int getMultiplicador() {
		return 1;
	}

	@Override
	public String getNome() {
		return "Ativos";
	}

}
