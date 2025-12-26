import java.util.ArrayList; // <--- Importante
import java.util.List;

public class Jogador {
    
    private Local localAtual;
    private List<Item> inventario;
    private List<Pista> diario = new ArrayList<>();
    // ... inventario, diarioPistas ...

    public Jogador(Local localInicial) {
        this.localAtual = localInicial;
        this.inventario = new ArrayList<>();
        this.diario = new ArrayList<>();
    }
    
    public void setLocalAtual(Local novoLocal) {
        this.localAtual = novoLocal;
    }
    
    public Local getLocalAtual(){return this.localAtual;}
    /**
     * Tenta apanhar um item do local atual.
     */
    public void recolher(String nomeItem) {
        // ... validações iniciais ...
        Item item = localAtual.removerItem(nomeItem);

        if (item != null) {
            inventario.add(item);
            System.out.println("Você apanhou: " + item.getNome());

            // --- LÓGICA SOLID PERFEITA ---
            // O jogador não sabe QUE item é, só vê se tem pista anexada
            if (item.getPista() != null) {
                adicionarPista(item.getPista());
            }
        } else {
            System.out.println("Não vejo nenhum " + nomeItem + " aqui.");
        }
    }
    
    // Atualize o método inspecionar para ver itens do inventário também!
    public String inspecionar(String argumento) {
        for(Item i : inventario) {
            if(i.getNome().equalsIgnoreCase(argumento)) {
                
                // Lógica Genérica: Se o item tiver pista, regista-a
                if (i.getPista() != null) {
                    adicionarPista(i.getPista());
                }
                
                return i.getDescricao();
            }
        }
        return "Não tem ou não vê isso aqui.";
    }

    public String falar(String nomeNPC) {
        if (nomeNPC == null || nomeNPC.trim().isEmpty()) {
            return "Falar com quem? Pessoas aqui: " + localAtual.getLocalNPCs();
        }

        // --- CORREÇÃO DE ENCAPSULAMENTO ---
        // Em vez de pedir a lista toda, pedimos só o NPC específico
        NPC npc = localAtual.getNPC(nomeNPC); 

        if (npc != null) {
            // Lógica SOLID (já está correta no teu código)
            if (npc.getPista() != null) {
                adicionarPista(npc.getPista());
            }
            return npc.getNome() + " diz: \"" + npc.getFala() + "\"";
        }
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
     * Devolve o número total de pistas recolhidas.
     * @return 
     */
    public int getNumeroPistas() {
        return diario.size();
    }
    
}