package classes;

import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente {
	final private String cnpj;
	private Date dataFundacao;

	public ClientePJ(String nome, String endereco, Date dataLicenca, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cnpj, Date dataFundacao) {
		super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	private boolean has_valid_dv (String cpf) {
		int i, sum, weight, dv;
		
		//verifica primeiro dv
		sum = 0;
		weight = 3;
		for (i = 0; i < 12; i++) {
			sum += ((weight % 8) + 2) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
			weight--;
		}
		dv = (sum % 11) > 2 ? (11 - (sum % 11)) : 2;
		if ((Integer.parseInt(String.valueOf(cpf.charAt(12)))) != dv)
				return false;
		
		//verifica segundo dv
		sum = 0;
		weight = 4;
		for (i = 0; i < 13; i++) {
			sum += ((weight % 8) + 2) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
			weight--;
		}
		dv = (sum % 11) > 2 ? (11 - (sum % 11)) : 2;
		if ((Integer.parseInt(String.valueOf(cpf.charAt(12)))) != dv)
			return false;
		return true;
	}
	
	public boolean validarCNPJ(String cnpj) {
		int n_digitos;
		cnpj.replaceAll("[^0-9]","");
		
		n_digitos = cnpj.length();
		if (n_digitos != 14)
			return false;
		
		if (is_equal_characters(cnpj))
			return false;
		
		return has_valid_dv(cnpj);
	}
}
