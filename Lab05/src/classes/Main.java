package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	//atributos
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	
	
	private static int escolheSeguradora(ArrayList<Seguradora> lista) {
		int opUsuario = -1;
		boolean isInt = false;
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(i + "-" + lista.get(i).getNome());
		}
		do {
			try {
				opUsuario = Integer.parseInt(scanner.nextLine()) - 1;
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; System.out.println("insira uma opção válida!"); }
		}while(!(isInt) && (opUsuario < 0 || opUsuario > lista.size()));
		return opUsuario;
	}
	
	private static int escolheCliente(ArrayList<Cliente> lista) {
		int opUsuario = -1;
		boolean isInt = false;
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(i + "-" + lista.get(i).getNome() + "(" + lista.get(i).getId() + ")");
		}
		do {
			try {
				opUsuario = Integer.parseInt(scanner.nextLine()) - 1;
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; System.out.println("insira uma opção válida!"); }
		}while(!(isInt) && (opUsuario < 0 || opUsuario > lista.size()));
		return opUsuario;
	}
	
	private static int escolheVeiculo(ArrayList<Veiculo> lista) {
		int opUsuario = -1;
		boolean isInt = false;
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(i + "-" + lista.get(i));
		}
		do {
			try {
				opUsuario = Integer.parseInt(scanner.nextLine()) - 1;
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; System.out.println("insira uma opção válida!"); }
		}while(!(isInt) && (opUsuario < 0 || opUsuario > lista.size()));
		return opUsuario;
	}
	
	private static int escolheFrota(ArrayList<Frota> lista) {
		int opUsuario = -1;
		boolean isInt = false;
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(i + "-" + lista.get(i).getCode());
		}
		do {
			try {
				opUsuario = Integer.parseInt(scanner.nextLine()) - 1;
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; System.out.println("insira uma opção válida!"); }
		}while(!(isInt) && (opUsuario < 0 || opUsuario > lista.size()));
		return opUsuario;
	}
	
	
	
	//implementacao das opcoes
	private static void opcaoGerarSinistro() {
		String placa, dataSinistro, enderecoSinistro;
		Veiculo veiculo = null;
		Seguradora seguradora;
		int seguradoraIndex, clienteIndex;
		
		System.out.println("Selecione a seguradora:");
		seguradoraIndex = escolheSeguradora(listaSeguradoras);
			
		
		System.out.println("Selecione o cliente:");
		seguradoraIndex = escolheCliente(listaSeguradoras.get(seguradoraIndex).getListaClientes());
		
		if (listaSeguradoras.get(seguradoraIndex).getListaClientes().get(clienteIndex).getTipo().equals("PF"))
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
	
	private static void opcaoCalcularReceita() {
		int seguradoraIndex;
		System.out.println("Selecione a seguradora:");
		seguradoraIndex = escolheSeguradora(listaSeguradoras);
		System.out.println("Receita da Seguradora " + listaSeguradoras.get(seguradoraIndex).getNome() + ":" 
		+ listaSeguradoras.get(seguradoraIndex).calculaReceita());
		return;
	}
	
	private static void opcaoCadastrarCliente() {
		String nomeSeguradora,tipoCliente,clienteId,nome,telefone,endereco,email;
		Seguradora seguradora;
		Cliente cliente;
		boolean isValidDate;
		int seguradoraIndex;
		
		System.out.println("Selecione a seguradora:");
		seguradoraIndex = escolheSeguradora(listaSeguradoras);
		
		//recebe tipo do cliente
		do {tipoCliente = printScan("Tipo do Cliente(PF ou PJ)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")));
		
		//cadastra cliente PF
		if (tipoCliente.equals("PF")) {
			String strDataNascimento,educacao,genero;
			Date dataNascimento = null;
			//recebe nome
			do {nome = printScan("Nome do Cliente: ");}
			while (!Validacao.validarNome(nome));
			//recebe cpf
			do {clienteId = printScan("Cpf: ");}
			while (!Validacao.validarCPF(clienteId));
			//receber data de nascimento
			do {
				strDataNascimento = printScan("Data de Nascimento(dd/mm/yyyy): ");
				try {
					dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(strDataNascimento);
					isValidDate = true;
				} catch (ParseException e) { isValidDate = false; }
			}while (!isValidDate);
			telefone = printScan("Telefone ");
			endereco = printScan("Endereço: ");
			email = printScan("Email: ");
			educacao = printScan("Educação: ");
			genero = printScan("Gênero: ");
			
			//instancia o cliente
			cliente = new ClientePF(nome, telefone, endereco, email, educacao, genero, clienteId, dataNascimento);
			//cadastra cliente
			if (listaSeguradoras.get(seguradoraIndex).cadastrarCliente(cliente))
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
			while (!Validacao.validarCNPJ(clienteId));
			//recebe data fundacao
			do {
				strDataFundacao = printScan("Data de Fundação(dd/mm/yyyy): ");
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
			telefone = printScan("Telefone ");
			endereco = printScan("Endereço: ");
			email = printScan("Email: ");
			//instancia o cliente
			cliente = new ClientePJ(nome, telefone, endereco, email, clienteId, dataFundacao, qtdeFuncionarios);
			//cadastra cliente
			if (listaSeguradoras.get(seguradoraIndex).cadastrarCliente(cliente))
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
	
	private static void opcaoExcluirVeiculo() {
		String placa;
		Veiculo veiculo = null;
		Cliente cliente = null;
		int i = -1;
		//receber e procura cliente
		placa = printScan("Placa do Veículo: ");
		while (veiculo == null && i < listaSeguradoras.size()-1) {
			i++;
			veiculo = listaSeguradoras.get(i).encontraVeiculo(placa);
		}
		if (veiculo == null) {
			System.out.println("Veículo não encontrado.");
			return;
		}
		cliente = listaSeguradoras.get(i).encontraDono(veiculo);
		if (cliente.removerVeiculo(veiculo))
			System.out.println("Veículo removido com sucesso!");
		else
			System.out.println("Erro ao remover veículo.");
		return;
	}
	
	private static void opcaoExcluirSinistro() {
		boolean isInt;
		int idSinistro=-1,i=-1;
		Sinistro sinistro = null;
		do {
			try {
				idSinistro = Integer.parseInt(printScan("Id do Sinistro: "));
				isInt = true;
				}
			catch (NumberFormatException e) { isInt = false; }
		}while(!isInt);
		while (sinistro == null && i < listaSeguradoras.size()-1) {
			i++;
			int j =-1;
			while (sinistro == null && j < listaSeguradoras.get(i).getListaSinistros().size()-1) {
				j++;
				if ((listaSeguradoras.get(i).getListaSinistros().get(j).getID()) == idSinistro)
					sinistro = listaSeguradoras.get(i).getListaSinistros().get(j);
			}
		}
		if (listaSeguradoras.get(i).getListaSinistros().remove(sinistro))
			System.out.println("Sinistro removido com sucesso!");
		else
			System.out.println("Erro ao remover SInistro.");
		return;
		
	}
	
	
	//metodos auxiliares
	/*Printa mensagem, le uma string e a retorna*/
	private static String printScan(String mensagem) {
		System.out.println(mensagem);
		return scanner.nextLine();
	}
	
	private static Seguradora encontraSeguradora(String nome) {
		for (Seguradora i:listaSeguradoras) {
			if (i.getNome().toLowerCase().equals(nome.toLowerCase()))
				return i;
		}
		return null;
	}
	
	//metodos do menu
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
			opcaoExcluirCliente();
			break;
		case EXCLUIR_VEICULO:
			opcaoExcluirVeiculo();
			break;
		case EXCLUIR_SINISTRO:
			opcaoExcluirSinistro();
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
	
	
	
	//MAIN
	public static void main(String[] args) {
		//instanciando seguradora e clientes
		Seguradora seguradoraTeste = new Seguradora ("01.671.505/0001-40","Safezinha", "15 4004-5274","safezinha@gmail.com", "Av Getúlio Vargas, 2900");
		listaSeguradoras.add(seguradoraTeste);
		ClientePF c1;
		ClientePJ c2;
		Date dataNascimento = null, dataLicenca = null, dataFundacao = null, dataInicio = null, dataFim = null, dataSinistro = null7
				;
		
		try {
			dataLicenca = new SimpleDateFormat("dd/MM/yyyy").parse("20/10/1996");
			dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("24/01/1979");
		}catch (ParseException e) {}
		c1 = new ClientePF("Dean Winchester", "305-296-5314", "rooster89@gmail.com", "Cross Junction, Virginia, 22625",
				"Ensino Médio", "Masculino","126.816.874-21", dataNascimento);
		
		try {
			dataFundacao = new SimpleDateFormat("dd/MM/yyyy").parse("28/07/2008");
		}catch (ParseException e) {}
		c2 = new ClientePJ("Carrara Taxis Carrara","21 3618-6124","Carrara@ctc.br", "Praça Mozar Firmeza, Rio de Janeiro, 761","15.656.646/0001-07", dataFundacao, 50);
		
		//instanciando e cadastrando veiculos e frota
		Veiculo v1 = new Veiculo("Chevrolet", "Impala", "KAZ2Y5", 1967);
		c1.cadastrarVeiculo(v1);
		Veiculo v2 = new Veiculo("Volkswagen", "Santana", "JOP-2260", 2000);
		Veiculo v3 = new Veiculo("Volkswagen", "Santana", "MBR-5862", 1998);
		Frota f1 = new Frota("Prime", new ArrayList<Veiculo>());
		f1.adicionarVeiculo(v3);
		f1.adicionarVeiculo(v2);
		c2.cadastrarFrota(f1);
		
		//cadastrando clientes
		seguradoraTeste.cadastrarCliente(c1);
		System.out.println("Novo cliente cadastrado:\n" + c1);
		seguradoraTeste.cadastrarCliente(c2);
		System.out.println("Novo cliente cadastrado:\n" + c2);
		
		//cadastrando condutores
		Condutor cond1 = new Condutor("Dean Winchester", "305-296-5314", "rooster89@gmail.com", "Cross Junction, Virginia, 22625",
				"126.816.874-21", c1.getDataNascimento());
		System.out.println("Novo condutor:\n" + cond1);
		try {
			dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse("28/06/1982");
		}catch (ParseException e) {}
		Condutor cond2 = new Condutor("Paje Murici", "21 3772-3348", "p849152@gmail.com", "Praça Mozar Firmeza, Rio de Janeiro, 892",
				"224.137.990-52", dataNascimento);
		System.out.println("Novo condutor:\n" + cond2);
		
		//gerando seguros
		try {
			dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2023");
			dataFim = new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2029");
		}catch (ParseException e) {}
		seguradoraTeste.gerarSeguro(dataInicio, dataFim, new ArrayList<Condutor>(), c1, v1);
		seguradoraTeste.getSegurosPorCliente(c1).get(0).autorizarCondutor(cond1);
		System.out.println("Novo seguro:\n" + seguradoraTeste.getSegurosPorCliente(c1).get(0));
		seguradoraTeste.gerarSeguro(dataInicio, dataFim, new ArrayList<Condutor>(), c2, f1);
		seguradoraTeste.getSegurosPorCliente(c2).get(0).autorizarCondutor(cond2);
		System.out.println("Novo seguro:\n" + seguradoraTeste.getSegurosPorCliente(c2).get(0));
		
		//gerando sinistro
		try { dataSinistro = new SimpleDateFormat("dd/MM/yyyy").parse("02/05/2022"); } catch (ParseException e) {}
		seguradoraTeste.getSegurosPorCliente(c1).get(0).gerarSinistro(dataSinistro, "R. Saturnino de Brito, 573 - Cidade Universitária, Campinas - SP, 13083-852", cond1);
		
		//listando clientes
		seguradoraTeste.listarClientes("");
		
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

