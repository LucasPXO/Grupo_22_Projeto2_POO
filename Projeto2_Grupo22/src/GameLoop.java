import java.util.Scanner;
import java.util.ArrayList; 
import java.util.List;

/**
 * Classe principal que controla o fluxo do jogo (Game Loop).
 * Responsável por inicializar o mundo, processar comandos e manter o estado do jogo.
 */
public class GameLoop {

    // --- Atributos ---
    private Jogador jogador;
    private Parser parser;
    private Vocabulario vocabulario;  
    private boolean jogoEmCurso;
    private List<NPC> todosNpcs;
    private Scanner scanner;

    /**
     * Construtor: Inicializa as listas, cria o mundo e carrega diálogos.
     */
    public GameLoop() {
        this.scanner = new Scanner(System.in);
        this.todosNpcs = new ArrayList<>();
        
        // 1. Constrói a geografia e itens do jogo
        criarMundo();
        
        // 2. Carrega as falas dos NPCs a partir de arquivo externo
        CarregadorDialogos carregador = new CarregadorDialogos();
        carregador.carregar("dialogos.txt", todosNpcs);
        
        // 3. Inicializa sistemas de comando (Vocabulário depende do jogador criado em criarMundo)
        this.vocabulario = new Vocabulario(jogador, this);
        this.parser = new Parser();
        this.jogoEmCurso = true;
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Define todos os locais, conexões (saídas), NPCs e itens/pistas.
     */
    private void criarMundo() {
        // --- 1. Definição de Locais ---
        Local ruaBaker = new Local("Está na 221B Baker Street, o seu escritório.", "Rua Baker");
        Local beco = new Local("Está numa viela escura. Há uma silhueta no chão.", "Beco");
        Local delegacia = new Local("Está na Scotland Yard, o inspetor Lestrade parece impaciente.", "Delegacia");
        Local mansao = new Local("Está nos portões de uma mansão imponente.", "Mansão");
        Local loja = new Local("Acaba de entrar na loja do Sr.Manuel, ao balcão.", "Loja");
        Local armazem = new Local("Está dentro do armazém da loja. Está escuro e suspeito.", "Armazem");
        Local barbearia = new Local("Entra na recém aberta barbearia do Lucas.", "barbearia");
        Local casa = new Local("Finalmente chegou a casa. A busca continua.", "casa");
        Local escadaria = new Local("Uma alta escadaria. No topo vê uma floresta.", "escadaria");
        Local floresta = new Local("Uma floresta densa e fria. Avance com cautela.", "floresta");
        Local bar = new Local("Entra no bar da Sra. Irlinda. Ambiente confortável.", "bar");
        
        // --- 2. Criação e Alocação de NPCs ---
        NPC cidadao = new NPC("Cidadao", ruaBaker);
        NPC cao = new NPC("Cao", ruaBaker);
        NPC lestrade = new NPC("Inspetor Lestrade", delegacia);
        NPC mordomo = new NPC("Mordomo", mansao);
        NPC srManuel = new NPC("Sr.Manuel", loja);
        NPC lucas = new NPC("Lucas", barbearia);
        NPC semAbrigo = new NPC("Sem Abrigo", escadaria);
        NPC irlinda = new NPC("Sra.Irlinda", bar);
        NPC joel = new NPC("Joel", bar);
        NPC eremita = new NPC("Eremita", floresta);
        
        // Adiciona NPCs à lista global para carregamento de diálogos
        todosNpcs.add(cidadao);         
        todosNpcs.add(lestrade);     
        todosNpcs.add(mordomo); 
        todosNpcs.add(cao);
        todosNpcs.add(srManuel);
        todosNpcs.add(lucas);
        todosNpcs.add(semAbrigo);
        todosNpcs.add(irlinda);
        todosNpcs.add(joel);
        todosNpcs.add(eremita);

        // --- 3. Configuração do Mapa (Conexões entre locais) ---
        ruaBaker.adicionarSaida("beco", beco);
        ruaBaker.adicionarSaida("delegacia", delegacia);
        ruaBaker.adicionarSaida("loja", loja);
        ruaBaker.adicionarSaida("bar", bar);
        
        beco.adicionarSaida("baker street", ruaBaker);
        beco.adicionarSaida("floresta", floresta);
        beco.adicionarSaida("bar", bar);
        beco.adicionarSaida("mansao", mansao);
        
        delegacia.adicionarSaida("baker street", ruaBaker);
        delegacia.adicionarSaida("casa", casa);
        
        loja.adicionarSaida("baker street", ruaBaker);
        loja.adicionarSaida("armazem", armazem);
        loja.adicionarSaida("barbearia", barbearia);
        
        armazem.adicionarSaida("loja", loja);
        barbearia.adicionarSaida("loja", loja);
        casa.adicionarSaida("delegacia", delegacia);
        
        escadaria.adicionarSaida("delegacia", delegacia);
        escadaria.adicionarSaida("floresta", floresta);
        
        floresta.adicionarSaida("escadaria", escadaria);
        floresta.adicionarSaida("beco", beco);
        
        bar.adicionarSaida("baker street", ruaBaker);
        bar.adicionarSaida("beco", beco);
        
        mansao.adicionarSaida("beco", beco);

        // --- 4. Criação de Itens e Pistas ---
        // Itens do Beco
        Item faca = new Item("Faca", "Uma faca de cozinha com sangue seco e símbolo do bar.");
        Item carta = new Item("Carta", "Uma carta com dívida do Lucas. Assinatura suspeita.");
        Item lenco = new Item("Lenço", "Lenço branco com iniciais 'J.M.' manchado de sangue.");
        beco.adicionarItem(faca);
        beco.adicionarItem(carta);
        beco.adicionarItem(lenco);

        // Itens do Armazém
        Item pegadas = new Item("Pegadas", "Pegadas de lama pequenas, parecem botas de trabalho.");
        Item corda = new Item("Corda", "Corda velha com nó de marinheiro.");
        Item cartaAmeaca = new Item("Carta de Ameaça", "Menciona pecados de 1795.");
        Item recibo = new Item("Recibo", "Compra de luvas por Joel Martins.");
        armazem.adicionarItem(pegadas);
        armazem.adicionarItem(corda);
        armazem.adicionarItem(cartaAmeaca);
        armazem.adicionarItem(recibo);

        // Itens da Floresta
        Item luvas = new Item("Luvas", "Luvas ensanguentadas com plano de improviso.");
        Item mochila = new Item("Mochila", "Contém 10.000 libras e foto da Sra. Irlinda.");
        Item rasgoRoupa = new Item("Tecido Rasgado", "Pedaço de avental de bar preso num galho.");
        Item diario = new Item("Diário", "Confissão de vingança de 30 anos assinada por 'J'.");
        floresta.adicionarItem(luvas);
        floresta.adicionarItem(mochila);
        floresta.adicionarItem(rasgoRoupa);
        floresta.adicionarItem(diario);

        // Outros itens
        casa.adicionarItem(new Item("Jornal Velho", "Notícia de 1795 sobre Barnabas."));
        
        Item registro = new Item("Registro", "Diário de Irlinda sobre abandono em 1795.");
        Item avental = new Item("Avental Limpo", "Avental de Joel com cheiro de ferro (sangue).");
        bar.adicionarItem(registro);
        bar.adicionarItem(avental);

        // --- 5. Associação de Pistas aos Itens (Lógica de Investigação) ---
        faca.setPista(new Pista("Arma do Crime", "Faca do bar da Irlinda. Possível incriminação."));
        lenco.setPista(new Pista("Iniciais Misteriosas", "J.M. - Joel Martins?"));
        diario.setPista(new Pista("Confissão", "Joel planejou tudo; Lucas era o bode expiatório."));
        mochila.setPista(new Pista("Dinheiro", "As 10.000 libras roubadas de Barnabas."));
        registro.setPista(new Pista("Segredo", "Irlinda foi abandonada grávida por Barnabas em 1795."));

        // --- 6. Inicialização do Jogador ---
        this.jogador = new Jogador(delegacia);
    }

    /**
     * Inicia o ciclo principal de entrada e saída de dados.
     */
    public void iniciar() {
        System.out.println("Bem-vindo ao Sherlock Holmes 25/26!");
        System.out.println("Escreva 'ajuda' para ver os comandos disponíveis.");
        System.out.println("---");
        System.out.println(jogador.getLocalAtual().mostrarInfo()); 

        // Loop de execução do jogo
        while (jogoEmCurso) {
            System.out.print("> ");
            String inputUtilizador = scanner.nextLine();
            
            // Processa o comando através do Parser e executa via Vocabulário
            Comando comando = parser.interpretar(inputUtilizador);
            vocabulario.executarComando(comando);
            
            // Dica visual de saídas
            jogador.getLocalAtual().getSaidasDisponiveis();
        }
        
        encerrar();
    }
    
    private void encerrar() {
        scanner.close();
        System.out.println("Obrigado por jogar. Adeus!");
    }

    public void terminarJogo() {
        this.jogoEmCurso = false;
    }
    
    public static void main(String[] args) {
        GameLoop jogo = new GameLoop();
        jogo.iniciar();
    }
}