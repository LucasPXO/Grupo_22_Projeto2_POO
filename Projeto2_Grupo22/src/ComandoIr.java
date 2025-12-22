/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gugar
 */
public class ComandoIr implements AcaoComando {
    private Jogador jogador;
    public ComandoIr(Jogador jogador) { this.jogador = jogador; }

    @Override
    public void executar(String argumento) {
        // Sem ifs, sem complicações. O Jogador que se desenrasque.
        jogador.mover(argumento); 
    }
}
