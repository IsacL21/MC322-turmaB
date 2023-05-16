package classes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList <Sinistro> listaSinistros;
	private LinkedList <Cliente> listaClientes;

	public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistros,
			LinkedList<Cliente> listaClientes) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = listaSinistros;
		this.listaClientes = listaClientes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public LinkedList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(LinkedList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public boolean cadastrarCliente(Cliente novoCliente) {
		return listaClientes.add(novoCliente);
	}
	
	public Cliente encontraCliente(String clienteId) {
		for (Cliente i:getListaClientes()) {
			if (i.getId().replaceAll("[^0-9]","").equals(clienteId.replaceAll("[^0-9]","")))
				return i;
		}
		return null;
	}
	
	public boolean removerCliente(String clienteId) {
		Cliente cliente = encontraCliente(clienteId);
		return getListaClientes().remove(cliente);
	}
	
	public boolean removerCliente(Cliente cliente) {
		return getListaClientes().remove(cliente);
	}
	
	public void listarClientes(String tipoCliente) {
		System.out.println("Lista Clientes " + tipoCliente +":\n");
		for (Cliente i:getListaClientes()) {
			if ((tipoCliente.equals("")) || (tipoCliente.equals(i.getTipo()))) 
				System.out.println(
						"\tNome: " + i.getNome() + "\n" +
						(i.getTipo() == "PF"? "\tCpf: ": "\tCnpj: ") + i.getId() + "\n" +
						"\tEndereco: " + i.getEndereco() + "\n" +
						"\tVeículos:\n" +
							i.stringListaVeiculos().indent(4)
						);
		}
	}
	
	public Veiculo encontraVeiculo(String placa) {
		Veiculo v;
		for (Cliente i:getListaClientes()) {
			if ((v = i.encontraVeiculo(placa)) != null)
				return v;
		}
		return null;
	}
	
	public Cliente encontraDono(Veiculo veiculo) {
		for (Cliente i:getListaClientes()) {
			for(Veiculo j: i.getListaVeiculos()) {
				if (j.equals(veiculo))
					return i;
			}
		}
		return null;
	}
	
	public boolean gerarSinistro(String data, String endereco, Veiculo veiculo) {
		Cliente cliente;
		if ((cliente = encontraDono(veiculo)) == null)
			return false;
		else
			return getListaSinistros().add(new Sinistro(data,endereco,this,veiculo, cliente));
	}
	
	public boolean visualizarSinistro(String clienteId) {
		Cliente cliente = encontraCliente(clienteId);
		return visualizarSinistro(cliente);
	}
	
	public boolean visualizarSinistro(Cliente cliente) {
		boolean found = false;
		System.out.println("Sinistros(" + cliente.getNome() + "):\n");
		for (Sinistro i:getListaSinistros()) {
			if (i.getCliente().equals(cliente)) {
				System.out.println(i + 
						"\n");
				found = true;
			}
		}
		return found;
	}
	
	private int numeroSinistros(Cliente cliente) {
		int numeroSinistros = 0;
		for (Sinistro i:getListaSinistros()) {
			if (i.getCliente().equals(cliente)) {
				numeroSinistros++;
			}
		}
		return numeroSinistros;
	}
	
	public void listarSinistros() {
		System.out.println("Sinistros:\n");
		for (Sinistro i:getListaSinistros()) {
				System.out.println(i + 
						"\n");	
		}
	}
	/*Calcula e armazena no campo valorSeguro do cliente*/
	public double calcularPrecoSeguroCliente(Cliente cliente) {
		double preco = cliente.calculaScore() * (1 + numeroSinistros(cliente));
		cliente.setValorSeguro(preco);
		return preco;
	}
	
	public double calculaReceita() {
		double receita = 0;
		for (Cliente i:getListaClientes()) {
			receita += i.getValorSeguro();
		}
		return receita;
	}
}