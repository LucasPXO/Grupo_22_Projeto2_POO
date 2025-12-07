import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Local {
    
    private String nome;
    private String descricao;
    private Map<String, Local> saidas; 
    private List<Item> itens;        // Lista de Itens no chão
    private List<NPC> npcsNoLocal;   // Lista de NPCs no local

    // Construtor unificado (aceita descrição e nome)
    public Local(String descricao, String nome) {
        this.nome = nome;
        this.descricao = descricao;
        this.saidas = new HashMap<>();
        this.itens = new ArrayList<>();       // Inicializa lista de itens
        this.npcsNoLocal = new ArrayList<>(); // Inicializa lista de NPCs
    }

    // --- GESTÃO DE SAÍDAS ---

    public void adicionarSaida(String direcao, Local vizinho) {
        this.saidas.put(direcao.toLowerCase(), vizinho);
    }

    public Local getSaida(String direcao) {
        return this.saidas.get(direcao.toLowerCase());
    }

    public String getSaidasDisponiveis() {
        return saidas.keySet().toString();
    }

    // --- GESTÃO DE ITENS ---

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public Item removerItem(String nomeItem) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(nomeItem)) {
                itens.remove(i);
                return i;
            }
        }
        return null;
    }

    // --- GESTÃO DE NPCS ---

    public void adicionarNPC(NPC npc) {
        this.npcsNoLocal.add(npc);
    }
    
    public void removerNPC(NPC npc) {
        this.npcsNoLocal.remove(npc);
    }
    
    public List<NPC> getNpcsNoLocal() {
        return npcsNoLocal;
    }

    // --- INFORMAÇÃO E GETTERS ---

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public List<Item> getItens(Local local){
        return local.itens;
    }
    /**
     * Método principal para mostrar o que há no local (Descrição + Itens + NPCs)
     */
    public String mostrarInfo() {
        StringBuilder info = new StringBuilder(descricao);
        
        // 1. Listar Itens
        if (!itens.isEmpty()) {
            info.append("\n\nVocê vê os seguintes itens:");
            for (Item i : itens) {
                info.append("\n- ").append(i.getNome());
            }
        }
        
        // 2. Listar NPCs
        if (!npcsNoLocal.isEmpty()) {
            info.append("\n\nPersonagens presentes:");
            for (NPC npc : npcsNoLocal) {
                info.append("\n- ").append(npc.getNome());
            }
        } else {
            // Opcional: info.append("\nNinguém por aqui.");
        }
        
        return info.toString();
    }
}