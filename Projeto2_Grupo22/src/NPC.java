/**
 * Representa um personagem não-jogável no mundo do jogo.
 * NPCs podem ter falas simples, pistas secretas ou árvores de diálogo complexas.
 */
public class NPC extends Entidade {
    private Local local;           // O local onde o NPC se encontra atualmente
    private String fala;           // Texto simples usado para NPCs sem menu de diálogo
    private Pista pistaSecreta;    // Pista que o NPC pode entregar ao jogador
    private NoDialogo dialogo;     // Estrutura que armazena as opções de conversa interativa
    
    /**
     * Construtor da classe NPC.
     * @param nome O nome do personagem (ex: "Mordomo", "Suspeito").
     * @param local O local onde o personagem será instanciado.
     */
    public NPC(String nome, Local local) {
        super(nome);
        this.local = local;
        this.fala = "...";         // Fala padrão caso nenhuma seja definida
        local.adicionarNPC(this);  // O NPC regista-se automaticamente no local ao ser criado
        this.pistaSecreta = null;
        this.dialogo = null; 
    }
    
    /**
     * Associa uma pista secreta a este NPC.
     * @param pista O objeto Pista que o NPC possui.
     */
    public void setPista(Pista pista) {
        this.pistaSecreta = pista;
    }
    
    /**
     * Recupera a pista associada ao NPC.
     */
    public Pista getPista() {
        return pistaSecreta;
    }
    
    /**
     * Define a fala simples (monólogo) do NPC.
     */
    public void setFala(String fala) {
        this.fala = fala;
    }
    
    /**
     * Recupera a fala simples do NPC.
     */
    public String getFala() {
        return this.fala;
    }
    
    /**
     * Configura o nó de diálogo interativo para este NPC.
     * @param dialogo O objeto NoDialogo contendo perguntas e respostas.
     */
    public void setDialogo(NoDialogo dialogo) {
        this.dialogo = dialogo;
    }
    
    /**
     * Recupera a árvore de diálogo interativo.
     */
    public NoDialogo getDialogo() {
        return this.dialogo;
    }
    
    /**
     * Verifica se o NPC possui um sistema de diálogo com múltiplas opções.
     * Útil para o ComandoFalar decidir se abre um menu ou apenas mostra uma fala.
     * @return true se houver um diálogo configurado com opções.
     */
    public boolean temDialogoInterativo() {
        return dialogo != null && dialogo.temOpcoes();
    }
    
    /**
     * Devolve a localização atual do NPC.
     */
    public Local getLocal() { 
        return this.local; 
    }
}