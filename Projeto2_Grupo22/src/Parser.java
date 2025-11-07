public class Parser {
    
    public Comando interpretar(String inputLinha) {
        String linha = inputLinha.trim().toLowerCase(); // Normalizar
        String[] palavras = linha.split(" ", 2); // Divide no primeiro espaço

        if (palavras.length == 0) {
            return new Comando(null, null); // Input vazio
        }

        String verbo = palavras[0];
        String argumento = null;

        if (palavras.length > 1) {
            argumento = palavras[1];
        }

        // Aqui pode verificar se o "verbo" é um comando conhecido
        // (ex: "ir", "olhar", "falar")
        
        return new Comando(verbo, argumento);
    }
}