import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CarregadorDialogos {

    public void carregar(String caminhoFicheiro, List<NPC> listaNpcs) {
        // Tenta ler o ficheiro (Try-Catch é obrigatório para nota!)
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoFicheiro))) {
            String linha;
            
            // Lê cada linha do ficheiro
            while ((linha = br.readLine()) != null) {
                // Divide a linha no caractere ':' (Ex: "Cidadao:Olá")
                String[] partes = linha.split(":", 2);
                
                if (partes.length == 2) {
                    String nomeNpc = partes[0].trim();
                    String fala = partes[1].trim();
                    
                    // Procura o NPC na lista e define a fala
                    for (NPC npc : listaNpcs) {
                        if (npc.getNome().equalsIgnoreCase(nomeNpc)) {
                            npc.setFala(fala);
                        }
                    }
                }
            }
            //System.out.println("Diálogos carregados com sucesso.");
            
        } catch (IOException e) {
            System.out.println("Erro: Não consegui ler o ficheiro de diálogos.");
            System.out.println("Detalhe do erro: " + e.getMessage());
        }
    }
}