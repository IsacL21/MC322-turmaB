package classes;

import java.util.ArrayList;
import java.util.Date;

public abstract class Seguro {
	private static int ultimaId;
	private final int id;
	private Date dataInicio;
	private Date dataFim;
	private Seguradora seguradora;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listaCondutores;
	private double valorMensal;

	
	public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Condutor> listaCondutores) {
		this.id = geraId();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = listaCondutores;
		valorMensal = 0;
	}

	public int getId() {
		return id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
	
	public abstract String getTipo();

	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}

	public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
		this.listaCondutores = listaCondutores;
	}

	public double getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(double valorMensal) {
		this.valorMensal = valorMensal;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	private int geraId() {
		ultimaId++;
		return ultimaId;
	}
	
	public boolean autorizarCondutor(Condutor condutor) {
		return listaCondutores.add(condutor);
	}
	
	public boolean desautorizarCondutor(Condutor condutor) {
		return listaCondutores.remove(condutor);
	}
	
	public boolean gerarSinistro(Date data, String endereco, Condutor condutor) {
		boolean returnValue = false;
		Sinistro sinistro = new Sinistro(data, endereco, this, condutor);
		if (condutor.adcionarSinistro(sinistro))
			returnValue = true;
		if (!listaSinistros.add(sinistro))
			returnValue = false;
		return returnValue;
	}
	
	public abstract Cliente getCliente();
	
	public abstract double calcularValor();
	
	public String toString() {
		String returnValue = "";
		returnValue += ("Id do Seguro: " + id + "\n" +
				"Seguradora: " + seguradora + "("+ seguradora.getCnpj() +")\n" +
				"Valor: " + valorMensal + "\n" +
				"Data de Início: " + dataInicio + "\n" +
				"Data de Término: " + dataFim + "\n");
		//gera lista compacta de condutores
		if (listaCondutores.size() < 0)
			returnValue += "O seguro não possui condutores cadastrados.\n";
		else {
			returnValue += "Condutores:\n";
			for (Condutor i:listaCondutores) {
				returnValue +=("\t" + i.getNome() +"(" + i.getCpf() + ")\n");
			}
		}
		//gera lista compacta de sinistros
		if (listaSinistros.size() < 0) {
			returnValue += "Sinistros: ";
			for (Sinistro i:listaSinistros)
				returnValue += ("id" + i.getId() + ", ");
			returnValue += "\n";
		}
		return returnValue;
	}
}
