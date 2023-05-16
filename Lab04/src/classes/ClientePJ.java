package classes;

import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente {
	final private String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;

	public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, Date dataFundacao, int qtdeFuncionarios) {
		super(nome, endereco, listaVeiculos);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.setQtdeFuncionarios(qtdeFuncionarios);
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getId() {
		return cnpj;
	}
	
	public String getTipo() {
		return "PJ";
	}
	
	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	public double calculaScore() {
		return (CalcSeguro.VALOR_BASE.valor() * (1 + ((double)getQtdeFuncionarios()/100)) * getListaVeiculos().size());
	}
	
	public String toString() {
		return ("Nome: " + getNome() + "\n" +
				"Cnpj: " + getId() + "\n" +
				"Data da Fundação : " + getDataFundacao() + "\n" +
				"Endereco: " + getEndereco() + "\n" +
				"Veículos:\n" +
					stringListaVeiculos()
				);
	}

	
	
	
}
