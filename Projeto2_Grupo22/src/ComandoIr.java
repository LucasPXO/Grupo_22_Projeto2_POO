// Em src/ComandoIr.java

public class ComandoIr implements AcaoComando {
    private Jogador jogador;

    public ComandoIr(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public void executar(String direcao) {
        if (direcao == null || direcao.trim().isEmpty()) {
            System.out.println("Ir para onde?");
            return;
        }

        // 1. Pedir o local atual ao jogador
        Local atual = jogador.getLocalAtual();
        
        // 2. Verificar se existe saída
        Local destino = atual.getSaida(direcao);

        if (destino != null) {
            // 3. Atualizar o estado do jogador
            jogador.setLocalAtual(destino);
            
            // 4. Feedback ao utilizador (Interface)
            System.out.println("Dirigiu-se para: " + direcao);
            System.out.println(jogador.getLocalAtual().mostrarInfo());
        } else {
            System.out.println("Não pode ir para '" + direcao + "'.");
        }
    }
}