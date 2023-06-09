package classes;

import java.util.ArrayList;
import java.util.Date;

public class Condutor {
	final private String cpf ;
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	private Date dataNascimento ;
	private ArrayList<Sinistro> listaSinistros;
	
	public Condutor(String nome, String telefone, String endereco, String email, String cpf, Date dataNascimento) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		listaSinistros = new ArrayList<Sinistro>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}
	
	public boolean adcionarSinistro(Sinistro sinistro) {
		return listaSinistros.add(sinistro);
	}
	
	public boolean removerSinistro(Sinistro sinistro) {
		return listaSinistros.remove(sinistro);
	}
	
	public String toString() {
		return(
				"Nome: " + nome + "\n" +
				"Telefone: " + telefone + "\n" +
				"Endereco: " + endereco + "\n" +
				"Email: " + email + "\n" +
				"Cpf: " + cpf+ "\n" +
				"Data de Nascimento : " + dataNascimento + "\n");
	}
}
