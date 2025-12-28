import java.util.ArrayList; 
import java.util.List;

/**
 * Representa o protagonista do jogo.
 * Mantém o rastro da localização atual, itens coletados e o progresso da investigação (diário).
 */
public class Jogador {
    
    // --- Atributos ---
    private Local localAtual;
    private List<Item> inventario;
    private List<Pista> diario;

    /**
     * Construtor do Jogador.
     * @param localInicial O ponto de partida do jogador no mapa.
     */
    public Jogador(Local localInicial) {
        this.localAtual = localInicial;
        this.inventario = new ArrayList<>();
        this.diario = new ArrayList<>();
    }
    
    // --- Métodos de Navegação ---

    public void setLocalAtual(Local novoLocal) {
        this.localAtual = novoLocal;
    }
    
    public Local getLocalAtual() {
        return this.localAtual;
    }

    // --- Gestão de Inventário e Investigação ---
  
    public List<Item> getInventario() {
        return this.inventario;
    }
    
    public List<Pista> getDiario() {
        return this.diario;
    }

    /**
     * Adiciona uma nova pista ao diário, garantindo que não haja duplicatas.
     * @param novaPista A pista a ser registada.
     */
    public void adicionarPista(Pista novaPista) {
        if (novaPista == null) return;

        // Verifica se a pista já existe no diário pelo nome (evita duplicados)
        boolean jaExiste = diario.stream()
                .anyMatch(p -> p.getNome().equalsIgnoreCase(novaPista.getNome()));

        if (!jaExiste) {
            diario.add(novaPista);
            System.out.println("\n(i) NOVA PISTA REGISTADA: " + novaPista.getNome());
        }
    }
    
    /**
     * Devolve o número total de pistas recolhidas até ao momento.
     * @return Total de objetos Pista no diário.
     */
    public int getNumeroPistas() {
        return diario.size();
    }
}