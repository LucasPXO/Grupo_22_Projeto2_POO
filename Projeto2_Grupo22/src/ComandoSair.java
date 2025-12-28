import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Classe responsável por tratar o encerramento voluntário do jogo pelo utilizador.
 * @author gugar
 */
public class ComandoSair implements AcaoComando {
    // --- Atributos ---
    private GameLoop gameLoop;
    
    /**
     * Construtor da classe ComandoSair.
     * @param gameLoop Referência ao ciclo principal do jogo para permitir o encerramento.
     */
    public ComandoSair(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }
    
    /**
     * Executa a lógica de saída com uma confirmação de segurança.
     * @param argumento Este comando normalmente não utiliza argumentos adicionais.
     */
    @Override
    public void executar(String argumento) {
        // Exibe aviso sobre a perda de progresso
        System.out.println("Tem a certeza que deseja sair? (A sua progressão não será guardada)");
        System.out.print("Escreva 'sim' para confirmar ou 'nao' para cancelar: ");
    
        // Captura a resposta diretamente do fluxo de entrada do sistema
        Scanner scanner = new Scanner(System.in);
        String resposta = scanner.nextLine().trim().toLowerCase();
    
        // Processa a decisão do utilizador
        if (resposta.equals("sim")) {
            // Caso confirme a saída
            System.out.println("Jogo terminado. Obrigado por ter jogado!");
            gameLoop.terminarJogo();
        } else if (resposta.equals("nao")) {
            // Caso desista de sair
            System.out.println("Continuando o jogo.");
        } else {
            // Caso o input não seja reconhecido (tratamento de erro simples)
            System.out.println("Resposta inválida. Continuando o jogo.");
        }
    }
}