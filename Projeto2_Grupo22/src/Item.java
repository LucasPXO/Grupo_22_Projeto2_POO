public class Item extends Entidade{
    
    private String nome;
    private String descricao;

    public Item(String nome, String descricao) {
        super(nome);
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }
    
    // Sobrescrever o toString ajuda na hora de imprimir listas
    @Override
    public String toString() {
        return nome;
    }
}