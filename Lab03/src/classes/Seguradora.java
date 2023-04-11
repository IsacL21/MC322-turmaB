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
		
	}
}
