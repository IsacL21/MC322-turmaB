package classes;


public class Sinistro {
	private static int ultima_ID = 0;
	final private int ID;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;

	public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
		this.ID = geraID();
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getID() {
		return ID;
	}

	private int geraID() {
		ultima_ID++;
		return ultima_ID;
	}
	
	public String toString() {
		return ("Id: " + getID() + "\n" +
				"Endereço: " + getEndereco() + "\n" +
				"Data: " + getData() + "\n" +
				"Endereco: " + getEndereco() + "\n" +
				"Cliente: " + getCliente().getNome() + " (" + getCliente().getId() + ")\n" +
				"Veículo: " + getVeiculo() +
				"Seguradora: " + getSeguradora().getNome() + "\n"
				);
	}
}
