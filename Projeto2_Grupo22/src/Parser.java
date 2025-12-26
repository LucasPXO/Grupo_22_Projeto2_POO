public class Parser {
    
    public Comando interpretar(String inputLinha) {
        // 1. Proteção contra Nulo ou Vazio (Segurança extra)
        if (inputLinha == null || inputLinha.trim().isEmpty()) {
            return new Comando(null, null);
        }

        String linha = inputLinha.trim().toLowerCase(); // Normalizar
        
        // 2. O segredo: Divide apenas no primeiro espaço
        // Ex: "acusar o mordomo" -> ["acusar", "o mordomo"]
        String[] palavras = linha.split(" ", 2); 

        String verbo = palavras[0];
        String argumento = null;

        if (palavras.length > 1) {
            argumento = palavras[1];
        }
        
        return new Comando(verbo, argumento);
    }
}