/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gugar
 */
public class ComandoRecolher implements AcaoComando {
    private Jogador jogador;
    public ComandoRecolher(Jogador jogador) { this.jogador = jogador; }

    @Override
    public void executar(String argumento) {
        jogador.recolher(argumento);
    }
}
