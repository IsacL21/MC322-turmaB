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
	
	public boolean removerCliente(String clienteId) {
		return listaClientes.removeIf(n -> n.getId().equals(clienteId)); 
	}
	
	public void listarClientes(String tipoCliente) {
		System.out.println("Lista Clientes " + tipoCliente +":\n");
		for (Cliente i:getListaClientes()) {
			if (!(tipoCliente.equals("")) && (tipoCliente.equals(i.getTipo()))) 
				System.out.println(
						"\tNome: " + getNome() + "\n" +
						(i.getTipo() == "PF"? "\tCpf: ": "\tCnpj: ") + i.getId() + "\n" +
						"\tEndereco: " + getEndereco() + "\n" +
						"\tVeículos:\n" +
							i.stringListaVeiculos().indent(4)
						);
		}
	}
	
	public boolean gerarSinistro(String data, String endereco, Veiculo veiculo, Cliente cliente) {
		return getListaSinistros().add(new Sinistro(data,endereco,this,veiculo,cliente));
	}
	
	public boolean visualizarSinistro(String clienteId) {
		boolean found = false;
		for (Sinistro i:getListaSinistros()) {
			if (i.getCliente().getId().equals(clienteId)) {
				System.out.println(i + 
						"\n");
				found = true;
			}
		}
		return found;
	}
	
	public void listarSinistros() {
		for (Sinistro i:getListaSinistros()) {
				System.out.println(i + 
						"\n");	
		}
	}	
}