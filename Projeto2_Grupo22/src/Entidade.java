public abstract class Entidade {
    private String nome; 
    
    public Entidade(String nome) {
        this.nome = nome;
    }
    
    // As subclasses (NPC, Item, Local) vão usar este método para ler o nome
    public String getNome() { 
        return nome; 
    }
}