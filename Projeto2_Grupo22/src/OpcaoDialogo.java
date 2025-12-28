/**
 * Representa uma interação individual dentro de um diálogo.
 * Mapeia uma pergunta do jogador a uma resposta do NPC e gerencia a entrega de pistas.
 */
public class OpcaoDialogo {
    private String texto;              // A frase que aparece no menu para o jogador escolher
    private String resposta;           // A réplica que o NPC dirá após a escolha
    private Pista pistaDesbloqueada;   // Evidência opcional que o jogador ganha ao fazer esta pergunta
    
    /**
     * Construtor para uma opção de diálogo simples (sem entrega de pistas).
     * @param texto A pergunta ou ação do jogador.
     * @param resposta O que o NPC diz em retorno.
     */
    public OpcaoDialogo(String texto, String resposta) {
        this.texto = texto;
        this.resposta = resposta;
        this.pistaDesbloqueada = null;
    }
    
    /**
     * Construtor para uma opção de diálogo que recompensa o jogador com uma pista.
     * @param texto A pergunta ou ação do jogador.
     * @param resposta A réplica do NPC.
     * @param pista O objeto Pista que será adicionado ao diário do jogador.
     */
    public OpcaoDialogo(String texto, String resposta, Pista pista) {
        this.texto = texto;
        this.resposta = resposta;
        this.pistaDesbloqueada = pista;
    }
    
    /**
     * Devolve o texto da pergunta/opção.
     */
    public String getTexto() { return texto; }

    /**
     * Devolve a resposta do NPC.
     */
    public String getResposta() { return resposta; }

    /**
     * Recupera a pista associada a esta escolha, se houver.
     * @return O objeto Pista ou null.
     */
    public Pista getPista() { return pistaDesbloqueada; }
}