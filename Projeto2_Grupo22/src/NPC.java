public class NPC extends Entidade {
    private Local local;
    private String fala;
    private Pista pistaSecreta; // <--- NOVO

    public NPC(String nome, Local local) {
        super(nome);
        this.local = local;
        this.fala = "...";
        local.adicionarNPC(this);
        this.pistaSecreta = null;
    }

    public void setPista(Pista pista) {
        this.pistaSecreta = pista;
    }

    public Pista getPista() {
        return pistaSecreta;
    }
    
    // Define a fala (usado pelo carregador)
    public void setFala(String fala) {
        this.fala = fala;
    }

    // Retorna a fala (usado pelo jogador)
    public String getFala() {
        return this.fala;
    }
    
    public Local getLocal() { return this.local; }
}