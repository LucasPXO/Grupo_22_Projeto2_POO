import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Vocabulário do jogo - centraliza todos os verbos e suas ações.
 * Regista comandos e executa-os sem necessidade de switch.
 */
public class Vocabulario {
    private Map<String, AcaoComando> acoes;
    
    public Vocabulario(Jogador jogador, GameLoop gameLoop) {
        this.acoes = new HashMap<>();
        registarComandos(jogador, gameLoop);
    }
    
    /**
     * Regista todos os comandos do jogo.
     * Para adicionar um novo comando:
     * 1. Crie a classe ComandoXXX que implementa AcaoComando
     * 2. Adicione aqui: acoes.put("verbo", new ComandoXXX(...));
     */
    private void registarComandos(Jogador jogador, GameLoop gameLoop) {
        // Comandos de movimento
        acoes.put("ir", new ComandoIr(jogador));
        // Sinonimos
        acoes.put("andar", new ComandoIr(jogador));
        acoes.put("mover", new ComandoIr(jogador));
        acoes.put("caminhar", new ComandoIr(jogador));
        
        // Comandos de observação
        acoes.put("olhar", new ComandoOlhar(jogador));
        // Sinonimos
        acoes.put("ver", new ComandoOlhar(jogador));
        acoes.put("observar", new ComandoOlhar(jogador));
        
        acoes.put("inspecionar", new ComandoInspecionar(jogador));
        // Sinonimos        
        acoes.put("examinar", new ComandoInspecionar(jogador));
        acoes.put("investigar", new ComandoInspecionar(jogador));
        acoes.put("analisar", new ComandoInspecionar(jogador));
        
        // Comandos de interação
        acoes.put("falar", new ComandoFalar(jogador));
        // Sinonimos
        acoes.put("conversar", new ComandoFalar(jogador));
        acoes.put("perguntar", new ComandoFalar(jogador));
        
        acoes.put("recolher", new ComandoRecolher(jogador));
        // Sinonimos
        acoes.put("pegar", new ComandoRecolher(jogador));
        acoes.put("apanhar", new ComandoRecolher(jogador));
        acoes.put("agarrar", new ComandoRecolher(jogador));
        
        // Comandos de informação
        acoes.put("inventario", new ComandoInventario(jogador));
        // Sinonimos
        acoes.put("inv", new ComandoInventario(jogador));
        acoes.put("i", new ComandoInventario(jogador));
        
        acoes.put("pistas", new ComandoPistas(jogador));
        
        acoes.put("acusar", new ComandoAcusar(gameLoop, jogador));
        
        // Comandos de sistema
        acoes.put("ajuda", new ComandoAjuda());
        // Sinonimos
        acoes.put("help", new ComandoAjuda());
        
        acoes.put("sair", new ComandoSair(gameLoop));
        // Sinonimos
        acoes.put("quit", new ComandoSair(gameLoop));
        acoes.put("terminar", new ComandoSair(gameLoop));
        acoes.put("exit", new ComandoSair(gameLoop));
                
        // ===== ADICIONE NOVOS COMANDOS AQUI =====
        // Exemplo:
        // acoes.put("usar", new ComandoUsar(jogador));
        // acoes.put("atacar", new ComandoAtacar(jogador));
        // acoes.put("combinar", new ComandoCombinar(jogador));
    }
   
    
    /**
     * Executa um comando com base no verbo
     * @return true se o comando foi executado, false se não existe
     */
    /**
     * Executa um comando com base no verbo.
     * Agora protege contra comandos nulos ou desconhecidos.
     */
    public void executarComando(Comando comando) {
        String verbo = comando.getVerbo();
        String argumento = comando.getArgumento();
        
        // 1. Proteção contra verbo nulo (se o Parser falhar)
        if (verbo == null) {
            System.out.println("Não percebi o que quer dizer.");
            return;
        }
        
        String verboNormalizado = normalizarVerbo(verbo);
        
        // 2. Proteção contra verbo desconhecido (não está no mapa)
        if (verboNormalizado == null) {
            System.out.println("Não sei como fazer '" + verbo + "'.");
            return;
        }
        
        AcaoComando acao = acoes.get(verboNormalizado);
        
        // 3. Execução segura
        if (acao != null) {
            acao.executar(argumento);
        } else {
            // Caso raro onde normalizou mas não achou a ação
            System.out.println("Erro interno: Ação não encontrada.");
        }
    }
    
    /**
     * Normaliza um verbo (converte sinónimos para o verbo principal)
     */
    private String normalizarVerbo(String verbo) {
        if (verbo == null) return null; // Segurança extra
        
        String verboLower = verbo.toLowerCase();
        
        if (acoes.containsKey(verboLower)) {
            return verboLower;
        }
        
        return null;
    }
    
    /**
     * Verifica se um verbo é válido
     */
    public boolean eVerboValido(String verbo) {
        return normalizarVerbo(verbo) != null;
    }
    
    /**
     * Adiciona um novo comando dinamicamente
     */
    public void adicionarComando(String verbo, AcaoComando acao) {
        acoes.put(verbo.toLowerCase(), acao);
    }
   
    
    /**
     * Retorna todos os verbos principais registados
     */
    public Set<String> getVerbos() {
        return acoes.keySet();
    }
    
    /**
     * Retorna o número de comandos registados
     */
    public int getNumeroComandos() {
        return acoes.size();
    }
}