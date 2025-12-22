/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gugar
 */
public class ComandoInspecionar implements AcaoComando {
    private Jogador jogador;
    
    public ComandoInspecionar(Jogador jogador) {
        this.jogador = jogador;
    }
    
    @Override
    public void executar(String argumento) {
        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Inspecionar o quê? Especifique um objeto.");
            return;
        }
        System.out.println(jogador.inspecionar(argumento));
    }
}
