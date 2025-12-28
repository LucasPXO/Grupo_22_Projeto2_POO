import java.util.ArrayList;
import java.util.List;

/**
 * Representa um bloco de diálogo estruturado.
 * Contém a saudação do NPC, as opções de resposta do jogador e a mensagem de encerramento.
 */
public class NoDialogo {
    private String falaInicial;           // O texto que o NPC diz ao começar a conversa
    private List<OpcaoDialogo> opcoes;    // Lista de perguntas/interações disponíveis para o jogador
    private boolean temOpcoes;            // Flag para verificar rapidamente se existem escolhas
    private String mensagemDespedida;     // Texto exibido quando o jogador escolhe sair da conversa
    
    /**
     * Construtor para diálogos com despedida padrão.
     * @param falaInicial A primeira frase dita pelo NPC.
     */
    public NoDialogo(String falaInicial) {
        this.falaInicial = falaInicial;
        this.opcoes = new ArrayList<>();
        this.temOpcoes = false;
        this.mensagemDespedida = "Boa sorte com a investigação, detetive."; // Mensagem genérica inicial
    }
    
    /**
     * Construtor completo para personalizar a saudação e a despedida.
     * @param falaInicial Texto de abertura.
     * @param mensagemDespedida Texto de encerramento.
     */
    public NoDialogo(String falaInicial, String mensagemDespedida) {
        this.falaInicial = falaInicial;
        this.opcoes = new ArrayList<>();
        this.temOpcoes = false;
        this.mensagemDespedida = mensagemDespedida;
    }
    
    /**
     * Adiciona uma nova pergunta/opção ao menu de diálogo.
     * @param opcao O objeto OpcaoDialogo que contém a pergunta, resposta e possível pista.
     */
    public void adicionarOpcao(OpcaoDialogo opcao) {
        this.opcoes.add(opcao);
        this.temOpcoes = true; // Atualiza a flag indicando que agora há interações
    }
    
    // --- Getters e Setters ---

    public String getFalaInicial() { return falaInicial; }
    
    public List<OpcaoDialogo> getOpcoes() { return opcoes; }
    
    /**
     * Verifica se o diálogo possui escolhas para o jogador.
     */
    public boolean temOpcoes() { return temOpcoes; }
    
    public String getMensagemDespedida() { return mensagemDespedida; }
    
    public void setMensagemDespedida(String mensagem) { this.mensagemDespedida = mensagem; }
}