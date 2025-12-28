/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Classe responsável por exibir o manual de comandos ao utilizador.
 * Implementa a interface AcaoComando para ser executada pelo sistema de vocabulário.
 * * @author gugar
 */
public class ComandoAjuda implements AcaoComando {
    
    /**
     * Executa a exibição da interface de ajuda no console.
     * @param argumento Este comando não utiliza argumentos adicionais.
     */
    @Override
    public void executar(String argumento) {
        // --- Cabeçalho Decorativo ---
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║           COMANDOS DISPONÍVEIS             ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();
        
        // --- Seção de Navegação ---
        System.out.println("MOVIMENTO:");
        System.out.println("   ir <local>          - Move-se para outro local");
        System.out.println();
        
        // --- Seção de Exploração ---
        System.out.println("OBSERVAÇÃO:");
        System.out.println("   olhar               - Observa o local atual");
        System.out.println("   inspecionar <obj>   - Examina um objeto detalhadamente");
        System.out.println();
        
        // --- Seção de Interação com Personagens e Itens ---
        System.out.println("INTERAÇÃO:");
        System.out.println("   falar <npc>         - Conversa com um personagem");
        System.out.println("   recolher <item>     - Recolhe um item do local");
        System.out.println("   acusar <npc>        - Tenta resolver o caso. CUIDADO: Se errar, perde!");

        System.out.println();
        
        // --- Seção de Estado do Jogador ---
        System.out.println("INFORMAÇÃO:");
        System.out.println("   inventario          - Lista os itens no seu inventário");
        System.out.println("   pistas              - Lista todas as pistas recolhidas");
        System.out.println();
        
        // --- Seção de Controle de Sessão ---
        System.out.println("SISTEMA:");
        System.out.println("   ajuda               - Mostra esta mensagem");
        System.out.println("   sair                - Termina o jogo");
        System.out.println();
        
        // --- Rodapé Visual ---
        System.out.println("════════════════════════════════════════════════════════");
    }
}