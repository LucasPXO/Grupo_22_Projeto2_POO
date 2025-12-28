import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Classe responsável por ler ficheiros de texto e configurar os diálogos dos NPCs.
 * Suporta diálogos simples (uma linha) e diálogos interativos (árvores de escolha).
 */
public class CarregadorDialogos {
    
    /**
     * Método principal de carregamento. Lê o ficheiro linha a linha.
     * @param nomeFicheiro Caminho para o ficheiro .txt com os diálogos.
     * @param npcs Lista de NPCs existentes no jogo para lhes atribuir as falas.
     */
    public void carregar(String nomeFicheiro, List<NPC> npcs) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFicheiro))) {
            String linha;
            
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                
                // Ignora linhas vazias ou comentários iniciados por '#'
                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }
                
                // Identifica se é o início de um bloco interativo ou um diálogo simples
                if (linha.equals("[DIALOGO_INTERATIVO]")) {
                    carregarDialogoInterativo(br, npcs);
                } else if (linha.contains(":")) {
                    carregarDialogoSimples(linha, npcs);
                }
            }
            
            System.out.println("Diálogos carregados com sucesso!");
            
        } catch (IOException e) {
            System.err.println("Erro ao carregar diálogos: " + e.getMessage());
        }
    }
    
    /**
     * Processa diálogos simples no formato "NomeNPC: Fala".
     */
    private void carregarDialogoSimples(String linha, List<NPC> npcs) {
        String[] partes = linha.split(":", 2);
        if (partes.length == 2) {
            String nomeNPC = partes[0].trim();
            String fala = partes[1].trim();
            
            // Procura o NPC na lista e atribui a fala correspondente
            for (NPC npc : npcs) {
                if (npc.getNome().equalsIgnoreCase(nomeNPC)) {
                    npc.setFala(fala);
                    break;
                }
            }
        }
    }
    
    /**
     * Processa um bloco de diálogo complexo até encontrar a etiqueta [FIM_DIALOGO].
     */
    private void carregarDialogoInterativo(BufferedReader br, List<NPC> npcs) throws IOException {
        String nomeNPC = null;
        String falaInicial = null;
        String despedida = null;
        NoDialogo dialogo = null;
        String linha;
        
        while ((linha = br.readLine()) != null) {
            linha = linha.trim();
            
            // Critério de paragem para o bloco interativo
            if (linha.equals("[FIM_DIALOGO]")) {
                break;
            }
            
            if (linha.isEmpty()) {
                continue;
            }
            
            // Identifica as tags específicas do bloco interativo
            if (linha.startsWith("NPC:")) {
                nomeNPC = linha.substring(4).trim();
                
            } else if (linha.startsWith("FALA_INICIAL:")) {
                falaInicial = linha.substring(13).trim();
                dialogo = new NoDialogo(falaInicial);
                
            } else if (linha.startsWith("DESPEDIDA:")) {
                despedida = linha.substring(10).trim();
                if (dialogo != null) {
                    dialogo.setMensagemDespedida(despedida);
                }
                
            } else if (linha.startsWith("OPCAO:")) {
                if (dialogo != null) {
                    // Transforma a linha da opção num objeto OpcaoDialogo
                    OpcaoDialogo opcao = parseOpcao(linha.substring(6).trim());
                    if (opcao != null) {
                        dialogo.adicionarOpcao(opcao);
                    }
                }
            }
        }
        
        // Após ler o bloco, associa o objeto NoDialogo ao NPC correto
        if (nomeNPC != null && dialogo != null) {
            for (NPC npc : npcs) {
                if (npc.getNome().equalsIgnoreCase(nomeNPC)) {
                    npc.setDialogo(dialogo);
                    break;
                }
            }
        }
    }
    
    /**
     * Transforma uma linha de opção no formato "Pergunta | Resposta | PISTA:Nome:Conteudo".
     * @return Um objeto OpcaoDialogo configurado.
     */
    private OpcaoDialogo parseOpcao(String linha) {
        // Divide a linha pelo caractere '|'
        String[] partes = linha.split("\\|");
        
        if (partes.length < 2) {
            return null;
        }
        
        String pergunta = partes[0].trim();
        String resposta = partes[1].trim();
        
        // Verifica se existe uma pista associada nesta opção (terceira parte)
        if (partes.length == 3 && partes[2].trim().startsWith("PISTA:")) {
            String dadosPista = partes[2].trim().substring(6);
            String[] pistaPartes = dadosPista.split(":", 2);
            
            if (pistaPartes.length == 2) {
                String nomePista = pistaPartes[0].trim();
                String conteudoPista = pistaPartes[1].trim();
                Pista pista = new Pista(nomePista, conteudoPista);
                return new OpcaoDialogo(pergunta, resposta, pista);
            }
        }
        
        // Se não houver pista, cria uma opção de diálogo normal
        return new OpcaoDialogo(pergunta, resposta);
    }
}