public class NPC {
    private String nome;
    private Local local;
    private String fala; // <--- NOVO

    public NPC(String nome, Local local) {
        this.nome = nome;
        this.local = local;
        this.fala = "..."; // Fala padrão se não houver ficheiro
        local.adicionarNPC(this);
    }
    
    // Define a fala (usado pelo carregador)
    public void setFala(String fala) {
        this.fala = fala;
    }

    // Retorna a fala (usado pelo jogador)
    public String getFala() {
        return this.fala;
    }
    
    public String getNome() { return this.nome; }
    public Local getLocal() { return this.local; }
}