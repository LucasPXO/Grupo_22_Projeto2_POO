import java.util.Scanner;
import java.util.List;

/**
 * Classe responsável por gerir a ação de falar com personagens (NPCs) no jogo.
 */
public class ComandoFalar implements AcaoComando {
    private Jogador jogador;
    private Scanner scanner;
    
    /**
     * Construtor da classe ComandoFalar.
     */
    public ComandoFalar(Jogador jogador, Scanner scanner) {
        this.jogador = jogador;
        this.scanner = scanner;
    }
    
    /**
     * Executa a lógica do comando falar.
     * @param argumento O nome do NPC com quem se pretende falar.
     */
    @Override
    public void executar(String argumento) {
        // Validação: Verifica se o utilizador escreveu um nome após o comando
        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Falar com quem? Pessoas aqui: " + jogador.getLocalAtual().getLocalNPCs());
            return;
        }
        
        // Tenta encontrar o NPC no local onde o jogador se encontra
        NPC npc = jogador.getLocalAtual().getNPC(argumento);
        
        // Verifica se o NPC realmente existe no local atual
        if (npc == null) {
            System.out.println("Não vê '" + argumento + "' aqui.");
            return;
        }
        
        // Decide se inicia uma árvore de diálogo complexa ou uma fala simples
        if (npc.temDialogoInterativo()) {
            conversarComNPC(npc); 
        } else {
            // Caso simples: o NPC diz uma frase e pode entregar uma pista
            if (npc.getPista() != null) {
                jogador.adicionarPista(npc.getPista());
            }
            System.out.println(npc.getNome() + " diz: \"" + npc.getFala() + "\"");
        }
    }
    
    /**
     * Gere o sistema de conversação por menus (diálogo interativo).
     */
    private void conversarComNPC(NPC npc) {
        NoDialogo dialogo = npc.getDialogo();
        boolean emConversa = true;
        boolean primeiraVez = true;
        
        // Cabeçalho visual da conversa
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Conversando com: " + npc.getNome());
        System.out.println("=".repeat(50));
        
        // Loop principal da conversa
        while (emConversa) {
            // Mostra a saudação inicial do NPC apenas no começo
            if (primeiraVez) {
                System.out.println("\n" + npc.getNome() + " diz: \"" + dialogo.getFalaInicial() + "\"");
                primeiraVez = false;
            }
            
            // Listagem das opções de perguntas disponíveis
            System.out.println("\n--- O que perguntar? ---");
            List<OpcaoDialogo> opcoes = dialogo.getOpcoes();
            
            for (int i = 0; i < opcoes.size(); i++) {
                System.out.println((i + 1) + ") " + opcoes.get(i).getTexto());
            }
            System.out.println("0) [Encerrar conversa]");
            
            // Leitura e validação da escolha do utilizador
            System.out.print("\nEscolha (0-" + opcoes.size() + "): ");
            String input = scanner.nextLine().trim();
            
            int escolha;
            try {
                escolha = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada inválida. Digite um número.");
                continue;
            }
            
            // Opção 0: Termina o loop de conversa
            if (escolha == 0) {
                System.out.println("\n" + npc.getNome() + " diz: \"" + dialogo.getMensagemDespedida() + "\"");
                System.out.println("=".repeat(50));
                emConversa = false;
                continue;
            }
            
            // Validação de intervalo numérico
            if (escolha < 1 || escolha > opcoes.size()) {
                System.out.println("\nOpção inválida. Escolha entre 0 e " + opcoes.size() + ".");
                continue;
            }
            
            // Processamento da opção escolhida
            OpcaoDialogo opcaoEscolhida = opcoes.get(escolha - 1);
            
            System.out.println("\n" + "-".repeat(50));
            System.out.println("Você: \"" + opcaoEscolhida.getTexto() + "\"");
            System.out.println("\n" + npc.getNome() + " responde: \"" + opcaoEscolhida.getResposta() + "\"");
            
            // Se a resposta contiver uma pista, adiciona-a ao diário do jogador
            if (opcaoEscolhida.getPista() != null) {
                jogador.adicionarPista(opcaoEscolhida.getPista());
            }
            
            System.out.println("-".repeat(50));
        }
    }
}