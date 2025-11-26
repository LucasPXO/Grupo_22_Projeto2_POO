import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Local {
    private String nome;
    private String descricao;
    private Map<String, Local> saidas; // Chave: "norte", "delegacia", etc. Valor: O objeto Local.
    private List<NPC> npcsNoLocal;

    public Local(String descricao, String nome) {
        this.nome=nome;
        this.descricao = descricao;
        this.saidas = new HashMap<>(); // Inicializa o mapa de saídas
        this.npcsNoLocal = new ArrayList<>();
    }
    
     // ADICIONAR este método
    public void adicionarNPC(NPC npc) {
        this.npcsNoLocal.add(npc);
    }
    
    // ADICIONAR este método
    public void removerNPC(NPC npc) {
        this.npcsNoLocal.remove(npc);
    }

    /**
     * Adiciona uma saída deste local para outro.
     * @param direcao O nome do comando para ir (ex: "norte", "baker street")
     * @param vizinho O Local para onde essa direção leva
     */
    public void adicionarSaida(String direcao, Local vizinho) {
        // Normaliza a chave para minúsculas para ser fácil de comparar
        this.saidas.put(direcao.toLowerCase(), vizinho);
    }

    /**
     * Retorna o local correspondente à direção, ou null se não houver saída.
     */
    public Local getSaida(String direcao) {
        return this.saidas.get(direcao.toLowerCase());
    }

    /**
     * Retorna a descrição do local.
     */
     // MODIFICAR: não precisa mais receber lista
    public String mostrarInfo() {
        StringBuilder info = new StringBuilder(descricao);
        
        if (!npcsNoLocal.isEmpty()) {
            info.append("\n\nPersonagens presentes:");
            for (NPC npc : npcsNoLocal) {
                info.append("\n- ").append(npc.getNome());
            }
        }else{
            info.append("\n Nenhum personagem presente.");
        }
        
        return info.toString();
    }    
        public String getDescricao(){return descricao;}
        public String getNome() {return nome;}
        public List<NPC> getNpcsNoLocal() { return npcsNoLocal; }
    }
    //public NPC getNCS(){}
    
    // ... outras
    // ... getters para NPCs, Itens, etc.
