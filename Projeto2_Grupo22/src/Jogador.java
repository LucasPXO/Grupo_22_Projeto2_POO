import java.util.ArrayList; // <--- Importante
import java.util.List;

public class Jogador {
    
    protected Local localAtual;
    private List<Item> inventario;
    private List<Pista> diario = new ArrayList<>();
    // ... inventario, diarioPistas ...

    public Jogador(Local localInicial) {
        this.localAtual = localInicial;
        this.inventario = new ArrayList<>();
        this.diario = new ArrayList<>();
    }
    
    public Local getLocalAtual(){return this.localAtual;}
    /**
     * Tenta apanhar um item do local atual.
     */
    public void recolher(String nomeItem) {
        if (nomeItem == null || nomeItem.trim().isEmpty()) {
            System.out.println("Recolher o quê?");
            System.out.println(localAtual.getLocalItens()); 
            return;
        }

        // 1. Tenta tirar o item do chão
        Item item = localAtual.removerItem(nomeItem);

        // 2. Verifica se conseguiu apanhar
        if (item != null) {
            inventario.add(item);
            System.out.println("Você apanhou: " + item.getNome());

            // --- NOVO: LÓGICA PARA DETETAR PISTAS AO APANHAR ---
            if (item.getNome().equalsIgnoreCase("Faca")) {
                adicionarPista(new Pista("Arma do Crime", "Uma faca ensanguentada encontrada no beco."));
            }
            // ---------------------------------------------------

        } else {
            System.out.println("Não vejo nenhum " + nomeItem + " aqui.");
        }
    }
    
    // Atualize o método inspecionar para ver itens do inventário também!
    public String inspecionar(String argumento) {
        for(Item i : inventario) {
            if(i.getNome().equalsIgnoreCase(argumento)) {
                
                // LÓGICA DE VITÓRIA AQUI:
                if (i.getNome().equalsIgnoreCase("Diário Perdido")) {
                    adicionarPista(new Pista("Confissão do Assassino", "O diário detalha como o crime foi cometido."));
                }
                
                return i.getDescricao();
            }
        }
        return "Não tem ou não vê isso aqui.";
    }

    public String falar(String nomeNPC) {
        // 1. Verificar se o jogador escreveu um nome
        if (nomeNPC == null || nomeNPC.trim().isEmpty()) {
            return "Falar com quem?"
                    + localAtual.getLocalNPCs() ;
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
        if (diario.isEmpty()) {
            System.out.println("Ainda não descobriu nenhuma pista.");
        } else {
            System.out.println("=== DIÁRIO DE PISTAS ===");
            for (Pista p : diario) {
                // Usa getNome() (da Entidade) e getConteudo() (da Pista)
                System.out.println("* " + p.getNome() + ": " + p.getConteudo());
            }
        }
    }
    
    // Método auxiliar para adicionar pistas sem repetir
    public void adicionarPista(Pista pista) {
        for (Pista p : diario) {
            if (p.getNome().equalsIgnoreCase(pista.getNome())) {
                return; // Já temos esta pista, não faz nada
            }
        }
        diario.add(pista);
        System.out.println("\n(i) NOVA PISTA REGISTADA: " + pista.getNome());
    }
    
    /**
     * Verifica se o jogador reuniu as condições para ganhar o jogo.
     * Retorna true se tiver a pista final ou pistas suficientes.
     */
    public boolean temPistaFinal() {
        // Lógica Nova: Só ganha se tiver pelo menos 3 pistas no diário
        // (Ou se tiver uma pista muito específica que só se ganha no fim)
        
        int pistasNecessarias = 1; // Defina quantas quiser
        
        if (diario.size() >= pistasNecessarias) {
            return true;
        }
        
        return false;
    }
    
}