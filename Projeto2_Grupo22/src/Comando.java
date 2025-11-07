public class Comando {
    private String verbo;
    private String argumento;

    public Comando(String verbo, String argumento) {
        this.verbo = verbo;
        this.argumento = argumento;
    }
    
    public String getVerbo() {
        return this.verbo;
    }

    public String getArgumento() {
        return this.argumento;
    }
    
    // Getters...
    public boolean eValido() {
        return verbo != null;
    }
}