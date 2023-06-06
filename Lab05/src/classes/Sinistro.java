package classes;

import java.util.Date;

public class Sinistro {
	private static int ultima_Id = 0;
	final private int Id;
	private Date data;
	private String endereco;
	private Seguro seguro;
	private Condutor condutor;

	public Sinistro(Date data, String endereco, Seguro seguro, Condutor condutor) {
		this.Id = geraId();
		this.data = data;
		this.endereco = endereco;
		this.seguro = seguro;
		this.condutor = condutor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public int getId() {
		return Id;
	}

	private int geraId() {
		ultima_Id++;
		return ultima_Id;
	}
	
	public String toString() {
		return ("Sinistro " + getId() + ":\n" +
				"\tData: " + getData() + "\n" +
				"\tEndereco: " + getEndereco() + "\n" +
				"\tCondutor: " + getCondutor().getNome() + " (" + getCondutor().getCpf() + ")\n" +
				"\tCliente: " + getSeguro().getCliente() + "\n" +
				"\tSeguradora: " + getSeguro().getSeguradora() + "\n"
				);
	}
}
