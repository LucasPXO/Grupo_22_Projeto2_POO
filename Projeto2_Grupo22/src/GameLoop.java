import java.util.Scanner;

public class GameLoop {

    private Jogador jogador;
    private final Parser parser; // Uma classe que ajuda a entender os comandos
    private boolean jogoEmCurso;

    public GameLoop() {
        criarMundo(); // Chama o método que cria os locais e o jogador
        this.parser = new Parser();
        this.jogoEmCurso = true;
    }
    
    private void criarMundo() {
        // Exemplos de Inicialização(debug)**
        
        // 1. Criar os locais
        Local ruaBaker = new Local("Está na 221B Baker Street, o seu escritório.");
        Local cenaDoCrime = new Local("Está numa viela escura. Há uma silhueta no chão.");
        Local delegacia = new Local("Está na Scotland Yard, o inspetor Lestrade parece impaciente.");
        Local mansao = new Local("Está nos portões de uma mansão imponente.");

        // 2. Ligar as saídas (ex: de Baker Street pode ir para a "cena" ou "delegacia")
        ruaBaker.adicionarSaida("cena do crime", cenaDoCrime);
        ruaBaker.adicionarSaida("delegacia", delegacia);
        
        // 3. Ligar as saídas de volta
        cenaDoCrime.adicionarSaida("baker street", ruaBaker);
        delegacia.adicionarSaida("baker street", ruaBaker);

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