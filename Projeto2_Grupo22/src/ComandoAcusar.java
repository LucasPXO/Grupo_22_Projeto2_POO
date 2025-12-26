public class ComandoAcusar implements AcaoComando {
    private GameLoop gameLoop;
    private Jogador jogador; // <--- Precisamos de acesso ao jogador

    // Atualiza o construtor para receber o Jogador também
    public ComandoAcusar(GameLoop gameLoop, Jogador jogador) {
        this.gameLoop = gameLoop;
        this.jogador = jogador;
    }

    @Override
    public void executar(String argumento) {
        // 1. VERIFICAÇÃO DE SEGURANÇA (O que pediste)
        if (jogador.getNumeroPistas() < 2) {
            System.out.println("Lestrade: \"Detetive, não me faça perder tempo!\"");
            System.out.println("Lestrade: \"Volte quando tiver provas concretas. (Mínimo 2 pistas)\"");
            return; // Sai do comando sem deixar acusar
        }

        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Acusar quem? (Ex: 'acusar Lucas')");
            return;
        }

        System.out.println("\n--- MOMENTO DA VERDADE ---");
        System.out.println("Você aponta o dedo e acusa: " + argumento.toUpperCase() + "!");

        // LÓGICA DE VITÓRIA / DERROTA
        if (argumento.equalsIgnoreCase("Lucas")) {
            System.out.println("\nLestrade: \"Meu Deus! É verdade! Olhem para a cara dele!\"");
            System.out.println("O Lucas tenta fugir, mas é agarrado pelos polícias.");
            System.out.println("Lestrade: \"Brilhante dedução. O caso está encerrado.\"");
            
            System.out.println("\n#################################");
            System.out.println("#      PARABÉNS! GANHOU!        #");
            System.out.println("#################################");
        } else {
            System.out.println("\nLestrade: \"O quê? O " + argumento + "? Impossível!\"");
            System.out.println("Você acusou um inocente. O verdadeiro assassino fugiu.");
            
            System.out.println("\n#################################");
            System.out.println("#      GAME OVER - PERDEU       #");
            System.out.println("#################################");
        }

        gameLoop.terminarJogo();
    }
}