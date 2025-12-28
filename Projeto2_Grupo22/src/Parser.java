/**
 * Classe responsável por analisar a entrada de texto do utilizador.
 * Transforma uma linha de texto bruta num objeto Comando processável.
 */
public class Parser {
    
    /**
     * Interpreta a linha digitada pelo jogador.
     * @param inputLinha A String capturada do teclado (ex: "ir cozinha" ou "acusar Joel").
     * @return Um objeto Comando contendo o verbo e o argumento isolados.
     */
    public Comando interpretar(String inputLinha) {
        // 1. Proteção contra Nulo ou Vazio (Segurança de Robusteza)
        // Evita que o programa falhe se o utilizador apenas carregar no 'Enter'
        if (inputLinha == null || inputLinha.trim().isEmpty()) {
            return new Comando(null, null);
        }

        // Normalização: Remove espaços extras e converte tudo para minúsculas
        // Isso garante que "IR", "Ir" e "ir" sejam tratados da mesma forma
        String linha = inputLinha.trim().toLowerCase(); 
        
        // 2. Tokenização: Divide a frase apenas no primeiro espaço encontrado
        // O limite '2' é crucial: permite argumentos com espaços (ex: "falar inspector lestrade")
        // Onde palavras[0] = "falar" e palavras[1] = "inspector lestrade"
        String[] palavras = linha.split(" ", 2); 

        String verbo = palavras[0];
        String argumento = null;

        // Verifica se existe uma segunda parte na frase (o argumento)
        if (palavras.length > 1) {
            argumento = palavras[1];
        }
        
        // Devolve o comando estruturado para ser validado pelo Vocabulario
        return new Comando(verbo, argumento);
    }
}