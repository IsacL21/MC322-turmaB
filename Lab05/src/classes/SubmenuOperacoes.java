package classes;

public enum SubmenuOperacoes {
	
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	AUTORIZAR_CONDUTOR("Autorizar condutor"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	INICIAR_FROTA("Iniciar frota"),
	CADASTRAR_SEGURO("Cadastrar Seguro"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SEGUROS_CLIENTE("Listar seguros do cliente"),
	LISTAR_CONDUTORES_SEGURO("Listar condutores do seguro"),
	LISTAR_SINISTROS_SEGURO("Listar sinistros do seguro"),
	LISTAR_SINISTROS_CONDUTOR("Listar sinistros do condutor"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos do cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	DESAUTORIZAR_CONDUTOR("Desautorizar condutor"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_FROTA("Excluir frota"),
	CANCELAR_SEGURO("Cancelar seguro"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	VOLTAR("Voltar");
	
	private final String descricao;
	
	SubmenuOperacoes(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
