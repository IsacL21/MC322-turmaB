package classes;

import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
	final private String cpf ;
	private Date dataNascimento ;
	private String educacao;
	private String genero;
	private ArrayList<Veiculo> listaVeiculos;

	public ClientePF(String nome, String telefone, String endereco, String email, String educacao, String genero,
			String cpf, Date dataNascimento) {
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.educacao = educacao;
		this.genero = genero;
		listaVeiculos = new ArrayList<Veiculo>();
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
	
	public String getTipo() {
		return "PF";
	}
	
	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public boolean cadastrarVeiculo(Veiculo novoVeiculo) {
		return listaVeiculos.add(novoVeiculo);
	}
	
	public boolean removerVeiculo(Veiculo veiculo) {
		return listaVeiculos.remove(veiculo); 
	}
	
	public String stringListaVeiculos() {
		String lista = "";
		for(Veiculo i:listaVeiculos)
			lista += i.toString();
		return lista;
	}
	
	public String toString() {
		return (super.toString() +
				"Cpf: " + getId() + "\n" +
				"Gênero : " + getGenero() + "\n" +
				"Data de Nascimento : " + getDataNascimento() + "\n" +
				"Educação: " + getEducacao() + "\n" +
				"Veículos:\n" +
					stringListaVeiculos().indent(1)
				);
	}	
}