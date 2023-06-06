package classes;

import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente {
	final private String cnpj;
	private Date dataFundacao;
	private int qtdeFuncionarios;
	private ArrayList<Frota> listaFrotas;

	public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, Date dataFundacao, int qtdeFuncionarios) {
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
		listaFrotas = new ArrayList<Frota>();
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getId() {
		return cnpj;
	}
	
	public String getTipo() {
		return "PJ";
	}
	
	public int getQtdeFuncionarios() {
		return qtdeFuncionarios;
	}

	public void setQtdeFuncionarios(int qtdeFuncionarios) {
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	public ArrayList<Frota> getListaFrotas(){
		return listaFrotas;
	}
	
	public boolean cadastrarFrota(Frota novaFrota) {
		return listaFrotas.add(novaFrota);
	}
	
	public boolean removerFrota(Frota frota) {
		return listaFrotas.remove(frota);
	}
	
	public boolean atualizarFrota(boolean cadastrar, Frota frota, Veiculo veiculo) {
		if (cadastrar)
			return frota.adicionarVeiculo(veiculo);
		else if (veiculo == null)
			return removerFrota(frota);
		else 
			return frota.removerVeiculo(veiculo);
	}
	
	public String stringListaFrotas() {
		String lista = "";
		for(Frota i:listaFrotas)
			lista += (i.toString().indent(1));
		return lista;
	}
	
	public String toString() {
		return (super.toString() +
				"Cnpj: " + getId() + "\n" +
				"Data da Fundação : " + getDataFundacao() + "\n" +
				"Frotas:\n" +
					stringListaFrotas().indent(1));
	}
}