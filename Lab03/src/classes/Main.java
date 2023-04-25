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
		
		c1.cadastrarVeiculo(new Veiculo("Chevrolet", "Impala", "KAZ2Y5", 1967));
		c2.cadastrarVeiculo(new Veiculo("Plymouth", "Junkerolla", "TR80R", 1986));
		c3.cadastrarVeiculo(new Veiculo("Volkswagen", "Santana", "JOP-2260", 2000));
		c3.cadastrarVeiculo(new Veiculo("Volkswagen", "Santana", "MBR-5862", 1998));
		
		seguradoraTeste.cadastrarCliente(c1);
		seguradoraTeste.cadastrarCliente(c2);
		seguradoraTeste.cadastrarCliente(c3);
		
		int j = 0;
		for (Cliente i:seguradoraTeste.getListaClientes()) {
			if (!i.validarId()) {
				seguradoraTeste.removerCliente(i.getId());
				j++;
			}
		}
		if (j != 0)
			System.out.println("Número de clientes com id inválido removido: " + j + "\n");
		
		
		
		
		
		
		
			 
		}
	}

