/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gugar
 */
public class ComandoAjuda implements AcaoComando {
    
    @Override
    public void executar(String argumento) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║           COMANDOS DISPONÍVEIS             ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("MOVIMENTO:");
        System.out.println("   ir <local>          - Move-se para outro local");
        System.out.println("                         Sinónimos: andar, mover, caminhar");
        System.out.println();
        System.out.println("OBSERVAÇÃO:");
        System.out.println("   olhar               - Observa o local atual");
        System.out.println("                         Sinónimos: ver, observar");
        System.out.println("   inspecionar <obj>   - Examina um objeto detalhadamente");
        System.out.println("                         Sinónimos: examinar, investigar, analisar");
        System.out.println();
        System.out.println("INTERAÇÃO:");
        System.out.println("   falar <npc>         - Conversa com um personagem");
        System.out.println("                         Sinónimos: conversar, perguntar");
        System.out.println("   recolher <item>     - Recolhe um item do local");
        System.out.println("                         Sinónimos: pegar, apanhar, agarrar");
        System.out.println("   acusar <npc>        - Tenta resolver o caso. CUIDADO: Se errar, perde!");

        System.out.println();
        System.out.println("INFORMAÇÃO:");
        System.out.println("   inventario          - Lista os itens no seu inventário");
        System.out.println("                         Sinónimos: inv, i");
        System.out.println("   pistas              - Lista todas as pistas recolhidas");
        System.out.println();
        System.out.println("SISTEMA:");
        System.out.println("   ajuda               - Mostra esta mensagem");
        System.out.println("                         Sinónimos: help");
        System.out.println("   sair                - Termina o jogo");
        System.out.println("                         Sinónimos: quit, terminar, exit");
        System.out.println();
        System.out.println("════════════════════════════════════════════════════════");
    }
}
