package classes;

public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String endereco;
	private int idade;
	
	public Cliente(String nome, String cpf, String dataNascimento, String endereco, int idade) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String toString() {
		return ("Nome: " + nome + "\n" +
				"Cpf: " + cpf + "\n" +
				"Data de Nascimento : " + dataNascimento + "\n" +
				"Idade: " + idade + "\n" +
				"Endereco: " + endereco + "\n"
				);
	}
	
	private boolean is_equal_characters(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(0) != str.charAt(i))
				return false;
		}
		return true;
	}
	
	private boolean has_valid_dv (String cpf) {
		int i,j;
		
		//verifica primeiro dv
		for (i = 0, j = 0; i < 9; i++)
			j += (10 - i) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
		if ((j % 11) == 1 || (j % 11) == 0) {
			if (cpf.charAt(9) != 0)
				return false;
		}
		else {
			if ((11 - (j%11)) != (Integer.parseInt(String.valueOf(cpf.charAt(9)))))
				return false;
		}
		
		//verifica segundo dv
		for (i = 0, j = 0; i < 10; i++)
			j += (11 - i) * (Integer.parseInt(String.valueOf(cpf.charAt(i))));
		if ((j % 11) == 1 || (j % 11) == 0) {
			if (cpf.charAt(10) != 0)
				return false;
		}
		else {
			if ((11 - (j%11)) != (Integer.parseInt(String.valueOf(cpf.charAt(10)))))
				return false;
		}
		
		return true;
	}
	
	public boolean validarCPF() {
		int n_digitos;
		String cpf;
		cpf = this.cpf.replaceAll("[^0-9]","");
		
		n_digitos = cpf.length();
		if (n_digitos != 11)
			return false;
		
		if (is_equal_characters(cpf))
			return false;
		
		return has_valid_dv(cpf);
	}
}
