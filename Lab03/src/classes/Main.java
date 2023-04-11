package classes;

public class Main {

	public static void main(String[] args) {
		Cliente cliente_teste = new Cliente("Ronaldo", "500.208.968-24", "20/04/2004", "Av. Santa Isabel, 1125", 18);
		Seguradora seguradora_teste = new Seguradora ("Segfacil", "(15 4004-5274)","segfacil@gmail.com", "Av Getúlio Vargas, 2900");
		Veiculo veiculo_teste = new Veiculo("Fiat","Siena 1.4","FEV-9460");
		Sinistro sinistro_teste = new Sinistro("21/03/2023","Av Getúlio Vargas, 2905");
		System.out.println(cliente_teste);
		System.out.println(cliente_teste.validarCPF());
	}

}
