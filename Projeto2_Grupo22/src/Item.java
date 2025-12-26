public class Item extends Entidade {
    private String descricao;
    private Pista pistaEscondida; // <--- NOVO

    public Item(String nome, String descricao) {
        super(nome);
        this.descricao = descricao;
        this.pistaEscondida = null; // Por defeito não tem pista
    }

    public void setPista(Pista pista) {
        this.pistaEscondida = pista;
    }

    public Pista getPista() {
        return pistaEscondida;
    }

    public String getDescricao() {
        return descricao;
    }
    
    // Sobrescrever o toString ajuda na hora de imprimir listas
    @Override
    public String toString() {
        return getNome();
    }
}