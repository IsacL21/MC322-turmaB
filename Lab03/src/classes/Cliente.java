package classes;

import java.util.ArrayList;

public abstract class Cliente {
	private String nome;
	private String endereco;
	private ArrayList<Veiculo> listaVeiculos;
	
	public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = listaVeiculos;
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
			lista += ("\t" + i.toString() + "\n");
		return lista;
	}
	
	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public abstract String getId();
	
	public abstract String getTipo();
	
	public abstract boolean validarId(String Id);
	
	protected boolean is_equal_characters(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(0) != str.charAt(i))
				return false;
		}
		return true;
	}
	

	

}
