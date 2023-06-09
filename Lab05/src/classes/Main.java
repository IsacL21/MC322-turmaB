package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	//Opcoes de cadastros
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
	
	//opcoes de listagem
	private static void opcaoListarClientes() {
		String tipoCliente;

		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		//recebe tipo do cliente
		do {tipoCliente = Input.printScan("Tipo do Cliente(PF,PJ ou vazio para ambos)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")|| tipoCliente.equals("")));
		
		seguradora.listarClientes(tipoCliente);
		return;
	}
	
	private static void opcaoListarSegurosCliente() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		Cliente cliente = Selecao.escolheCliente(seguradora.getListaClientes());
		
		ArrayList<Seguro> listaSeguros = seguradora.getSegurosPorCliente(cliente);
		if (listaSeguros.size() > 0) {
			System.out.println("Seguros:");
			for (Seguro i:listaSeguros)
				System.out.println(i.toString().indent(1));
		}
		else System.out.println("O cliente não possui nenhum seguro.");
	}
	
	private static void opcaoListarCondutoresSeguro() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		Seguro seguro = Selecao.escolheSeguro(seguradora.getListaSeguros());
		
		if (seguro.getListaCondutores().size() > 0) {
			System.out.println("Condutores:");
			for (Condutor i:seguro.getListaCondutores())
				System.out.println(i.toString().indent(1));
		}
		else System.out.println("O seguro não possui nenhum condutor.");
	}
	
	private static void opcaoListarSinistrosSeguro() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		Seguro seguro = Selecao.escolheSeguro(seguradora.getListaSeguros());
		
		if (seguro.getListaSinistros().size() > 0) {
			System.out.println("Sinistros:");
			for (Sinistro i:seguro.getListaSinistros())
				System.out.println(i.toString().indent(1));
		}
		else System.out.println("O seguro não possui nenhum sinistro.");
	}
	
	private static void opcaoListarSinistrosCondutor() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		Seguro seguro = Selecao.escolheSeguro(seguradora.getListaSeguros());
		Condutor condutor = Selecao.escolheCondutor(seguro.getListaCondutores());
		
		if (condutor.getListaSinistros().size() > 0) {
			System.out.println("Sinistros:");
			for (Sinistro i:condutor.getListaSinistros())
				System.out.println(i.toString().indent(1));
		}
		else System.out.println("O condutor não possui nenhum sinistro.");
	}
	
	private static void opcaoListarVeiculosCliente() {
		String tipoCliente;
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		
		do {tipoCliente = Input.printScan("Insira o tipo de cliente(PF ou PJ)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")));
		
		if (tipoCliente.equals("PF")){
			ClientePF cliente = Selecao.escolheClientePF(seguradora.getListaClientesPF());
			if (cliente.getListaVeiculos().size() > 0) {
				System.out.println("Lista de Veículos:");
				for (Veiculo i:cliente.getListaVeiculos())
					System.out.println(i.toString().indent(1));
			}
			else System.out.println("O cliente não possui veículos.");
		}
		else if (tipoCliente.equals("PJ")){
			ClientePJ cliente = Selecao.escolheClientePJ(seguradora.getListaClientesPJ());
			Frota frota = Selecao.escolheFrota(cliente.getListaFrotas());
			System.out.println(frota);
		}
		return;
	}
	
	//Opcoes de remocao
	private static void opcaoExcluirCliente() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		if (seguradora.removerCliente(Selecao.escolheCliente(seguradora.getListaClientes())))
			System.out.println("Cliente removido com sucesso!");
		else System.out.println("Erro ao remover cliente.");
		return;
	}
	
	private static void opcaoDesautorizarCondutor() {
		Seguro seguro = Selecao.escolheSeguro(Selecao.escolheSeguradora(listaSeguradoras).getListaSeguros());
		if (seguro.desautorizarCondutor(Selecao.escolheCondutor(seguro.getListaCondutores())))
			System.out.println("Condutor desautorizado com sucesso!");
		else System.out.println("Erro ao desautorizar condutor.");
		return;
	}
	
	private static void opcaoExcluirVeiculo() {
		String tipoCliente;
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		boolean returnBool = false;
		
		do {tipoCliente = Input.printScan("Insira o tipo de cliente(PF ou PJ)");}
		while (!(tipoCliente.equals("PF") || tipoCliente.equals("PJ")));
		
		if (tipoCliente.equals("PF")){
			ClientePF cliente = Selecao.escolheClientePF(seguradora.getListaClientesPF());
			Veiculo veiculo = Selecao.escolheVeiculo(cliente.getListaVeiculos());
			Seguro seguro = seguradora.getSeguroPorVeiculo(veiculo);
			//remove seguro referente ao veiculo
			if (seguro != null)
				seguradora.cancelarSeguro(seguro);
			returnBool = cliente.removerVeiculo(veiculo);
		}
		else if (tipoCliente.equals("PJ")){
			ClientePJ cliente = Selecao.escolheClientePJ(seguradora.getListaClientesPJ());
			Frota frota = Selecao.escolheFrota(cliente.getListaFrotas());
			returnBool = frota.removerVeiculo(Selecao.escolheVeiculo(frota.getListaVeiculos()));
		}
		
		if (returnBool)
			System.out.println("Veículo removido com sucesso!");
		else System.out.println("Erro ao remover veículo.");
		return;
	}
	
	private static void opcaoExcluirFrota() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		ClientePJ cliente = Selecao.escolheClientePJ(seguradora.getListaClientesPJ());
		Frota frota = Selecao.escolheFrota(cliente.getListaFrotas());
		Seguro seguro;
		//remove seguro referente a frota removida
		if ((seguro = seguradora.getSeguroPorFrota(frota)) != null)
			seguradora.cancelarSeguro(seguro);
		if (cliente.removerFrota(frota))
			System.out.println("Frota removida com sucesso!");
		else System.out.println("Erro ao remover frota.");
	}
	
	private static void opcaoCancelarSeguro() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		Seguro seguro = Selecao.escolheSeguro(seguradora.getListaSeguros());
		
		if (seguradora.cancelarSeguro(seguro))
			System.out.println("Seguro cancelado com sucesso!");
		else System.out.println("Erro ao cancelar seguro.");
		
	}
	
	private static void opcaoExcluirSinistro() {
		Seguradora seguradora = Selecao.escolheSeguradora(listaSeguradoras);
		Seguro seguro = Selecao.escolheSeguro(seguradora.getListaSeguros());
		Sinistro sinistro = Selecao.escolheSinistro(seguro.getListaSinistros());
		
		if (seguro.removerSinistro(sinistro))
			System.out.println("Sinistro removido com sucesso!");
		else System.out.println("Erro ao remover sinistro.");
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
			case CALCULAR_RECEITA:
				opcaoCalcularReceita();
				break;
			case SAIR:
				break;
		}
	}
	
	private static MenuOperacoes lerOpcaoMenuExterno() {
		System.out.println("Digite uma opcao: ");
		return MenuOperacoes.values()[Input.getIntInputBetween(0, MenuOperacoes.values().length)];
	}
	
	private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes op) {
			System.out.println("Digite uma opcao: ");
		return op.getSubmenu()[Input.getIntInputBetween(0, op.getSubmenu().length)];
	}
	
	public static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) {
		try {
			switch(opSubmenu) {
			case CADASTRAR_CLIENTE:
				opcaoCadastrarCliente();
				break;
			case AUTORIZAR_CONDUTOR:
				opcaoAutorizarCondutor();
				break;
			case CADASTRAR_VEICULO:
				opcaoCadastrarVeiculo();
				break;
			case INICIAR_FROTA:
				opcaoIniciarFrota();
				break;
			case CADASTRAR_SEGURO:
				opcaoCadastrarSeguro();
				break;
			case CADASTRAR_SEGURADORA:
				opcaoCadastrarSeguradora();
				break;
			case LISTAR_CLIENTES:
				opcaoListarClientes();
				break;
			case LISTAR_SEGUROS_CLIENTE:
				opcaoListarSegurosCliente();
				break;
			case LISTAR_CONDUTORES_SEGURO:
				opcaoListarCondutoresSeguro();
				break;
			case LISTAR_SINISTROS_SEGURO:
				opcaoListarSinistrosSeguro();
				break;
			case LISTAR_SINISTROS_CONDUTOR:
				opcaoListarSinistrosCondutor();
				break;
			case LISTAR_VEICULOS_CLIENTE:
				opcaoListarVeiculosCliente();
				break;
			case EXCLUIR_CLIENTE:
				opcaoExcluirCliente();
				break;
			case DESAUTORIZAR_CONDUTOR:
				opcaoDesautorizarCondutor();
				break;
			case EXCLUIR_VEICULO:
				opcaoExcluirVeiculo();
				break;
			case EXCLUIR_FROTA:
				opcaoExcluirFrota();
				break;
			case CANCELAR_SEGURO:
				opcaoCancelarSeguro();
				break;
			case EXCLUIR_SINISTRO:
				opcaoExcluirSinistro();
				break;
			case VOLTAR:
				break;
			}
		}
		catch (IllegalStateException e) {
			System.out.println("Erro ao tentar escolher elemento de uma lista vazia!");
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
		Date dataNascimento = null, dataFundacao = null, dataInicio = null, dataFim = null, dataSinistro = null;
		
		try {
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

