package classes;

import java.util.ArrayList;

public class Selecao {
	
	public static Seguradora escolheSeguradora(ArrayList<Seguradora> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione a seguradora:");
		for (int i = 0; i < lista.size(); i++)
			System.out.println(i+1 + "-" + lista.get(i).getNome());
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static Seguro escolheSeguro(ArrayList<Seguro> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione o seguro:");
		for (int i = 0; i < lista.size(); i++)
			System.out.println(i+1 + "-Id." + lista.get(i).getId());
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static Cliente escolheCliente(ArrayList<Cliente> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione o cliente:");
		for (int i = 0; i < lista.size(); i++) 
			System.out.println(i+1 + "-" + lista.get(i).getNome() + "(" + lista.get(i).getId() + ")");
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static ClientePF escolheClientePF(ArrayList<ClientePF> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione o cliente:");
		for (int i = 0; i < lista.size(); i++) 
			System.out.println(i+1 + "-" + lista.get(i).getNome() + "(" + lista.get(i).getId() + ")");
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static ClientePJ escolheClientePJ(ArrayList<ClientePJ> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione o cliente:");
		for (int i = 0; i < lista.size(); i++) 
			System.out.println(i+1 + "-" + lista.get(i).getNome() + "(" + lista.get(i).getId() + ")");
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static Veiculo escolheVeiculo(ArrayList<Veiculo> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione o veículo:");
		for (int i = 0; i < lista.size(); i++) 
			System.out.println(i+1 + "-" + lista.get(i));
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static Frota escolheFrota(ArrayList<Frota> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione a frota:");
		for (int i = 0; i < lista.size(); i++) 
			System.out.println(i+1 + "-" + lista.get(i).getCode());
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static Condutor escolheCondutor(ArrayList<Condutor> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione o condutor:");
		for (int i = 0; i < lista.size(); i++) 
			System.out.println(i+1 + "-" + lista.get(i).getNome() + "(" + lista.get(i).getCpf() + ")");
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
	
	public static Sinistro escolheSinistro(ArrayList<Sinistro> lista) {
		if (lista.size() <= 0)
			throw new IllegalStateException();
		System.out.println("Selecione o sinistro:");
		for (int i = 0; i < lista.size(); i++) 
			System.out.println(i+1 + "-Id." + lista.get(i).getId());
		return lista.get(Input.getIntInputBetween(0, lista.size()));
	}
}
	
