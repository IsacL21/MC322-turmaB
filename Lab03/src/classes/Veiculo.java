package classes;

public class Veiculo {
	private String marca;
	private String modelo;
	private String placa;
	private int anoFabricacao;
	
	public Veiculo (String marca, String modelo, String placa, int anoFabricacao) {
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.anoFabricacao = anoFabricacao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public int getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(int anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
}
