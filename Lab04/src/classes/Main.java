package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	
	/*Printa mensagem, le uma string e a retorna*/
	private static String printScan(String mensagem) {
		System.out.println(mensagem);
		return scanner.nextLine();
	}
	
	private static void opcaoGerarSinistro() {
		String placa, dataSinistro, enderecoSinistro;
		Veiculo veiculo = null;
		Seguradora seguradora;
		int i = -1;
		//recebe placa e procura veiculo nas seguradoras
		placa = printScan("Placa do veículo: ");
		while (veiculo == null && i < listaSeguradoras.size()-1) {
			i++;
			veiculo = listaSeguradoras.get(i).encontraVeiculo(placa);
		}
		if (veiculo == null) {
			//retorna erro se nao encontrar
			System.out.println("Veículo não encontrado.\n");
			return;
		}
		//recebe as outras informacoes se encontrar
		dataSinistro = printScan("Data:");
		enderecoSinistro = printScan("Endereço: ");
		if (listaSeguradoras.get(i).gerarSinistro(dataSinistro, enderecoSinistro, veiculo)) 
			System.out.println("Sinistro cadastrado com sucesso!\n");
		else
			System.out.println("Erro ao cadastrar sinistro");
		return;
	}
	
	private static void opcaoTransferirSeguro() {
		String idCliente, nomeSegDest;
		Cliente cliente = null;
		Seguradora segDest;
		int i = -1;
		//receber e procura cliente
		idCliente = printScan("Id do Cliente: ");
		while (cliente == null && i < listaSeguradoras.size()-1) {
			i++;
			cliente = listaSeguradoras.get(i).encontraCliente(idCliente);
		}
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		//recebe e procura seguradora destino
		nomeSegDest = printScan("Nome da seguradora a receber o cliente: ");
		if ((segDest = encontraSeguradora(nomeSegDest)) == null) {
			System.out.println("Seguradora não encontrada");
			return;
		}
		//ambos os itens anteriores foram encontrados
		if ((listaSeguradoras.get(i).removerCliente(cliente)) && (segDest.cadastrarCliente(cliente)))
			System.out.println("Seguro transferio com sucesso!");
		else
			System.out.println("Erro ao transferir seguro");
		return;
	}
	
	private static void opcaoCalcularReceita() {
		String nomeSeguradora;
		Seguradora seguradora;
		nomeSeguradora = printScan("Nome da seguradora: ");
		if ((seguradora = encontraSeguradora(nomeSeguradora)) == null) {
			System.out.println("Seguradora não encontrada");
			return;
		}
		System.out.println("Receita da Seguradora " + seguradora.getNome() + ":" + seguradora.calculaReceita());
		return;
	}
	
	private static void opcaoCadastrarCliente() {
		String nomeSeguradora,tipoCliente,clienteId,nome,endereco;
		Seguradora seguradora;
		Cliente cliente;
		boolean isValidDate;
		
		//recebe e procura seguradora
		nomeSeguradora = printScan("Nome da seguradora a receber o cliente: ");
		if ((seguradora = encontraSeguradora(nomeSeguradora)) == null) {
			System.out.println("Seguradora não encontrada");
			return;
		}
		
		//recebe tipo do cliente
		do {tipoCliente = printScan("Tipo do Cliente(PF ou PJ)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")));
		
		//cadastra cliente PF
		if (tipoCliente.equals("PF")) {
			String strDataNascimento,strDataLicenca,classeEconomica,educacao,genero;
			Date dataNascimento = null,dataLicenca = null;
			//recebe nome
			do {nome = printScan("Nome do Cliente: ");}
			while (Validacao.validarNome(nome));
			//recebe cpf
			do {clienteId = printScan("Cpf: ");}
			while (Validacao.validarCPF(clienteId));
			//receber data de nascimento
			do {
				strDataNascimento = printScan("Data de Nascimento(dd/mm/yyyy): ");
				try {
					dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(strDataNascimento);
					isValidDate = true;
				} catch (ParseException e) { isValidDate = false; }
			}while (!isValidDate);
			//receber data da licenca
			do {
				strDataLicenca = printScan("Data da Licença(dd/mm/yyyy): ");
				try {
					dataLicenca = new SimpleDateFormat("dd/MM/yyyy").parse(strDataLicenca);
					isValidDate = true;
				} catch (ParseException e) { isValidDate = false; }
			}while (!isValidDate);
			//receber classeEconomica,endereço, educacao e genero
			endereco = printScan("Endereço: ");
			classeEconomica = printScan("Classe Econômica: ");
			educacao = printScan("Educação: ");
			genero = printScan("Gênero: ");
			
			//instancia o cliente
			cliente = new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica,
					new ArrayList<Veiculo>(), clienteId, dataNascimento);
			//cadastra cliente
			if (seguradora.cadastrarCliente(cliente))
				System.out.println("Cliente cadastrado com sucesso!\n");
			else
				System.out.println("Erro ao cadastrar cliente\n");
			return;
			}
		//cadastra cliente PJ
		if (tipoCliente.equals("PJ")) {
			String strDataFundacao;
			Date dataFundacao = null;
			int qtdeFuncionarios = 0;
			boolean isInt;
			//recebe nome
			nome = printScan("Nome do Cliente: ");
			//recebe cpf
			do {clienteId = printScan("Cnpj: ");}
			while (Validacao.validarCNPJ(clienteId));
			//recebe data fundacao
			do {
				strDataFundacao = printScan("Data de Nascimento(dd/mm/yyyy): ");
				try {
					dataFundacao = new SimpleDateFormat("dd/MM/yyyy").parse(strDataFundacao);
					isValidDate = true;
				} catch (ParseException e) { isValidDate = false; }
			}while (!isValidDate);
			//receber endereco e qtdeFuncionarios
			do {
				try {
					qtdeFuncionarios = Integer.parseInt(printScan("Quantidade de Funcionarios: "));
					isInt = true;
					}
				catch (NumberFormatException e) { isInt = false; }
			}while(!isInt);
			endereco = printScan("Endereço: ");
			//instancia o cliente
			cliente = new ClientePJ(nome, endereco, new ArrayList<Veiculo>(), clienteId, dataFundacao, qtdeFuncionarios);
			//cadastra cliente
			if (seguradora.cadastrarCliente(cliente))
				System.out.println("Cliente cadastrado com sucesso!\n");
			else
				System.out.println("Erro ao cadastrar cliente\n");
			return;
		}
	}
	
	private static void opcaoCadastrarVeiculo() {
		String idCliente, marca, modelo, placa;
		Cliente cliente = null;
		Veiculo veiculo;
		boolean isInt;
		int i = -1, anoFabricacao = -1;
		//receber e procura cliente
		idCliente = printScan("Id do Dono do veículo: ");
		while (cliente == null && i < listaSeguradoras.size()-1) {
			i++;
			cliente = listaSeguradoras.get(i).encontraCliente(idCliente);
		}
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		//recebe parametros
		marca = printScan("Marca: ");
		modelo = printScan("Modelo: ");
		do {
			try {
				anoFabricacao = Integer.parseInt(printScan("Ano de Fabricação: "));
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; }
		}while(!isInt);
		placa = printScan("Placa: ");
		//instancia veiculo
		veiculo = new Veiculo(marca, modelo, placa, anoFabricacao);
		//cadastra veiculo
		if (cliente.cadastrarVeiculo(veiculo))
			System.out.println("Veículo cadastrado com sucesso!\n");
		else
			System.out.println("Erro ao cadastrar veículo\n");
		return;
	}
	
	private static void opcaoCadastrarSeguradora() {
		String nome, telefone, email, endereco;
		Seguradora seguradora;
		//recebe parametros
		nome = printScan("Nome da Seguradora: ");
		telefone = printScan("Telefone: ");
		email = printScan("Email: ");
		endereco = printScan("Endereço: ");
		//instancia seguradora
		seguradora = new Seguradora(nome, telefone, email, endereco, new ArrayList<Sinistro>(), new LinkedList<Cliente>());
		//adiciona na lista de seguradoras
		if (listaSeguradoras.add(seguradora))
			System.out.println("Seguradora cadastrada com sucesso!\n");
		else
			System.out.println("Erro ao cadastrar seguradora\n");
		return;
	}
	
	private static void opcaoListarClientes() {
		String nomeSeguradora,tipoCliente;
		Seguradora seguradora;
		
		//recebe e procura seguradora
		nomeSeguradora = printScan("Nome da seguradora: ");
		if ((seguradora = encontraSeguradora(nomeSeguradora)) == null) {
			System.out.println("Seguradora não encontrada");
			return;
		}
		//recebe tipo do cliente
		do {tipoCliente = printScan("Tipo do Cliente(PF,PJ ou vazio para ambos)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")|| tipoCliente.equals("")));
		
		seguradora.listarClientes(tipoCliente);
		return;
		
	}
	
	private static void opcaoListarSinistrosSeguradora() {
		String nomeSeguradora;
		Seguradora seguradora;
		
		//recebe e procura seguradora
		nomeSeguradora = printScan("Nome da seguradora: ");
		if ((seguradora = encontraSeguradora(nomeSeguradora)) == null) {
			System.out.println("Seguradora não encontrada");
			return;
		}
		
		seguradora.listarSinistros();
		return;
	}
	
	private static void opcaoListarSinistrosCliente() {
		String idCliente;
		Cliente cliente = null;
		int i = -1;
		//receber e procura cliente
		idCliente = printScan("Id do Cliente: ");
		while (cliente == null && i < listaSeguradoras.size()-1) {
			i++;
			cliente = listaSeguradoras.get(i).encontraCliente(idCliente);
		}
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		//cliente foi encontrado
		listaSeguradoras.get(i).visualizarSinistro(cliente);
		return;
	}
	
	private static void opcaoListarVeiculosCliente() {
		String idCliente;
		Cliente cliente = null;
		int i = -1;
		//receber e procura cliente
		idCliente = printScan("Id do Cliente: ");
		while (cliente == null && i < listaSeguradoras.size()-1) {
			i++;
			cliente = listaSeguradoras.get(i).encontraCliente(idCliente);
		}
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		//imprime a lista
		System.out.println(cliente.stringListaVeiculos());
		return;
	}
	
	private static void opcaoListarVeiculosSeguradora() {
		String nomeSeguradora;
		Seguradora seguradora;
		
		//recebe e procura seguradora
		nomeSeguradora = printScan("Nome da seguradora: ");
		if ((seguradora = encontraSeguradora(nomeSeguradora)) == null) {
			System.out.println("Seguradora não encontrada");
			return;
		}
		System.out.println("Veículos:\n");
		for (Cliente i : seguradora.getListaClientes()) {
			System.out.println(i.stringListaVeiculos());
		}
		return;
	}
	
	private static void opcaoExcluirCliente() {
		String idCliente;
		Cliente cliente = null;
		int i = -1;
		//receber e procura cliente
		idCliente = printScan("Id do Cliente: ");
		while (cliente == null && i < listaSeguradoras.size()-1) {
			i++;
			cliente = listaSeguradoras.get(i).encontraCliente(idCliente);
		}
		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}
		if (listaSeguradoras.get(i).removerCliente(cliente))
			System.out.println("Cliente removido com sucesso!");
		else 
			System.out.println("Erro ao remover cliente");
		return;
	}
	
	private static void opcaoExcluirVeiculo() {}
	
	private static void opcaoExcluirSinistro() {}
	
		
	
	private static Seguradora encontraSeguradora(String nome) {
		for (Seguradora i:listaSeguradoras) {
			if (i.getNome().toLowerCase().equals(nome.toLowerCase()))
				return i;
		}
		return null;
	}
	
	private static void exibirMenuExterno() {
		MenuOperacoes menuOperacoes[] = MenuOperacoes.values();
		System.out.println("Menu principal");
		for(MenuOperacoes op: menuOperacoes) {
			System.out.println(op.ordinal()+1 + " - " + op.getDescricao());
		}
	}
	
	private static void exibirSubmenu(MenuOperacoes op) {
		SubmenuOperacoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i+1 +" - " + submenu[i].getDescricao());
		}
	}
	
	private static void executarOpcaoMenuExterno(MenuOperacoes op) {
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				opcaoGerarSinistro();
				break;
			case TRANSFERIR_SEGURO:
				opcaoTransferirSeguro();
				break;
			case CALCULAR_RECEITA:
				opcaoCalcularReceita();
				break;
			//case SAIR:
		}
	}
	
	private static MenuOperacoes lerOpcaoMenuExterno() {
		int opUsuario = -1;
		boolean isInt;
		MenuOperacoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			try {
				opUsuario = Integer.parseInt(scanner.nextLine()) - 1;
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; }
		}while(!(isInt) && (opUsuario < 0 || opUsuario > MenuOperacoes.values().length - 1));
		opUsuarioConst = MenuOperacoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes op) {
		int opUsuario = -1;
		boolean isInt;
		SubmenuOperacoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			try {
				opUsuario = Integer.parseInt(scanner.nextLine()) - 1;
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; }
		}while(!(isInt) && (opUsuario < 0 || opUsuario > MenuOperacoes.values().length - 1));
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	public static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) {
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			opcaoCadastrarCliente();
			break;
		case CADASTRAR_VEICULO:
			opcaoCadastrarVeiculo();
			break;
		case CADASTRAR_SEGURADORA:
			opcaoCadastrarSeguradora();
			break;
		case LISTAR_CLIENTES:
			opcaoListarClientes();
			break;
		case LISTAR_SINISTROS_SEGURADORA:
			opcaoListarSinistrosSeguradora();
			break;
		case LISTAR_SINISTROS_CLIENTE:
			opcaoListarSinistrosCliente();
			break;
		case LISTAR_VEICULOS_SEGURADORA:
			opcaoListarVeiculosSeguradora();
			break;
		case LISTAR_VEICULOS_CLIENTE:
			opcaoListarVeiculosCliente();
			break;
		case EXCLUIR_CLIENTE:
			System.out.println("Chamar metodo excluir cliente");
			break;
		case EXCLUIR_VEICULO:
			System.out.println("Chamar metodo excluir veiculo");
			break;
		case EXCLUIR_SINISTRO:
			System.out.println("Chamar metodo excluir sinistro");
			break;
		//case VOLTAR:
			//break;
		}
	}
	
		private static void executarSubmenu(MenuOperacoes op) {
			SubmenuOperacoes opSubmenu;
			do {
				exibirSubmenu(op);
				opSubmenu = lerOpcaoSubmenu(op);
				executarOpcaoSubMenu(opSubmenu);
			}while(opSubmenu != SubmenuOperacoes.VOLTAR);
		}
	
	
	
	
	public static void main(String[] args) {
		//instanciando seguradora e clientes
		Seguradora seguradoraTeste = new Seguradora ("Safezinha", "(15) 4004-5274","safezinha@gmail.com",
					"Av Getúlio Vargas, 2900", new ArrayList<Sinistro>(), new LinkedList<Cliente>());
		listaSeguradoras.add(seguradoraTeste);
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
		c3 = new ClientePJ("Carrara Taxis Carrara", "Praça Mozar Firmeza, Rio de Janeiro, 761", new ArrayList<Veiculo>(), "15.656.646/0001-07", dataFundacao, 50);
		
		//validando IDs
		System.out.println("Cliente 1 é " + (Validacao.validarCPF(c1.getId()) ? "válido\n" : "inválido\n") +
				"Cliente 2 é " + (Validacao.validarCPF(c2.getId())? "válido\n" : "inválido\n") +
				"Cliente 3 é " + (Validacao.validarCNPJ(c3.getId()) ? "válido\n" : "inválido\n"));
		
		//instanciando e cadastrando veiculos
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
		
		//gerando sinistros
		seguradoraTeste.gerarSinistro("02/05/2022", "R. Saturnino de Brito, 573 - Cidade Universitária, Campinas - SP, 13083-852", v3);
		seguradoraTeste.gerarSinistro("16/11/2016", "Praça Laudelino Amaral, 16 - Vila Nova Sorocaba, Sorocaba - SP, 18070-740", v4);
		seguradoraTeste.gerarSinistro("02/05/2022", "742 Evergreen Terrace", v2);
		
		//listando clientes
		seguradoraTeste.listarClientes("");
		
		//visualizandos sinistros do cliente c3
		seguradoraTeste.visualizarSinistro("15.656.646/0001-07");
		
		//listando sinistros
		seguradoraTeste.listarSinistros();
		
		//Atualizando o atributo valorSeguro de cada cliente cadastrado na seguradora
		for (Cliente i: seguradoraTeste.getListaClientes()) {
			seguradoraTeste.calcularPrecoSeguroCliente(i);
		}
		
		//Mostrando a receita total
		System.out.println("A receita total da seguradora " + seguradoraTeste.getNome() + " é: " + seguradoraTeste.calculaReceita());
		
			MenuOperacoes op;
			do {
				exibirMenuExterno();
				op = lerOpcaoMenuExterno();
				executarOpcaoMenuExterno(op);
			}while(op != MenuOperacoes.SAIR);
			System.out.println("Saiu do sistema");
			scanner.close();
		}
		
	}

