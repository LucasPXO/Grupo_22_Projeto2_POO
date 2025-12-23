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
        Local localAtual=jogador.getLocalAtual();
        // Se a direção for nula ou vazia (apenas espaços)
        if (argumento == null || argumento.trim().isEmpty()) {
            System.out.println("Ir para onde?");
            // AQUI ESTÁ A MUDANÇA: Mostra as opções
            System.out.println("Saídas possíveis: " + localAtual.getSaidasDisponiveis());
            return;
        }

        // 1. Pede ao local atual se existe uma saída com esse nome
        Local proximoLocal = localAtual.getSaida(argumento);

        // 2. Verifica a resposta
        if (proximoLocal == null) {
            System.out.println("Não pode ir por aí.");
            // Opcional: Mostrar as saídas também quando o jogador erra a direção
            System.out.println("Tente: " + localAtual.getSaidasDisponiveis());
        } else {
            // 3. Atualiza o local do jogador
            jogador.localAtual = proximoLocal;
            
            // 4. Mostra a descrição do novo local (importante!)
            System.out.println("\n=== " + proximoLocal.getNome() + " ===");
            System.out.println(proximoLocal.mostrarInfo()); 

        }
    }
}
