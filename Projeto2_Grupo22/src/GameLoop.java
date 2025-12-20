import java.util.Scanner;
import java.util.ArrayList; 
import java.util.List;

public class GameLoop {

    private Jogador jogador;
    private final Parser parser; // Uma classe que ajuda a entender os comandos
    private boolean jogoEmCurso;
    private List<NPC> todosNpcs;

    public GameLoop() {
        this.todosNpcs = new ArrayList<>(); // Garanta que isto está antes de criarMundo
        criarMundo();
        
        // --- ADICIONE ISTO AQUI ---
        CarregadorDialogos carregador = new CarregadorDialogos();
        carregador.carregar("dialogos.rtf", todosNpcs);
        // --------------------------
        
        this.parser = new Parser();
        this.jogoEmCurso = true;
    }
    
    private void criarMundo() {
        // Exemplos de Inicialização(debug)**
        
        // 1. Criar os locais
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
        
        
        // 2. Ligar as saídas (ex: de Baker Street pode ir para a "cena" ou "delegacia")
        ruaBaker.adicionarSaida("cena do crime", cenaDoCrime);
        ruaBaker.adicionarSaida("delegacia", delegacia);
        ruaBaker.adicionarSaida("loja", loja);
        ruaBaker.adicionarSaida("bar", bar);
        
        
        // 3. Ligar as saídas de volta
        cenaDoCrime.adicionarSaida("baker street", ruaBaker);
        cenaDoCrime.adicionarSaida("floresta", floresta);
        cenaDoCrime.adicionarSaida("bar", bar);
        
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
        

        // 4. Ligar a mansão (ex: só se chega pela cena do crime)
        cenaDoCrime.adicionarSaida("mansao", mansao);
        mansao.adicionarSaida("cena do crime", cenaDoCrime);
        
        // 5. Adicionar Itens
        Item lupa = new Item("Lupa", "Uma lupa antiga, boa para ver detalhes.");
        Item faca = new Item("Faca", "Uma faca afiada encontrada no chão.");

        // 6. Colocar itens nos locais
        ruaBaker.adicionarItem(lupa);
        cenaDoCrime.adicionarItem(faca);

        // 7. Criar o jogador e colocá-lo no local inicial
        this.jogador = new Jogador(ruaBaker);
    }

    /**
     * O loop principal do jogo.
     */
    public void iniciar() {
        System.out.println("Bem-vindo ao Sherlock Holmes 25/26!");
        System.out.println("Escreva 'ajuda' para ver os comandos disponíveis.");
        System.out.println("---");

        // Mostra a descrição inicial do primeiro local
        System.out.println(jogador.olhar()); 

        Scanner scanner = new Scanner(System.in);
        
        while (jogoEmCurso) {
            System.out.print("> "); // Prompt para o utilizador
            String inputUtilizador = scanner.nextLine();
            
            // 1. O Parser interpreta o input
            Comando comando = parser.interpretar(inputUtilizador);
            
            // 2. O GameLoop delega a execução
            executarComando(comando);
        }
        
        scanner.close();
        System.out.println("Obrigado por jogar. Adeus!");
    }

    /**
     * Delega a ação com base no comando interpretado.
     */
    private void executarComando(Comando comando) {
        if (!comando.eValido()) {
            System.out.println("Não percebi o que quer dizer. Tente 'ajuda'.");
            return;
        }

        String verbo = comando.getVerbo();
        String argumento = comando.getArgumento();

        switch (verbo) {
            case "sair":
                jogoEmCurso = false; // 
                break;
            case "ajuda":
                mostrarAjuda(); //
                break;
            case "ir":
                jogador.mover(argumento); // 
                break;
            case "olhar":
                System.out.println(jogador.olhar()); // 
                break;
            case "inspecionar":
                System.out.println(jogador.inspecionar(argumento)); // 
                break;
            case "falar":
                System.out.println(jogador.falar(argumento)); // 
                break;
            case "inventario":
                jogador.listarInventario(); // 
                break;
            case "pistas":
                jogador.listarPistas(); // 
                break;
            case "recolher":
                jogador.recolher(argumento);// 
                break;
            // Outros comandos como "usar", "examinar", "recolher" 
            default:
                System.out.println("Comando desconhecido.");
        }
    }

    private void mostrarAjuda() {
        System.out.println("Os seus comandos são:");
        System.out.println("   ir <local>");
        System.out.println("   olhar");
        System.out.println("   recolher");
        System.out.println("   inspecionar <objeto>");
        System.out.println("   falar <npc>");
        System.out.println("   recolher <item>");
        System.out.println("   inventario");
        System.out.println("   pistas");
        System.out.println("   sair");
    }
    
    public static void main(String[] args) {
        GameLoop jogo = new GameLoop();
        jogo.iniciar();
    }
}