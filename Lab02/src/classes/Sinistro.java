package classes;

public class Sinistro {
	private static int ultima_id = 0;
	private int id;
	private String data;
	private String endereco;

	public Sinistro(String data, String endereco) {
		id  = geraId();
		this.data = data;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	private int geraId() {
		ultima_id++;
		return ultima_id;
	}
	
}
