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
    
    public ComandoIr(Jogador jogador) {
        this.jogador = jogador;
    }
    
    @Override
    public void executar(String argumento) {
        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Ir para onde? Especifique um local.");
            return;
        }
        jogador.mover(argumento);
    }
}
