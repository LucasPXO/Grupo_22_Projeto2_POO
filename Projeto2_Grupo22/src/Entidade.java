public abstract class Entidade {
    
    // 1. MUDAR PARA PRIVATE (Encapsulamento Forte)
    private String nome; 
    
    public Entidade(String nome) {
        this.nome = nome;
    }
    
    // As subclasses (NPC, Item, Local) vão usar este método para ler o nome
    public String getNome() { 
        return nome; 
    }
}