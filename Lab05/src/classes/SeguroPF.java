package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SeguroPF extends Seguro {
	private Veiculo veiculo;
	private ClientePF cliente;
	
	public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Condutor> listaCondutores,
			Veiculo veiculo, ClientePF cliente) {
		super(dataInicio, dataFim, seguradora, listaCondutores);
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public int getIdadeCliente() {
		Date dataAtual = new java.util.Date();                                                                                      
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int d1 = Integer.parseInt(formatter.format(cliente.getDataNascimento()));                            
	    int d2 = Integer.parseInt(formatter.format(dataAtual));                          
	    int idade = (d2 - d1) / 10000;                                                       
	    return idade;
	}
	
	public double calcularValor() {
		double fatorIdade;
		int idade = getIdadeCliente();
		//descobre o fator da idade
		if (idade >= 18 && idade <= 30)
			fatorIdade = CalcSeguro.FATOR_18_30.valor();
		else if (idade > 30 && idade <= 60)
			fatorIdade = CalcSeguro.FATOR_30_60.valor();
		else if (idade > 60 && idade <= 90)
			fatorIdade = CalcSeguro.FATOR_60_90.valor();
		else
			return 0;
		
		int quantidadeVeiculos = getSeguradora().getSegurosPorCliente(cliente).size(); //cada veículo deveria estar em apenas um seguro
		int quantidadeSinistrosCliente = getSeguradora().getSinistrosPorCliente(cliente).size();
		int quantidadeSinistrosCondutores = 0;
		for (Condutor i : getListaCondutores())
			quantidadeSinistrosCondutores += getSeguradora().getSinistrosPorCondutor(i).size();
		return (CalcSeguro.VALOR_BASE.valor() * fatorIdade * (1 + 1.0/(quantidadeVeiculos+2)) *
				(2 + quantidadeSinistrosCliente/10.0) * 
				(5 + quantidadeSinistrosCondutores/10.0));
	}
	
	public String toString() {
		return(super.toString() +
				"Cliente: " + cliente.getNome() + "(" + cliente.getId() + ")\n" + 
				"Veiculo: " + veiculo);
	}
}
