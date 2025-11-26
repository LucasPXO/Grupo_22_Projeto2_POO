public class Jogador {
    
    private Local localAtual;
    // ... inventario, diarioPistas ...

    public Jogador(Local localInicial) {
        this.localAtual = localInicial;
    }

    /**
     * Tenta mover o jogador para uma nova direção/local.
     * @param direcao O argumento do comando "ir" (ex: "norte", "mansao")
     */
    public void mover(String direcao) {
        if (direcao == null || direcao.isEmpty()) {
            System.out.println("Ir para onde?");
            return;
        }

        // 1. Pede ao local atual se existe uma saída com esse nome
        Local proximoLocal = localAtual.getSaida(direcao);

        // 2. Verifica a resposta
        if (proximoLocal == null) {
            System.out.println("Não pode ir por aí.");
        } else {
            // 3. Atualiza o local do jogador
            this.localAtual = proximoLocal;
            
            // 4. Mostra a descrição do novo local (importante!)
            System.out.println("\n=== " + proximoLocal.getNome() + " ===");
            System.out.println(proximoLocal.mostrarInfo()); 

        }
    }

    /**
     * Retorna a descrição do local atual.
     */
    
    public String olhar() {
        return localAtual.mostrarInfo();
    }
    public String inspecionar(String argumento) {
        //  Implementar a lógica de inspeção
        return "Não vê nada de especial no(a) " + argumento + ".";
    }

    public String falar(String argumento) {
        //  Implementar a lógica de diálogo
        return "Tenta falar com " + argumento + ", mas não obtém resposta.";
    }

    public void listarInventario() {
        // Implementar a lógica do inventário
        System.out.println("O seu inventário está vazio.");
    }

    public void listarPistas() {
        // Implementar a lógica das pistas
        System.out.println("Ainda não descobriu nenhuma pista.");
    }
    
}