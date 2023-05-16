package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	public int getIdade() {
		Date dataAtual = new java.util.Date();                                                                                      
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int d1 = Integer.parseInt(formatter.format(getDataNascimento()));                            
	    int d2 = Integer.parseInt(formatter.format(dataAtual));                          
	    int idade = (d2 - d1) / 10000;                                                       
	    return idade;
	}
	
	public double calculaScore() {
		double fatorIdade;
		int idade = getIdade();
		//descobre o fator da idade
		if (idade >= 18 && idade <= 30)
			fatorIdade = CalcSeguro.FATOR_18_30.valor();
		else if (idade > 30 && idade <= 60)
			fatorIdade = CalcSeguro.FATOR_30_60.valor();
		else if (idade > 60 && idade <= 90)
			fatorIdade = CalcSeguro.FATOR_60_90.valor();
		else
			return 0;
		
		return CalcSeguro.VALOR_BASE.valor() * fatorIdade * getListaVeiculos().size();
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
