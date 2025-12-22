/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gugar
 */
public class ComandoSair implements AcaoComando {
    private GameLoop gameLoop;
    
    public ComandoSair(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }
    
    @Override
    public void executar(String argumento) {
        System.out.println("Tem a certeza que deseja sair? (A sua progressão não será guardada)");
        gameLoop.terminarJogo();
    }
}
