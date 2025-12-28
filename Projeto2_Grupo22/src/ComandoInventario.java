import java.util.List;

/**
 * Classe que implementa o comando para listar os itens que o jogador possui.
 */
public class ComandoInventario implements AcaoComando {
    // --- Atributos ---
    private Jogador jogador;
    
    /**
     * Construtor da classe ComandoInventario.
     * @param jogador Referência do jogador para aceder ao seu inventário.
     */
    public ComandoInventario(Jogador jogador) {
        this.jogador = jogador;
    }
    
    /**
     * Executa a lógica de exibição do inventário.
     * @param argumento Este comando normalmente não utiliza argumentos adicionais.
     */
    @Override
    public void executar(String argumento) {
        // Obtém a lista de itens atualmente em posse do jogador
        List<Item> inventario = jogador.getInventario();
        
        // Verifica se o jogador possui algum item
        if (inventario.isEmpty()) {
            System.out.println("O seu inventário está vazio.");
        } else {
            // Lista todos os itens presentes no inventário
            System.out.println("Inventário:");
            for (Item i : inventario) {
                System.out.println("- " + i.getNome());
            }
        }
    }
}