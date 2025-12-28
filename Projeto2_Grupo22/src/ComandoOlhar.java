// Em src/ComandoOlhar.java

/**
 * Classe responsável por descrever o ambiente atual ao jogador.
 * Permite que o utilizador "redescubra" o local, listando saídas, itens e NPCs presentes.
 */
public class ComandoOlhar implements AcaoComando {
    // --- Atributos ---
    private Jogador jogador;

    /**
     * Construtor da classe ComandoOlhar.
     * @param jogador Referência do jogador para saber a sua localização atual.
     */
    public ComandoOlhar(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     * Executa a ação de observação.
     * @param argumento Este comando normalmente ignora argumentos (olha para o local como um todo).
     */
    @Override
    public void executar(String argumento) {        
        // Solicita ao local atual a sua descrição completa e imprime-a no terminal.
        System.out.println(jogador.getLocalAtual().mostrarInfo());
    }
}