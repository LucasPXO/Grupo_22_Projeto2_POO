/**
 * Representa um comando introduzido pelo utilizador.
 * Um comando consiste tipicamente num verbo (ação) e num argumento (alvo da ação).
 */
public class Comando {
    // A ação principal (ex: "ir", "recolher", "falar")
    private String verbo;
    
    // O alvo da ação (ex: "norte", "faca", "Lestrade")
    private String argumento;

    /**
     * Construtor da classe Comando.
     * @param verbo A primeira palavra do comando.
     * @param argumento A(s) palavra(s) seguinte(s) que complementam a ação.
     */
    public Comando(String verbo, String argumento) {
        this.verbo = verbo;
        this.argumento = argumento;
    }
    
    /**
     * Devolve o verbo do comando.
     * @return String com o verbo.
     */
    public String getVerbo() {
        return this.verbo;
    }

    /**
     * Devolve o argumento do comando.
     * @return String com o argumento, ou null se não existir.
     */
    public String getArgumento() {
        return this.argumento;
    }
    
    /**
     * Verifica se o comando é válido.
     * Um comando é considerado inválido se o verbo for nulo (não reconhecido pelo sistema).
     * @return true se for um comando reconhecido, false caso contrário.
     */
    public boolean eValido() {
        return verbo != null;
    }
}