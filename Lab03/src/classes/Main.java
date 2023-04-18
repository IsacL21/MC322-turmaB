package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Seguradora seguradora_teste = new Seguradora ("Safezinha", "(15) 4004-5274","safezinha@gmail.com",
					"Av Getúlio Vargas, 2900", new ArrayList<Sinistro>(), new LinkedList<Cliente>());
		System.out.println("menu:");
		Scanner input = new Scanner(System.in);
		String comando = input.nextLine().toLowerCase();
		switch (comando) {
	
		
		case "cadastrar cliente":
			String tipo, nome, id, endereco;
			boolean isValidDate;
			
			do {
				System.out.println("Tipo do Cliente(PF ou PJ): ");
				tipo = input.nextLine().toUpperCase();
			} while (!tipo.equals("PF") && !tipo.equals("PJ"));
			
			System.out.println("Nome do Cliente: ");
			nome = input.nextLine();
			
			System.out.println("Endereço: ");
			endereco = input.nextLine();
			
			if (tipo.equals("PF")) {
				String genero, stringDataLicenca, educacao, stringDataNascimento, classeEconomica;
				Date dataNascimento, dataLicenca = null;
				
				System.out.println("Gênero: ");
				genero = input.nextLine();
				
				System.out.println("Educação: ");
				educacao = input.nextLine();
				
				System.out.println("Classe Econômica: ");
				classeEconomica = input.nextLine();
				
				System.out.println("Data de Nascimento: ");
				do {
					stringDataNascimento = input.nextLine();
					try {
						dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(stringDataNascimento);
						isValidDate = true;
					} catch (ParseException e) {
						isValidDate = false;
						System.out.println("Insira uma data no formato válido (dd/mm/yyyy)!");
					}
				}while (!isValidDate);
				
				System.out.println("Data de Licença: ");
				do {
					stringDataLicenca = input.nextLine();
					try {
						dataLicenca = new SimpleDateFormat("dd/MM/yyyy").parse(stringDataLicenca);
						isValidDate = true;
					} catch (ParseException e) {
						isValidDate = false;
						System.out.println("Insira uma data no formato válido (dd/mm/yyyy)!");
					}
				}while (!isValidDate);
				
				seguradora_teste.cadastrarCliente(new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, new ArrayList<Veiculo>(), id, dataNascimento));
			} 
		}
	}

}
