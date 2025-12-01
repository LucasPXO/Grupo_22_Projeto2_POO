

/**
 *
 * @author gugar
 */
public  class NPC {
    private String nome;
    private Local local;
    
    public NPC(String nome, Local local){
        this.nome=nome;
        this.local=local;
        local.adicionarNPC(this);
}
    
    public String getNome(){return this.nome;}
    public Local getLocal(){return this.local;}
    
}
