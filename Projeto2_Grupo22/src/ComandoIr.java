// Em src/ComandoIr.java

/**
 * Classe responsável pela movimentação do jogador entre os diferentes locais do mapa.
 */
public class ComandoIr implements AcaoComando {
    // --- Atributos ---
    private Jogador jogador;

    /**
     * Construtor da classe ComandoIr.
     * @param jogador Referência do jogador que irá mudar de posição.
     */
    public ComandoIr(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     * Executa a lógica de deslocamento para um destino específico.
     * @param direcao O nome da saída ou local de destino pretendido.
     */
    @Override
    public void executar(String direcao) {
        // Validação: Verifica se o utilizador indicou para onde quer ir
        if (direcao == null || direcao.trim().isEmpty()) {
            System.out.println("Ir para onde?");
            return;
        }

        // 1. Obtém o local onde o jogador se encontra atualmente
        Local atual = jogador.getLocalAtual();
        
        // 2. Tenta recuperar o objeto Local correspondente à direção/saída indicada
        Local destino = atual.getSaida(direcao);

        // Se o destino existir e for uma saída válida do local atual
        if (destino != null) {
            // 3. Atualiza a posição do jogador para o novo local
            jogador.setLocalAtual(destino);
            
            // 4. Fornece feedback visual e exibe a descrição do novo ambiente
            System.out.println("Dirigiu-se para: " + direcao);
            System.out.println(jogador.getLocalAtual().mostrarInfo());
        } 
        // Caso não exista conexão entre o local atual e o destino indicado
        else {
            System.out.println("Não pode ir para '" + direcao + "'.");
        }
    }
}