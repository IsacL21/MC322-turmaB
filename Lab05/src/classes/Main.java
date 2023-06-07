package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	//atributos
	public static final Scanner scanner = new Scanner(System.in);
	private static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	
	
	//implementacao das opcoes
	
	private static void opcaoGerarSinistro() {
		String enderecoSinistro;
		Date dataSinistro;	
		Condutor condutor;
		Seguro seguro;
		
		seguro = Selecao.escolheSeguro(Selecao.escolheSeguradora(listaSeguradoras).getListaSeguros());
		condutor = Selecao.escolheCondutor(seguro.getListaCondutores());
		
		//receber data do sinistro
		System.out.println("Data do Sinistro:");
		dataSinistro = Input.recebeDataValida();
		enderecoSinistro = Input.printScan("Endereço: ");
		if (seguro.gerarSinistro(dataSinistro, enderecoSinistro, condutor))
			System.out.println("Sinistro gerado com sucesso!");
		else System.out.println("Erro ao gerar sinistro.");
		}
		
	private static void opcaoCalcularReceita() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		System.out.println("Receita da Seguradora " + seguradora.getNome() + ":" 
		+ seguradora.calculaReceita());
		return;
	}
	
	private static void opcaoCadastrarCliente() {
		String tipoCliente;
		Seguradora seguradora;
		boolean returnBool = false;
		
		seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		
		//recebe tipo do cliente
		do {tipoCliente = Input.printScan("Tipo do Cliente(PF ou PJ)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")));
		
		//cadastra cliente PF
		if (tipoCliente.equals("PF")) 
			returnBool = seguradora.cadastrarCliente(Input.instanciaClientePF());
		else if (tipoCliente.equals("PJ")) 
			returnBool = seguradora.cadastrarCliente(Input.instanciaClientePJ());
		if (returnBool)
			System.out.println("Cliente cadastrado com sucesso!");
		else System.out.println("Erro ao cadastrar cliente.");
		return;
	}
	
	private static void opcaoAutorizarCondutor() {
		Seguro seguro;
		seguro = Selecao.escolheSeguro(Selecao.escolheSeguradora(listaSeguradoras).getListaSeguros());

		if (seguro.autorizarCondutor(Input.instanciaCondutor()))
			System.out.println("Condutor autorizado com sucesso!");
		else System.out.println("Erro ao autorizar condutor.");
		return;
	}
	
	private static void opcaoCadastrarVeiculo() {
		String tipoCliente;
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		boolean returnBool = false;
		
		do {tipoCliente = Input.printScan("Insira o tipo de cliente(PF ou PJ)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")));
		
		if (tipoCliente.equals("PF")){
			ClientePF cliente = Selecao.escolheClientePF(seguradora.getListaClientesPF());
			returnBool = cliente.cadastrarVeiculo(Input.instaciaVeiculo());
		}
		else if (tipoCliente.equals("PJ")){
			ClientePJ cliente = Selecao.escolheClientePJ(seguradora.getListaClientesPJ());
			Frota frota = Selecao.escolheFrota(cliente.getListaFrotas());
			returnBool = frota.adicionarVeiculo(Input.instaciaVeiculo());
		}
		
		if (returnBool)
			System.out.println("Veículo cadastrado com sucesso!");
		else System.out.println("Erro ao cadastrar veículo.");
		return;
	}
	
	private static void opcaoIniciarFrota() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		ClientePJ cliente = Selecao.escolheClientePJ(seguradora.getListaClientesPJ());
		
		String code = Input.printScan("Code da Frota:");
		if (cliente.cadastrarFrota(new Frota(code, new ArrayList<Veiculo>())))
			System.out.println("Frota inicializada com sucesso!");
		else System.out.println("Erro ao iniciar frota.");
	}
	
	private static void opcaoCadastrarSeguradora() {
		if (listaSeguradoras.add(Input.instanciaSeguradora()))
			System.out.println("Seguradora cadastrada com sucesso!\n");
		else
			System.out.println("Erro ao cadastrar seguradora\n");
		return;
	}
	
	private static void opcaoCadastrarSeguro() {
		boolean returnBool = false;
		
		System.out.println("Data de Início:");
		Date dataInicio = Input.recebeDataValida();
		
		System.out.println("Data de Término:");
		Date dataFim = Input.recebeDataValida();
		
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		
		String tipoCliente;
		do {tipoCliente = Input.printScan("Insira o tipo de cliente(PF ou PJ)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")));
		
		if (tipoCliente.equals("PF")) {
			ClientePF cliente = Selecao.escolheClientePF(seguradora.getListaClientesPF());
			Veiculo veiculo = Selecao.escolheVeiculo(cliente.getListaVeiculos());
			returnBool = seguradora.gerarSeguro(dataInicio, dataFim, new ArrayList<Condutor>(), cliente, veiculo);
		}
		if (tipoCliente.equals("PJ")) {
			ClientePJ cliente = Selecao.escolheClientePJ(seguradora.getListaClientesPJ());
			Frota frota = Selecao.escolheFrota(cliente.getListaFrotas());
			returnBool = seguradora.gerarSeguro(dataInicio, dataFim, new ArrayList<Condutor>(), cliente, frota);
		}
		
		if (returnBool)
			System.out.println("Seguro cadastrado com sucesso!");
		else System.out.println("Erro ao cadastrar seguro");
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
				//opcaoTransferirSeguro();
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
		Date dataNascimento = null, dataLicenca = null, dataFundacao = null, dataInicio = null, dataFim = null, dataSinistro = null;
		
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

