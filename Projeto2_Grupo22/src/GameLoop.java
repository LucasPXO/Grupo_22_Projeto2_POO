import java.util.Scanner;
import java.util.ArrayList; 
import java.util.List;

public class GameLoop {

    private Jogador jogador;
    private Parser parser;
    private Vocabulario vocabulario;  
    private boolean jogoEmCurso;
    private List<NPC> todosNpcs;

    public GameLoop() {
        this.todosNpcs = new ArrayList<>();
        criarMundo();
        
        CarregadorDialogos carregador = new CarregadorDialogos();
        carregador.carregar("dialogos.txt", todosNpcs);
        
        // ← NOVO: Inicializa o Vocabulário DEPOIS de criar o jogador
        this.vocabulario = new Vocabulario(jogador, this);
        this.parser = new Parser();
        this.jogoEmCurso = true;
    }
    
    private void criarMundo() {
        // Criar os locais
        Local ruaBaker = new Local("Está na 221B Baker Street, o seu escritório.", "Rua Baker");
        Local beco = new Local("Está numa viela escura. Há uma silhueta no chão.", "Beco");
        Local delegacia = new Local("Está na Scotland Yard, o inspetor Lestrade parece impaciente.", "Delegacia");
        Local mansao = new Local("Está nos portões de uma mansão imponente.", "Mansão");
        Local loja = new Local("Acaba de entrar na loja do Sr.Manuel, que se encontra ao balcão a limpar alguns artigos.", "Loja");
        Local armazem = new Local("Está dentro do armazém da loja do Sr.Manuel. Está escuro, empoeirado mas com um ar suspeito.", "Armazem");
        Local barbearia = new Local("Entra na recém aberta barbearia do Lucas. É a mais popular da cidade.", "barbearia");
        Local casa = new Local("Finalmente chegou a casa. Está cansado mas sabe que a busca ainda não terminou.", "casa");
        Local escadaria = new Local("Uma alta escadaria. No topo, consegue ver uma floresta.", "escadaria");
        Local floresta = new Local("Uma floresta densa, escura, fria e cheia de má reputação. Avance com cautela.", "floresta");
        Local bar = new Local("Acaba de entrar no bar da Senhora Irlinda. O cheiro a bebida, comida quente e música de qualidade deixam-no confortável.", "bar");
        
        NPC cidadao = new NPC("Cidadao", ruaBaker);
        NPC cao = new NPC("Cao", ruaBaker);
        NPC lestrade = new NPC("Inspetor Lestrade", delegacia);
        NPC mordomo = new NPC("Mordomo", mansao);
        NPC srManuel = new NPC("Sr.Manuel", loja);
        NPC lucas = new NPC("Lucas", barbearia);
        NPC semAbrigo = new NPC("Sem Abrigo", escadaria);
        NPC irlinda = new NPC("Sra.Irlinda", bar);
        NPC joel = new NPC("Joel", bar);
        
        todosNpcs.add(cidadao);         
        todosNpcs.add(lestrade);     
        todosNpcs.add(mordomo); 
        todosNpcs.add(cao);
        todosNpcs.add(srManuel);
        todosNpcs.add(lucas);
        todosNpcs.add(semAbrigo);
        todosNpcs.add(irlinda);
        todosNpcs.add(joel);
        
        // Ligar as saídas
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
        delegacia.adicionarSaida("escadaria", escadaria);
        
        loja.adicionarSaida("baker street", ruaBaker);
        loja.adicionarSaida("armazem", armazem);
        loja.adicionarSaida("barbearia", barbearia);
        
        armazem.adicionarSaida("loja", loja);
        
        barbearia.adicionarSaida("loja", loja);
        barbearia.adicionarSaida("casa", casa);
        
        casa.adicionarSaida("delegacia", delegacia);
        casa.adicionarSaida("barbearia", barbearia);
        
        escadaria.adicionarSaida("delegacia", delegacia);
        escadaria.adicionarSaida("floresta", floresta);
        
        floresta.adicionarSaida("escadaria",escadaria);
        floresta.adicionarSaida("beco", beco);
        
        bar.adicionarSaida("baker street", ruaBaker);
        bar.adicionarSaida("beco", beco);
        
        mansao.adicionarSaida("beco", beco);
        
        Item lupa = new Item("Lupa", "Uma lupa antiga.");
        Item faca = new Item("Faca", "Uma faca de cozinha com sangue seco. É mesmo parecida às que a Sra.Irlinda usa no seu bar.");
        Item carta = new Item("Carta", "->Uma nota de dívida assinada pelo Lucas (o Barbeiro). Valor: 5000 libras. \n"
                + "->Junto da mesma, tem um texto do Barnabas que diz o seguinte:\n"
                + "->'Encontra-te comigo amanhã pelas 22h no beco para me pagares a dívida, sem falta!! Ou haverão consequências...' "); // <--- NOVO ITEM

        ruaBaker.adicionarItem(lupa);
        beco.adicionarItem(faca);
        beco.adicionarItem(carta);
        
        // 1. Configurar Pista na Faca
        faca.setPista(new Pista("Arma do Crime", "Faca encontrada junto ao corpo."));

        // 2. Configurar Pista na Carta
        carta.setPista(new Pista("Dívida do Barbeiro", "Prova que o Lucas devia dinheiro."));

        // 3. Configurar Pista no Sr. Manuel
        srManuel.setPista(new Pista("Vulto na Loja", "Manuel viu alguém fugir para a Barbearia."));

        // 4. Configurar Pista no Sem Abrigo
        semAbrigo.setPista(new Pista("Testemunho do Sem Abrigo", "Viu o Lucas no beco."));

        // Criar o jogador
        this.jogador = new Jogador(ruaBaker);
    }

    public void iniciar() {
        System.out.println("Bem-vindo ao Sherlock Holmes 25/26!");
        System.out.println("Escreva 'ajuda' para ver os comandos disponíveis.");
        System.out.println("---");

        System.out.println(jogador.getLocalAtual().mostrarInfo()); 

        Scanner scanner = new Scanner(System.in);
        
        while (jogoEmCurso) {
            System.out.print("> ");
            String inputUtilizador = scanner.nextLine();
            
            // O Parser apenas extrai verbo e argumento
            Comando comando = parser.interpretar(inputUtilizador);
            vocabulario.executarComando(comando);
           
            jogador.getLocalAtual().getSaidasDisponiveis();
           
        }
        
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