import java.util.ArrayList; // <--- Importante
import java.util.HashMap;
import java.util.List;      // <--- Importante
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Local {
    private String nome;
    private String descricao;
    private Map<String, Local> saidas; // Chave: "norte", "delegacia", etc. Valor: O objeto Local.
    private List<Item> itens;
    
    public Local(String descricao) {
        this.descricao = descricao;
        this.saidas = new HashMap<>(); // Inicializa o mapa de saídas
        this.itens = new ArrayList<>();
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
     * Coloca um item neste local.
     */
    public void adicionarItem(Item item) {
        itens.add(item);
    }

    /**
     * Tenta retirar um item do local pelo nome.
     * Retorna o Item se encontrar, ou null se não existir.
     */
    public Item removerItem(String nomeItem) {
        for (Item i : itens) {
            if (i.getNome().equalsIgnoreCase(nomeItem)) {
                itens.remove(i);
                return i; // Devolve o objeto Item para quem o pediu
            }
        }
        return null; // Não encontrou
    }

    /**
     * Retorna a descrição, incluindo os itens visíveis.
     */
    public String getDescricao() {
        String texto = this.descricao;
        
        // Se houver itens, adiciona à descrição
        if (!itens.isEmpty()) {
            texto += "\nVocê vê: ";
            for (Item i : itens) {
                texto += i.getNome() + " ";
            }
        }
        return texto;
    }
    
    /**
     * Devolve uma string com os nomes de todas as saídas disponíveis.
     * Exemplo: "norte sul oeste"
     */
    public String getSaidasDisponiveis() {
        // keySet() devolve todas as chaves do mapa (os nomes das direções)
        // toString() formata-as automaticamente como [norte, sul]
        return saidas.keySet().toString();
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
