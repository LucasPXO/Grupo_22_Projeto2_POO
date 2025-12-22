/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gugar
 */
public class ComandoFalar implements AcaoComando {
    private Jogador jogador;
    
    public ComandoFalar(Jogador jogador) {
        this.jogador = jogador;
    }
    
    @Override
    public void executar(String argumento) {
        System.out.println(jogador.falar(argumento));
    }
}
