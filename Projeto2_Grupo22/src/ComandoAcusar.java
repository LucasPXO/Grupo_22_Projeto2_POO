// Em src/ComandoAcusar.java
import java.util.List;

public class ComandoAcusar implements AcaoComando {
    private GameLoop gameLoop;
    private Jogador jogador; 
    
    // Melhoria: Definir o culpado numa constante para facilitar alterações futuras
    private final String CULPADO = "Joel";

    public ComandoAcusar(GameLoop gameLoop, Jogador jogador) {
        this.gameLoop = gameLoop;
        this.jogador = jogador;
    }

    @Override
    public void executar(String argumento) {
        // 1. VERIFICAÇÃO DE ARGUMENTO
        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Acusar quem? (Ex: 'acusar Lucas')");
            return;
        }

        // 2. VERIFICAÇÃO DE EXISTÊNCIA (A funcionalidade que pediste)
        // Vamos percorrer a lista de NPCs do jogo para ver se o nome existe
        boolean npcExiste = false;
        List<NPC> listaNpcs = gameLoop.getTodosNpcs(); // Usa o novo método do GameLoop
        
        for (NPC npc : listaNpcs) {
            if (npc.getNome().equalsIgnoreCase(argumento)) {
                npcExiste = true;
                break;
            }
        }
        
        // Se o NPC não existir, dá um erro amigável e NÃO termina o jogo
        if (!npcExiste) {
            System.out.println("Lestrade: \"Quem é '" + argumento + "'? Não conhecemos ninguém com esse nome em Londres.\"");
            System.out.println("(Dica: Verifique se escreveu o nome corretamente)");
            return; 
        }

        // 3. VERIFICAÇÃO DE PISTAS (Só avança se tiver provas)
        if (jogador.getNumeroPistas() < 2) {
            System.out.println("Lestrade: \"Detetive, não me faça perder tempo!\"");
            System.out.println("Lestrade: \"Volte quando tiver provas concretas. (Mínimo 2 pistas)\"");
            return;
        }

        // 4. DRAMA E RESOLUÇÃO
        System.out.println("\n--- MOMENTO DA VERDADE ---");
        System.out.println("Você aponta o dedo e acusa: " + argumento.toUpperCase() + "!");

        if (argumento.equalsIgnoreCase(CULPADO)) {
            // Vitória
            System.out.println("\nLestrade: \"Meu Deus! É verdade! Olhem para a cara dele!\"");
            System.out.println("O " + CULPADO + " tenta fugir, mas é agarrado pelos polícias.");
            System.out.println("Lestrade: \"Brilhante dedução, Holmes. O caso está encerrado.\"");
            System.out.println("\n#################################");
            System.out.println("#      PARABÉNS! GANHOU!        #");
            System.out.println("#################################");
        } else {
            // Derrota
            System.out.println("\nLestrade: \"O quê? O " + argumento + "? Isso é ridículo!\"");
            System.out.println("O " + argumento + " tem um álibi confirmado.");
            System.out.println("Enquanto você perdia tempo com acusações falsas, o verdadeiro assassino fugiu de Londres.");
            System.out.println("\n#################################");
            System.out.println("#      GAME OVER - PERDEU       #");
            System.out.println("#################################");
        }

        gameLoop.terminarJogo();
    }
}