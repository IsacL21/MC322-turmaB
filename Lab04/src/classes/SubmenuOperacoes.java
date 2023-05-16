package classes;

public enum SubmenuOperacoes {
	
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SINISTROS_SEGURADORA("Listar sinistros da seguradora"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros do cliente"),
	LISTAR_VEICULOS_SEGURADORA("Listar veiculos da seguradora"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos do cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
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
