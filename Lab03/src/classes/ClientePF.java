package classes;

import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
	final private String cpf ;
	private Date dataNascimento ;

	public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cpf, Date dataNascimento) {
		super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	private boolean has_valid_dv (String cpf) {
		int i,j;
		
		//verifica primeiro dv
		for (i = 0, j = 0; i < 9; i++)
			j += (10 - i) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
		if ((j % 11) == 1 || (j % 11) == 0) {
			if (cpf.charAt(9) != 0)
				return false;
		}
		else {
			if ((11 - (j%11)) != (Integer.parseInt(String.valueOf(cpf.charAt(9)))))
				return false;
		}
		
		//verifica segundo dv
		for (i = 0, j = 0; i < 10; i++)
			j += (11 - i) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
		if ((j % 11) == 1 || (j % 11) == 0) {
			if (cpf.charAt(10) != 0)
				return false;
		}
		else {
			if ((11 - (j%11)) != (Integer.parseInt(String.valueOf(cpf.charAt(10)))))
				return false;
		}
		
		return true;
	}
	
	public boolean validarCPF(String cpf) {
		int n_digitos;
		cpf.replaceAll("[^0-9]","");
		
		n_digitos = cpf.length();
		if (n_digitos != 11)
			return false;
		
		if (is_equal_characters(cpf))
			return false;
		
		return has_valid_dv(cpf);
	}
	
}
