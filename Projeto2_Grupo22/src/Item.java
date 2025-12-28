/**
 * Representa um objeto físico no mundo do jogo que pode ser coletado ou inspecionado.
 * Estende a classe Entidade para herdar o comportamento básico de objetos nomeados.
 */
public class Item extends Entidade {
    // Descrição detalhada que aparece ao inspecionar o item
    private String descricao;
    
    // Referência para uma pista que pode estar vinculada a este objeto
    private Pista pistaEscondida; 

    /**
     * Construtor da classe Item.
     * @param nome O nome simples do item (ex: "Relógio", "Carta").
     * @param descricao O texto explicativo sobre o item.
     */
    public Item(String nome, String descricao) {
        super(nome); // Chama o construtor da classe pai (Entidade)
        this.descricao = descricao;
        this.pistaEscondida = null; // Por padrão, inicia sem nenhuma pista vinculada
    }

    /**
     * Define ou vincula uma pista a este item.
     * @param pista O objeto Pista a ser armazenado dentro do item.
     */
    public void setPista(Pista pista) {
        this.pistaEscondida = pista;
    }

    /**
     * Recupera a pista escondida no item, se existir.
     * @return O objeto Pista ou null se o item não contiver pistas.
     */
    public Pista getPista() {
        return pistaEscondida;
    }

    /**
     * Retorna a descrição do item.
     * @return String contendo a descrição.
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Sobrescreve o método toString para retornar apenas o nome do item.
     * Útil para exibir listas de itens (como no inventário) de forma limpa.
     * @return O nome da entidade.
     */
    @Override
    public String toString() {
        return getNome();
    }
}