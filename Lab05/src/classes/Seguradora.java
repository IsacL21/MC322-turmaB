package classes;

import java.util.ArrayList;
import java.util.Date;

public class Seguradora {
	private final String cnpj;
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList <Seguro> listaSeguros;
	private ArrayList <Cliente> listaClientes;

	public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		listaSeguros = new ArrayList<Seguro>();
		listaClientes = new ArrayList<Cliente>();
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
	
	public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public void listarClientes(String tipoCliente) {
		System.out.println("Lista Clientes " + tipoCliente +":\n");
		for (Cliente i:getListaClientes()) {
			if ((tipoCliente.equals("")) || (tipoCliente.equals(i.getTipo()))) 
				System.out.println(i.toString().indent(1)+"\n");
		}
	}
	
	public boolean gerarSeguro(Date dataInicio, Date dataFim, ArrayList<Condutor> listaCondutores, ClientePF cliente, Veiculo veiculo) {
		SeguroPF seguro = new SeguroPF(dataInicio, dataFim, this, listaCondutores, veiculo, cliente);
		return listaSeguros.add(seguro);
	}
	
	public boolean gerarSeguro(Date dataInicio, Date dataFim, ArrayList<Condutor> listaCondutores, ClientePJ cliente, Frota frota) {
		SeguroPJ seguro = new SeguroPJ(dataInicio, dataFim, this, listaCondutores, frota, cliente);
		return listaSeguros.add(seguro);
	}
	
	public boolean cancelarSeguro(Seguro seguro) {
		return listaSeguros.remove(seguro);
	}

	public boolean cadastrarCliente(Cliente novoCliente) {
		return listaClientes.add(novoCliente);
	}
	
	public boolean removerCliente(Cliente cliente) {
		return getListaClientes().remove(cliente);
	}
	
	public ArrayList<SeguroPF> getSegurosPorCliente(ClientePF cliente){
		ArrayList<SeguroPF> returnList = new ArrayList<SeguroPF>();
		for (Seguro i:listaSeguros) 
			if (i.getCliente().equals(cliente))
				returnList.add((SeguroPF) i);
		return returnList;
	}
	
	public ArrayList<SeguroPJ> getSegurosPorCliente(ClientePJ cliente){
		ArrayList<SeguroPJ> returnList = new ArrayList<SeguroPJ>();
		for (Seguro i:listaSeguros) 
			if (i.getCliente().equals(cliente))
				returnList.add((SeguroPJ) i);
		return returnList;
	}
	
	public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente){
		ArrayList<Sinistro> returnList = new ArrayList<Sinistro>();
		for (Seguro i:listaSeguros) 
			if (i.getCliente().equals(cliente))
				for(Sinistro j:i.getListaSinistros())
					returnList.add(j);
		return returnList;
	}
	
	public ArrayList<Sinistro> getSinistrosPorCondutor(Condutor condutor){
		ArrayList<Sinistro> returnList = new ArrayList<Sinistro>();
		for (Sinistro i:condutor.getListaSinistros()) 
			if (i.getSeguro().getSeguradora().equals(this))
				returnList.add(i);
		return returnList;
	}
	
	private SeguroPJ getSeguro(Frota frota, ClientePJ cliente) {
		for (SeguroPJ i : getSegurosPorCliente(cliente)) {
			if (i.getFrota().equals(frota))
				return i;
		}
		return null;
	}
	
	private SeguroPF getSeguro(Veiculo veiculo, ClientePF cliente) {
		for (SeguroPF i : getSegurosPorCliente(cliente)) {
			if (i.getVeiculo().equals(veiculo))
				return i;
		}
		return null;
	}

	public double calculaReceita() {
		double receita = 0;
		for (Seguro i: listaSeguros) {
			receita += i.calcularValor();
		}
		return receita;
	}
	
	public String toString() {
		return ("Nome: " + nome + "\n" +
				"Telefone: " + telefone + "\n" +
				"Endereco: " + endereco + "\n" +
				"Email: " + email + "\n" +
				"Cnpj: " + cnpj + "\n");
	}
}