/**
 * Classe que representa uma evidência ou descoberta no jogo.
 * Estende a classe Entidade para herdar propriedades básicas como o nome.
 */
public class Pista extends Entidade { 
    
    // Texto detalhado que explica o significado da pista para a investigação
    private String conteudo; 

    /**
     * Construtor da classe Pista.
     * @param nome O título ou identificador da pista (ex: "Mancha de Sangue").
     * @param conteudo A descrição detalhada do que a pista revela.
     */
    public Pista(String nome, String conteudo) {
        super(nome); // Chama o construtor da classe base (Entidade)
        this.conteudo = conteudo;
    }

    /**
     * Devolve o conteúdo informativo da pista.
     * @return String contendo os detalhes da descoberta.
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Sobrescrita do método toString para facilitar a exibição da pista.
     * @return Representação textual formatada da pista.
     */
    @Override
    public String toString() {
        return getNome() + ": " + conteudo;
    }
}