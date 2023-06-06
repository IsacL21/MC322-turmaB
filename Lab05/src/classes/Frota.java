 package classes;

import java.util.ArrayList;

public class Frota {
	private final String code;
	private ArrayList<Veiculo> listaVeiculos;

	public Frota(String code, ArrayList<Veiculo> listaVeiculos) {
		this.code = code;
		this.listaVeiculos = listaVeiculos;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}
	
	public String getCode() {
		return code;
	}
	
	public boolean adicionarVeiculo(Veiculo veiculo) {
		return listaVeiculos.add(veiculo);
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
		return( "Frota " + code + ":\n" +
					stringListaVeiculos().indent(1));
		
	}
}
