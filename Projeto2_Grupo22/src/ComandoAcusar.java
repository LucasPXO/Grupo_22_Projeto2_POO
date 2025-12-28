/**
 * Classe responsável por processar a acusação final do jogador.
 * Este comando decide se o jogador vence ou perde o jogo com base nas provas recolhidas.
 */
public class ComandoAcusar implements AcaoComando {
    // --- Atributos ---
    private GameLoop gameLoop;
    private Jogador jogador; 

    /**
     * Construtor da classe ComandoAcusar.
     * @param gameLoop Referência ao ciclo principal para permitir encerrar o jogo.
     * @param jogador Referência ao jogador para verificar o progresso da investigação.
     */
    public ComandoAcusar(GameLoop gameLoop, Jogador jogador) {
        this.gameLoop = gameLoop;
        this.jogador = jogador;
    }

    /**
     * Executa a lógica de acusação.
     * @param argumento O nome do suspeito que o jogador deseja acusar.
     */
    @Override
    public void executar(String argumento) {
        // 1. VERIFICAÇÃO DE SEGURANÇA: Impede acusações precipitadas sem provas suficientes
        if (jogador.getNumeroPistas() < 2) {
            System.out.println("Lestrade: \"Detetive, não me faça perder tempo!\"");
            System.out.println("Lestrade: \"Volte quando tiver provas concretas. (Mínimo 2 pistas)\"");
            return; // Interrompe a execução do comando
        }

        // 2. VALIDAÇÃO DE ARGUMENTO: Verifica se foi digitado um nome
        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Acusar quem? (Ex: 'acusar Lucas')");
            return;
        }

        // Efeito dramático no terminal
        System.out.println("\n--- MOMENTO DA VERDADE ---");
        System.out.println("Você aponta o dedo e acusa: " + argumento.toUpperCase() + "!");

        // 3. LÓGICA DE VITÓRIA / DERROTA
        // Verifica se o suspeito acusado é o culpado (neste caso, "Joel")
        if (argumento.equalsIgnoreCase("Joel")) {
            // Cenário de Vitória
            System.out.println("\nLestrade: \"Meu Deus! É verdade! Olhem para a cara dele!\"");
            System.out.println("O Joel tenta fugir, mas é agarrado pelos polícias.");
            System.out.println("Lestrade: \"Brilhante dedução. O caso está encerrado.\"");
            
            System.out.println("\n#################################");
            System.out.println("#      PARABÉNS! GANHOU!        #");
            System.out.println("#################################");
        } else {
            // Cenário de Derrota (acusar qualquer outra pessoa)
            System.out.println("\nLestrade: \"O quê? O " + argumento + "? Impossível!\"");
            System.out.println("Você acusou um inocente. O verdadeiro assassino fugiu.");
            
            System.out.println("\n#################################");
            System.out.println("#      GAME OVER - PERDEU       #");
            System.out.println("#################################");
        }

        // Finaliza o loop do jogo independentemente do resultado
        gameLoop.terminarJogo();
    }
}