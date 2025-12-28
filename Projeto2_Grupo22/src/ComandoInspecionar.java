import java.util.List;

/**
 * Classe responsável pela ação de inspecionar itens que o jogador já possui.
 * Permite obter descrições detalhadas e descobrir pistas ocultas nos objetos.
 */
public class ComandoInspecionar implements AcaoComando {
    // --- Atributos ---
    private Jogador jogador;
    
    /**
     * Construtor da classe ComandoInspecionar.
     * @param jogador Referência do jogador para aceder ao inventário.
     */
    public ComandoInspecionar(Jogador jogador) {
        this.jogador = jogador;
    }
    
    /**
     * Executa a lógica de inspeção de um item específico.
     * @param argumento O nome do item que se pretende examinar.
     */
    @Override
    public void executar(String argumento) {
        // Obtém a lista de itens que o jogador está a carregar
        List<Item> inventario = jogador.getInventario();
        
        // Validação: Verifica se o utilizador especificou o que quer inspecionar
        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Inspecionar o quê? Especifique um objeto.\n");
            return;
        }

        // Percorre o inventário à procura do item correspondente ao argumento
        for(Item i : inventario) {
            // Verifica se o nome do item coincide (ignorando maiúsculas/minúsculas)
            if(i.getNome().equalsIgnoreCase(argumento)) {
                
                // Se o item tiver uma pista associada, regista-a no diário do jogador
                if (i.getPista() != null) {
                    jogador.adicionarPista(i.getPista());
                    // Exibe a descrição detalhada do item
                    System.out.print(i.getDescricao());
                }
                
            }
            // Caso o item atual do loop não seja o pretendido
            else {
                System.out.print("Não tem ou não vê isso aqui.\n");
            }
        }
    }
}