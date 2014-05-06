package br.unb.cic.poo.controlefinancas.dominio;

public class ContaDespesas extends Conta {

	public ContaDespesas() {
		
	}

	@Override
	public int getMultiplicador() {		
		return -1;
	}

	@Override
	public String getNome() {	
		return "Despesas";
	}

}
