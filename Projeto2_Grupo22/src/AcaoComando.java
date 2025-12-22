public interface AcaoComando {
    /**
     * Executa a ação do comando
     * @param argumento O argumento do comando (pode ser null)
     */
    void executar(String argumento);
}