import java.util.ArrayList; // <--- Importante
import java.util.List;

public class Jogador {
    
    private Local localAtual;
    private List<Item> inventario;
    // ... inventario, diarioPistas ...

    public Jogador(Local localInicial) {
        this.localAtual = localInicial;
        this.inventario = new ArrayList<>();
    }

    /**
     * Tenta mover o jogador para uma nova direção/local.
     * @param direcao O argumento do comando "ir" (ex: "norte", "mansao")
     */
    public void mover(String direcao) {
        // Se a direção for nula ou vazia (apenas espaços)
        if (direcao == null || direcao.trim().isEmpty()) {
            System.out.println("Ir para onde?");
            // AQUI ESTÁ A MUDANÇA: Mostra as opções
            System.out.println("Saídas possíveis: " + localAtual.getSaidasDisponiveis());
            return;
        }

        // 1. Pede ao local atual se existe uma saída com esse nome
        Local proximoLocal = localAtual.getSaida(direcao);

        // 2. Verifica a resposta
        if (proximoLocal == null) {
            System.out.println("Não pode ir por aí.");
            // Opcional: Mostrar as saídas também quando o jogador erra a direção
            System.out.println("Tente: " + localAtual.getSaidasDisponiveis());
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
    
    /**
     * Tenta apanhar um item do local atual.
     */
    public void recolher(String nomeItem) {
        if (nomeItem == null) {
            System.out.println("Recolher o quê?");
            return;
        }

        // 1. Tenta tirar o item do chão (Local)
        Item item = localAtual.removerItem(nomeItem);

        // 2. Verifica se conseguiu
        if (item != null) {
            inventario.add(item); // 3. Guarda no inventário
            System.out.println("Você apanhou: " + item.getNome());
        } else {
            System.out.println("Não vejo nenhum " + nomeItem + " aqui.");
        }
    }
    
    // Atualize o método inspecionar para ver itens do inventário também!
    public String inspecionar(String argumento) {
        // Verifica no inventário
        for(Item i : inventario) {
            if(i.getNome().equalsIgnoreCase(argumento)) {
                return i.getDescricao();
            }
        }
        return "Não tem ou não vê isso aqui.";
    }

    public String falar(String nomeNPC) {
        // 1. Verificar se o jogador escreveu um nome
        if (nomeNPC == null || nomeNPC.trim().isEmpty()) {
            return "Falar com quem?";
        }

        // 2. Obter a lista de NPCs no local atual
        List<NPC> npcsNoLocal = localAtual.getNpcsNoLocal();

        // 3. Procurar o NPC pelo nome
        for (NPC npc : npcsNoLocal) {
            if (npc.getNome().equalsIgnoreCase(nomeNPC)) {
                // 4. Se encontrou, retorna a fala dele
                return npc.getNome() + " diz: \"" + npc.getFala() + "\"";
            }
        }

        // 5. Se não encontrou ninguém com esse nome
        return "Não vês '" + nomeNPC + "' aqui.";
    }

    /**
     * Mostra o que o jogador carrega.
     */
    public void listarInventario() {
        if (inventario.isEmpty()) {
            System.out.println("O seu inventário está vazio.");
        } else {
            System.out.println("Inventário:");
            for (Item i : inventario) {
                System.out.println("- " + i.getNome());
            }
        }
    }

    public void listarPistas() {
        // Implementar a lógica das pistas
        System.out.println("Ainda não descobriu nenhuma pista.");
    }
    
}