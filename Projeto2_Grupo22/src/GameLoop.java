import java.util.Scanner;
import java.util.ArrayList; 
import java.util.List;

public class GameLoop {

    private Jogador jogador;
    private Parser parser;
    private Vocabulario vocabulario;  // ← NOVO
    private boolean jogoEmCurso;
    private List<NPC> todosNpcs;

    public GameLoop() {
        this.todosNpcs = new ArrayList<>();
        criarMundo();
        
        CarregadorDialogos carregador = new CarregadorDialogos();
        carregador.carregar("dialogos.rtf", todosNpcs);
        
        // ← NOVO: Inicializa o Vocabulário DEPOIS de criar o jogador
        this.vocabulario = new Vocabulario(jogador, this);
        this.parser = new Parser();
        this.jogoEmCurso = true;
    }
    
    private void criarMundo() {
        // Criar os locais
        Local ruaBaker = new Local("Está na 221B Baker Street, o seu escritório.", "Rua Baker");
        Local cenaDoCrime = new Local("Está numa viela escura. Há uma silhueta no chão.", "Beco");
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
        ruaBaker.adicionarSaida("cena do crime", cenaDoCrime);
        ruaBaker.adicionarSaida("delegacia", delegacia);
        ruaBaker.adicionarSaida("loja", loja);
        ruaBaker.adicionarSaida("bar", bar);
        
        cenaDoCrime.adicionarSaida("baker street", ruaBaker);
        cenaDoCrime.adicionarSaida("floresta", floresta);
        cenaDoCrime.adicionarSaida("bar", bar);
        cenaDoCrime.adicionarSaida("mansao", mansao);
        
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
        floresta.adicionarSaida("cena do crime", cenaDoCrime);
        
        bar.adicionarSaida("baker street", ruaBaker);
        bar.adicionarSaida("cena do crime", cenaDoCrime);
        
        mansao.adicionarSaida("cena do crime", cenaDoCrime);
        
        // Adicionar Itens
        Item lupa = new Item("Lupa", "Uma lupa antiga, boa para ver detalhes.");
        Item faca = new Item("Faca", "Uma faca afiada encontrada no chão.");

        ruaBaker.adicionarItem(lupa);
        cenaDoCrime.adicionarItem(faca);

        // Criar o jogador
        this.jogador = new Jogador(ruaBaker);
    }

    public void iniciar() {
        System.out.println("Bem-vindo ao Sherlock Holmes 25/26!");
        System.out.println("Escreva 'ajuda' para ver os comandos disponíveis.");
        System.out.println("---");

        System.out.println(jogador.olhar()); 

        Scanner scanner = new Scanner(System.in);
        
        while (jogoEmCurso) {
            System.out.print("> ");
            String inputUtilizador = scanner.nextLine();
            
            // O Parser apenas extrai verbo e argumento
            Comando comando = parser.interpretar(inputUtilizador);
            
            // ← MUDANÇA: Agora delega ao Vocabulário (SEM SWITCH!)
            executarComando(comando);
        }
        
        scanner.close();
        System.out.println("Obrigado por jogar. Adeus!");
    }

    
    private void executarComando(Comando comando) {
        if (!comando.eValido()) {
            System.out.println("Não percebi o que quer dizer. Tente 'ajuda'.");
            return;
        }

        String verbo = comando.getVerbo();
        String argumento = comando.getArgumento();

        boolean sucesso = vocabulario.executarComando(verbo, argumento);
        
        if (!sucesso) {
            System.out.println("Comando desconhecido. Tente 'ajuda'.");
        }
        
        
    }
    
    
    public void terminarJogo() {
        this.jogoEmCurso = false;
    }
    
    
    public static void main(String[] args) {
        GameLoop jogo = new GameLoop();
        jogo.iniciar();
    }
}