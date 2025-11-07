import java.util.HashMap;
import java.util.Map;

public class Local {

    private String descricao;
    private Map<String, Local> saidas; // Chave: "norte", "delegacia", etc. Valor: O objeto Local.

    public Local(String descricao) {
        this.descricao = descricao;
        this.saidas = new HashMap<>(); // Inicializa o mapa de saídas
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
    public String getDescricao() {
        return this.descricao;
        // Poderíamos adicionar aqui os NPCs e Itens visíveis
    }
    
    // ... outras
    // ... getters para NPCs, Itens, etc.
}