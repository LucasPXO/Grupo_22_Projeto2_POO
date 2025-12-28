/**
 * Classe responsável pela ação de recolher itens do cenário.
 * Faz a ponte entre o Local atual e o inventário do Jogador.
 */
public class ComandoRecolher implements AcaoComando {
    // --- Atributos ---
    private Jogador jogador;

    /**
     * Construtor da classe ComandoRecolher.
     * @param jogador Referência do jogador que irá receber o item.
     */
    public ComandoRecolher(Jogador jogador) { 
        this.jogador = jogador; 
    }

    /**
     * Executa a lógica de recolher um item específico do chão/local.
     * @param argumento O nome do item que o jogador tenta apanhar.
     */
    @Override
    public void executar(String argumento) {
        // Tenta remover o item do local atual (se ele existir lá)
        Item item = jogador.getLocalAtual().removerItem(argumento);

        // Se o item foi encontrado e removido com sucesso do local
        if (item != null) {
            // Adiciona o item à lista de inventário do jogador
            jogador.getInventario().add(item);
            System.out.println("Você apanhou: " + item.getNome());
            
            // Verifica se este item contém uma pista imediata ao ser recolhido
            if (item.getPista() != null) {
                // Regista a descoberta no diário do jogador
                jogador.adicionarPista(item.getPista());
            }
        } 
        // Caso o item não exista no local ou o nome tenha sido digitado incorretamente
        else {
            System.out.println("Não vejo nenhum " + argumento + " aqui.");
        }
    }
}