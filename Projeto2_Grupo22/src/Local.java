import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Representa um cenário ou sala no jogo. 
 * Contém ligações para outros locais, uma lista de itens e personagens presentes.
 */
public class Local extends Entidade {
    
    private String descricao;
    private Map<String, Local> saidas;        // Mapeia uma direção (ex: "norte") a outro objeto Local
    private List<Item> itens;                // Itens atualmente no chão deste local
    private List<NPC> npcsNoLocal;           // Personagens presentes neste local

    /**
     * Construtor da classe Local.
     * @param descricao Texto que descreve o ambiente.
     * @param nome Nome identificador do local (ex: "Cozinha").
     */
    public Local(String descricao, String nome) {
        super(nome);
        this.descricao = descricao;
        this.saidas = new HashMap<>();
        this.itens = new ArrayList<>();       // Inicializa a lista para evitar NullPointerException
        this.npcsNoLocal = new ArrayList<>(); // Inicializa a lista de NPCs
    }

    // --- GESTÃO DE SAÍDAS (Navegação) ---

    /**
     * Define uma ligação entre este local e outro.
     * @param direcao A palavra-chave para mover (ex: "quarto").
     * @param vizinho O objeto Local para onde o jogador irá.
     */
    public void adicionarSaida(String direcao, Local vizinho) {
        this.saidas.put(direcao.toLowerCase(), vizinho);
    }

    /**
     * Recupera o destino associado a uma direção.
     */
    public Local getSaida(String direcao) {
        return this.saidas.get(direcao.toLowerCase());
    }

    /**
     * Imprime no console todas as saídas configuradas para este local.
     */
    public void getSaidasDisponiveis() {
        Set<String> chaves = saidas.keySet();
        String formatada = String.join(", ", chaves);
        System.out.println("Saídas disponíveis: " + formatada);
    }

    // --- GESTÃO DE ITENS ---

    /**
     * Coloca um item no chão do local.
     */
    public void adicionarItem(Item item) {
        itens.add(item);
    }

    /**
     * Procura um item pelo nome e remove-o do local (usado quando o jogador recolhe algo).
     * @return O objeto Item se encontrado, ou null caso contrário.
     */
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
    
    /**
     * Procura um NPC presente no local pelo nome.
     */
    public NPC getNPC(String nomeProcurado) {
        for (NPC npc : npcsNoLocal) {
            if (npc.getNome().equalsIgnoreCase(nomeProcurado)) {
                return npc;
            }
        }
        return null;
    }

    // --- INFORMAÇÃO E EXIBIÇÃO ---

    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Agrega toda a informação visual do local: descrição, itens e NPCs.
     * @return Uma String formatada pronta para ser exibida ao jogador.
     */
    public String mostrarInfo() {
        StringBuilder info = new StringBuilder(descricao);
        
        // Adiciona a lista de itens e NPCs à descrição base
        info.append(getLocalItens());
        info.append(getLocalNPCs());
                
        return info.toString();
    }
   
    /**
     * Formata a lista de itens visíveis no chão.
     */
    public String getLocalItens(){
        StringBuilder info = new StringBuilder();
        if (!itens.isEmpty()) {
            info.append("\n\nVocê vê os seguintes itens:");
            for (Item i : itens) {
                info.append("\n- ").append(i.getNome());
            }
        }
        return info.toString();
    }
    
    /**
     * Formata a lista de personagens presentes.
     */
    public String getLocalNPCs(){
        StringBuilder info = new StringBuilder();
        if (!npcsNoLocal.isEmpty()) {
            info.append("\n\nPersonagens presentes:");
            for (NPC npc : npcsNoLocal) {
                info.append("\n- ").append(npc.getNome());
            }
        } else {
            info.append("\nNinguém por aqui.");
        }
        return info.toString();
    }
}