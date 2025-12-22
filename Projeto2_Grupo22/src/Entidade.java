public abstract class Entidade {
    private String nome;
    
    public Entidade(String nome) {
        this.nome = nome;
    }
    
    public String getNome() { return nome; }
}