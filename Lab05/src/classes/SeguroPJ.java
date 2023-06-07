package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SeguroPJ extends Seguro {

	private Frota frota;
	private ClientePJ cliente;
	
	public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Condutor> listaCondutores,
			Frota frota, ClientePJ cliente) {
		super(dataInicio, dataFim, seguradora, listaCondutores);
		this.frota = frota;
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public Frota getFrota() {
		return frota;
	}
	
	public String getTipo() {
		return "PJ";
	}

	public int getIdadeCliente() {
		Date dataAtual = new java.util.Date();                                                                                      
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int d1 = Integer.parseInt(formatter.format(cliente.getDataFundacao()));                            
	    int d2 = Integer.parseInt(formatter.format(dataAtual));                          
	    int idade = (d2 - d1) / 10000;                                                       
	    return idade;
	}
	
	public double calcularValor() {
		int quantidadeSinistrosCliente = getSeguradora().getSinistrosPorCliente(cliente).size();
		int quantidadeSinistrosCondutores = 0;
		for (Condutor i : getListaCondutores())
			quantidadeSinistrosCondutores += getSeguradora().getSinistrosPorCondutor(i).size();
		return (CalcSeguro.VALOR_BASE.valor() * (cliente.getQtdeFuncionarios()/10.0) * (1 + 1.0/(frota.getListaVeiculos().size()+2)) *
				(1 + 1.0/(getIdadeCliente()+2)) * 
				(2 + quantidadeSinistrosCliente/10.0) * 
				(5 + quantidadeSinistrosCondutores/10.0));
	}

	public String toString() {
		return (super.toString() + 
				"Cliente: " + cliente.getNome() + "(" + cliente.getId() + ")\n" + 
				"Frota: " + frota);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
