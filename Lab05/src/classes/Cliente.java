package classes;

public abstract class Cliente {
	private String nome;
	private String telefone;
	private String endereco;
	private String email;
	
	public Cliente(String nome, String telefone, String endereco, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public abstract String getId();
	
	public abstract String getTipo();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String toString() {
		return ("Nome: " + getNome() + "\n" +
				"Telefone: " + getTelefone() + "\n" +
				"Endereco: " + getEndereco() + "\n" +
				"Email: " + getEmail() + "\n");
	}
}