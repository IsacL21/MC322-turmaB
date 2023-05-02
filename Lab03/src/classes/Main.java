package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Seguradora seguradoraTeste = new Seguradora ("Safezinha", "(15) 4004-5274","safezinha@gmail.com",
					"Av Getúlio Vargas, 2900", new ArrayList<Sinistro>(), new LinkedList<Cliente>());
		ClientePF c1, c2;
		ClientePJ c3;
		Date dataNascimento = null, dataLicenca = null, dataFundacao = null;
		
		try {
			dataLicenca = new SimpleDateFormat("dd/MM/yyyy").parse("20/10/1996");
			dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("24/01/1979");
		}catch (ParseException e) {}
		c1 = new ClientePF("Dean Winchester", "Cross Junction, Virginia, 22625", dataLicenca,
				"Ensino Médio", "Masculino", "Baixa", new ArrayList<Veiculo>(), "126.816.874-21", dataNascimento);
		
		try {
			dataLicenca = new SimpleDateFormat("dd/MM/yyyy").parse("13/02/1968");
			dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("24/01/1979");
		}catch (ParseException e) {}
		c2 = new ClientePF("Homer Simpson", "Evergreen Terrace, Springfield, 742", dataLicenca,
				"Ensino Fundamental", "Masculino","Média", new ArrayList<Veiculo>(), "598.537.490-41", dataNascimento);
		
		try {
			dataFundacao = new SimpleDateFormat("dd/MM/yyyy").parse("28/07/2008");
		}catch (ParseException e) {}
		c3 = new ClientePJ("Carrara Taxis Carrara", "Praça Mozar Firmeza, Rio de Janeiro, 761", new ArrayList<Veiculo>(), "15.656.646/0001-07", dataFundacao);
		System.out.println("Cliente 1 é " + (c1.validarId() ? "válido\n" : "inválido\n") +
				"Cliente 2 é " + (c2.validarId() ? "válido\n" : "inválido\n") +
				"Cliente 3 é " + (c3.validarId() ? "válido\n" : "inválido\n"));
		
		Veiculo v1 = new Veiculo("Chevrolet", "Impala", "KAZ2Y5", 1967);
		c1.cadastrarVeiculo(v1);
		Veiculo v2 = new Veiculo("Plymouth", "Junkerolla", "TR80R", 1986);
		c2.cadastrarVeiculo(v2);
		Veiculo v3 = new Veiculo("Volkswagen", "Santana", "JOP-2260", 2000);
		c3.cadastrarVeiculo(v3);
		Veiculo v4 = new Veiculo("Volkswagen", "Santana", "MBR-5862", 1998);
		c3.cadastrarVeiculo(v4);
		
		seguradoraTeste.cadastrarCliente(c1);
		System.out.println("Novo cliente cadastrado:\n" + c1);
		seguradoraTeste.cadastrarCliente(c2);
		System.out.println("Novo cliente cadastrado:\n" + c2);
		seguradoraTeste.cadastrarCliente(c3);
		System.out.println("Novo cliente cadastrado:\n" + c3);
		
		int j = 0;
		for (int i = 0; i < seguradoraTeste.getListaClientes().size(); i++) {
			if (!seguradoraTeste.getListaClientes().get(i).validarId()) {
				seguradoraTeste.removerCliente(seguradoraTeste.getListaClientes().get(i).getId());
				j++;
			}
		}
		if (j != 0)
			System.out.println("Número de clientes com id inválido removidos: " + j + "\n");
		
		seguradoraTeste.gerarSinistro("02/05/2022", "R. Saturnino de Brito, 573 - Cidade Universitária, Campinas - SP, 13083-852", v3, c3);
		seguradoraTeste.gerarSinistro("16/11/2016", "Praça Laudelino Amaral, 16 - Vila Nova Sorocaba, Sorocaba - SP, 18070-740", v4, c3);
		seguradoraTeste.gerarSinistro("02/05/2022", "742 Evergreen Terrace", v2, c2);
		
		String comando = "inicial";
		String aux;
		Scanner input = new Scanner(System.in);
		
		while (!comando.equals("0")) {
			switch (comando) {
			case "1":
				System.out.println("Tipo de Cliente(PF, PJ ou Vazio):");
				aux = input.nextLine();
				seguradoraTeste.listarClientes(aux);
				break;
				
			case "2":
				System.out.println("Id do Cliente:");
				aux = input.nextLine();
				seguradoraTeste.visualizarSinistro(aux);
				break;
				
			case "3":
				seguradoraTeste.listarSinistros();
				break;
			}
			
			System.out.println("Menu:\n"
					+ "1 - Listar Clientes\n"
					+ "2 - Vizualizar Sinistros\n"
					+ "3 - Listar Sinistros\n"
					+ "0 - Sair\n");
			comando = input.nextLine();
		}
		input.close();
	}
}

