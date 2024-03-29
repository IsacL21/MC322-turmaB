package classes;

public enum MenuOperacoes {
	CADASTROS("Cadastros", new SubmenuOperacoes[] {
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.AUTORIZAR_CONDUTOR,
			SubmenuOperacoes.CADASTRAR_VEICULO,
			SubmenuOperacoes.INICIAR_FROTA,
			SubmenuOperacoes.CADASTRAR_SEGURO,
			SubmenuOperacoes.CADASTRAR_SEGURADORA,
			SubmenuOperacoes.VOLTAR
	}),
	LISTAR("Listar", new SubmenuOperacoes[] {
			SubmenuOperacoes.LISTAR_CLIENTES,
			SubmenuOperacoes.LISTAR_SEGUROS_CLIENTE,
			SubmenuOperacoes.LISTAR_CONDUTORES_SEGURO,
			SubmenuOperacoes.LISTAR_SINISTROS_SEGURO,
			SubmenuOperacoes.LISTAR_SINISTROS_CONDUTOR,
			SubmenuOperacoes.LISTAR_VEICULOS_CLIENTE,
			SubmenuOperacoes.VOLTAR
	}),
	EXCLUIR("Excluir", new SubmenuOperacoes[] {
			SubmenuOperacoes.EXCLUIR_CLIENTE,
			SubmenuOperacoes.DESAUTORIZAR_CONDUTOR,
			SubmenuOperacoes.EXCLUIR_VEICULO,
			SubmenuOperacoes.EXCLUIR_FROTA,
			SubmenuOperacoes.CANCELAR_SEGURO,
			SubmenuOperacoes.EXCLUIR_SINISTRO,
			SubmenuOperacoes.VOLTAR}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	SAIR("Sair", new SubmenuOperacoes[] {});
	
	private final String descricao;
	private final SubmenuOperacoes[] submenu;
	
	MenuOperacoes(String descricao, SubmenuOperacoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOperacoes[] getSubmenu() {
		return submenu;
	}
}