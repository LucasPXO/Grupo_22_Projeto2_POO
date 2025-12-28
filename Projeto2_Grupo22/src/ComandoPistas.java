import java.util.List;

/**
 * Classe que implementa o comando para listar todas as pistas recolhidas pelo jogador.
 * Funciona como o diário de bordo do detetive.
 */
public class ComandoPistas implements AcaoComando {
    // --- Atributos ---
    private Jogador jogador;
    
    /**
     * Construtor da classe ComandoPistas.
     * @param jogador Referência do jogador para aceder ao seu diário de pistas.
     */
    public ComandoPistas(Jogador jogador) {
        this.jogador = jogador;
    }
    
    /**
     * Executa a lógica de listagem das pistas.
     * @param argumento Este comando ignora argumentos adicionais.
     */
    @Override
    public void executar(String argumento) {
        // Obtém a lista de pistas registadas no diário do jogador
        List<Pista> diario = jogador.getDiario();
        
        // Verifica se o jogador já encontrou algum dado relevante
        if (diario.isEmpty()) {
            System.out.println("Ainda não descobriu nenhuma pista.");
        } else {
            // Exibe o cabeçalho e percorre a lista de pistas encontradas
            System.out.println("=== DIÁRIO DE PISTAS ===");
            for (Pista p : diario) {
                // Mostra o título da pista seguido da informação detalhada
                System.out.println("* " + p.getNome() + ": " + p.getConteudo());
            }
        }
    }
}