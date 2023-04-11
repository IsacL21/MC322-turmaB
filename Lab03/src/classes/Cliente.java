package classes;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {
	private String nome;
	private String endereco;
	private Date dataLicenca;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private ArrayList<Veiculo> ListaVeiculos;
	
	public Cliente(String nome, String endereco, Date dataLicenca, String educacao, String genero,
			String classeEconomica, ArrayList<Veiculo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.ListaVeiculos = listaVeiculos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	public ArrayList<Veiculo> getListaVeiculos() {
		return ListaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		ListaVeiculos = listaVeiculos;
	}

	public String toString() {
		return ("Nome: " + nome + "\n" +
				"Cpf: " + cpf + "\n" +
				"Data de Nascimento : " + dataNascimento + "\n" +
				"Idade: " + idade + "\n" +
				"Endereco: " + endereco + "\n"
				);
	}
	
	protected boolean is_equal_characters(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(0) != str.charAt(i))
				return false;
		}
		return true;
	}
	

	

}
