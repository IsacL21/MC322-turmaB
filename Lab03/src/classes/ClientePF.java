package classes;

import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
	final private String cpf ;
	private Date dataNascimento ;
	private Date dataLicenca;
	private String educacao;
	private String genero;
	private String classeEconomica;

	public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cpf, Date dataNascimento) {
		super(nome, endereco, listaVeiculos);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
	}

	public String getId() {
		return cpf;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Date getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	public String getTipo() {
		return "PF";
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
	
	public boolean validarId() {
		int n_digitos;
		String cpf = this.cpf.replaceAll("[^0-9]","");
		
		n_digitos = cpf.length();
		if (n_digitos != 11)
			return false;
		
		if (is_equal_characters(cpf))
			return false;
		
		return has_valid_dv(cpf);
	}
	
	public String toString() {
		return ("Nome: " + getNome() + "\n" +
				"Cpf: " + getId() + "\n" +
				"Gênero : " + getGenero() + "\n" +
				"Data de Nascimento : " + getDataNascimento() + "\n" +
				"Educação: " + getEducacao() + "\n" +
				"Classe Econômica:" + getClasseEconomica() + "\n" +
				"Data da Licença: " + getDataLicenca() + "\n" +
				"Endereco: " + getEndereco() + "\n" +
				"Veículos:\n" +
					stringListaVeiculos()
				);
	}
	
}
