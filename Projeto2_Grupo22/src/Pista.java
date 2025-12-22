public class Pista extends Entidade { // <--- Herda nome de Entidade
    
    private String conteudo; // A informação/descrição da pista

    public Pista(String nome, String conteudo) {
        super(nome); // Envia o nome para a classe mãe (Entidade)
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    @Override
    public String toString() {
        return getNome() + ": " + conteudo;
    }
}