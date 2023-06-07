package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Input {
	
	public static int getIntInput() {
		int input = -1;
		boolean isInt = false;
		do {
			try {
				input = Integer.parseInt(Main.scanner.nextLine()) - 1;
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; System.out.println("insira um numero inteiro!"); }
		}while(!(isInt));
		return input;
	}
	
	//receives an int in the interval [min,max[
	public static int getIntInputBetween(int min, int max) {
		int n = getIntInput();
		while(n < min || n >= max) {
			System.out.println("insira uma opção válida!");
			n = getIntInput();
		}
		return n;
	}
	
	/*Printa mensagem, le uma string e a retorna*/
	public static String printScan(String mensagem) {
		System.out.println(mensagem);
		return Main.scanner.nextLine();
	}
	
	public static Date recebeDataValida() {
		Date data = null;
		String strData;
		boolean isValidDate;
		do {
			strData = Main.scanner.nextLine();
			try {
				data = new SimpleDateFormat("dd/MM/yyyy").parse(strData);
				isValidDate = true;
			} catch (ParseException e) { isValidDate = false; System.out.println("insira uma data no formato 'dd/MM/yyyy'!");}
		}while (!isValidDate);
		return data;
	}
	
	private static String[] recebeBaseCliente(){
		String[] returnString = {"","","",""};
		//recebe entradas
		do {returnString[0] = printScan("Nome do Cliente: ");}
		while (!Validacao.validarNome(returnString[0]));
		returnString[1] = printScan("Telefone ");
		returnString[2] = printScan("Endereço: ");
		returnString[3] = printScan("Email: ");
		
		return returnString;
	}
	
	public static ClientePF instanciaClientePF() {
		String[] dadosBase = recebeBaseCliente();
		String cpf,educacao,genero;
		Date dataNascimento;
		
		//recebe cpf
		do {cpf = printScan("Cpf: ");}
		while (!Validacao.validarCPF(cpf));
		//receber data de nascimento
		System.out.println("Data de Nascimento: ");
		dataNascimento = recebeDataValida();
		//receber outros parametros
		educacao = printScan("Educação: ");
		genero = printScan("Gênero: ");

		return new ClientePF(dadosBase[0], dadosBase[1], dadosBase[2], dadosBase[3], educacao, genero, cpf, dataNascimento);
	}
	
	public static ClientePJ instanciaClientePJ() {
		String[] dadosBase = recebeBaseCliente();
		String cnpj;
		Date dataFundacao;
		int qtdeFuncionarios;
		
		//recebe cnpj
		do {cnpj = printScan("Cnpj: ");}
		while (!Validacao.validarCNPJ(cnpj));
		//receber data de fundacao
		System.out.println("Data de Fundação: ");
		dataFundacao = recebeDataValida();
		//receber quantidade de funcionarios
		qtdeFuncionarios = getIntInput();
		
		return new ClientePJ(dadosBase[0], dadosBase[1], dadosBase[2], dadosBase[3], cnpj, dataFundacao, qtdeFuncionarios);
	}
	
	public static Condutor instanciaCondutor() {
		String[] dadosBase = recebeBaseCliente();
		String cpf;
		Date dataNascimento;
		
		//recebe cpf
		do {cpf = printScan("Cpf: ");}
		while (!Validacao.validarCPF(cpf));
		//receber data de nascimento
		System.out.println("Data de Nascimento: ");
		dataNascimento = recebeDataValida();

		return new Condutor(dadosBase[0], dadosBase[1], dadosBase[2], dadosBase[3], cpf, dataNascimento);
	}
	
	public static Seguradora instanciaSeguradora() {
		String[] dadosBase = recebeBaseCliente();
		String cnpj;
		//recebe cnpj
		do {cnpj = printScan("Cnpj: ");}
		while (!Validacao.validarCNPJ(cnpj));

		return new Seguradora(cnpj, dadosBase[0], dadosBase[1], dadosBase[2], dadosBase[3]);
	}
	
	public static Veiculo instaciaVeiculo() {
		//recebe parametros
		String marca = printScan("Marca: ");
		String modelo = printScan("Modelo: ");
		String placa = printScan("Placa: ");
		int anoFabricacao = getIntInput();

		return new Veiculo(marca, modelo, placa, anoFabricacao);
		}
	
}
