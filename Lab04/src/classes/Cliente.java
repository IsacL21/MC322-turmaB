package classes;

import java.util.ArrayList;

public abstract class Cliente {
	private String nome;
	private String endereco;
	private ArrayList<Veiculo> listaVeiculos;
	private double valorSeguro;
	
	public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = listaVeiculos;
		valorSeguro = 0;
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

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public boolean cadastrarVeiculo(Veiculo novoVeiculo) {
		return listaVeiculos.add(novoVeiculo);
	}
	
	public boolean removerVeiculo(String placaVeiculo) {
		return listaVeiculos.removeIf(n -> n.getPlaca().equals(placaVeiculo)); 
	}

	public String stringListaVeiculos() {
		String lista = "";
		for(Veiculo i:getListaVeiculos())
			lista += ("\t" + i.toString());
		return lista;
	}
	
	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public Veiculo encontraVeiculo(String placa) {
		for (Veiculo i:getListaVeiculos()) {
			if (i.getPlaca().equals(placa))
				return i;
		}
		return null;
	}
	
	public abstract String getId();
	
	public abstract String getTipo();

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}
	
	public abstract double calculaScore();
	
	
	
	
	
	

	

}
