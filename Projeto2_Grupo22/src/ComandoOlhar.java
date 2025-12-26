// Em src/ComandoOlhar.java

public class ComandoOlhar implements AcaoComando {
    private Jogador jogador;

    public ComandoOlhar(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public void executar(String argumento) {
        // Respeita o SRP: O comando pede a info ao jogador e mostra-a.
        System.out.println(jogador.getLocalAtual().mostrarInfo());
    }
}