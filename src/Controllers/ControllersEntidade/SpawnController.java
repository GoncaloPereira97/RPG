package Controllers.ControllersEntidade;

import Domain.Entidades.Heroi;
import Domain.Entidades.Inimigo;
import Domain.Entidades.NPC;
import Domain.Entidades.Vendedor;
import Domain.Jogo;
import Repository.NPCRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import static Controllers.ControllersEntidade.VendedorController.imprimirLoja;
import static Tools.Impressora.imprimirTextoLento;
import static Tools.Impressora.pararCor;

public class SpawnController {

    /**
     * Metodo para controlar o spawn de inimigos, pela era do jogo e pela dificuldade.
     * @param jogo recebe o jogo para ver a dificuldade e a era
     * @return Retorna o arraylist de inimigos para spawnar
     * @throws FileNotFoundException
     */
    public static ArrayList<Inimigo> spawnInimigos(Jogo jogo) throws FileNotFoundException {
        NPCRepository npcRepository = new NPCRepository();
        ArrayList<Inimigo> inimigos = new ArrayList<>();
        int randomQuantidade, random;
        switch (jogo.getEra()){
            case "medieval":
                if (jogo.getDificuldade().equalsIgnoreCase("facil")){
                    randomQuantidade = new Random().nextInt(1, 3);
                } else {
                    randomQuantidade = new Random().nextInt(3, 5);
                }
                for (int i = 0; i < randomQuantidade; i++) {
                    random = new Random().nextInt(2, npcRepository.getArrayNPC().size());
                    if (npcRepository.getArrayNPC().get(random).getEra().equalsIgnoreCase("medieval") && npcRepository.getArrayNPC().get(random).getTipoNPC().equalsIgnoreCase("inimigo")){
                        inimigos.add((Inimigo) npcRepository.getArrayNPC().get(random));
                    } else i--;
                }
                break;
            case "2077":
                if (jogo.getDificuldade().equalsIgnoreCase("facil")){
                    randomQuantidade = new Random().nextInt(1, 3);
                } else {
                    randomQuantidade = new Random().nextInt(3, 5);
                }
                for (int i = 0; i < randomQuantidade; i++) {
                    random = new Random().nextInt(2, npcRepository.getArrayNPC().size());
                    if (npcRepository.getArrayNPC().get(random).getEra().equalsIgnoreCase("2077") && npcRepository.getArrayNPC().get(random).getTipoNPC().equalsIgnoreCase("inimigo")){
                        inimigos.add((Inimigo) npcRepository.getArrayNPC().get(random));
                    } else i--;
                }
                break;
            default:
        }
        return inimigos;
    }

    /**
     * Método para imprimir a loja de um vendedor normal random usando um metodo da class VendedorController
     * @param heroi recebe o heroi para usar no método imprimirloja da class VendedorController
     * @param jogo recebe o jogo para ver a era
     * @param npcRepository recebe o repositório de npc para ser possivel avaliar se o heroi já conheceu o vendedor
     * @throws FileNotFoundException
     */
    public static void spawnVendedor(Heroi heroi, Jogo jogo, NPCRepository npcRepository) throws FileNotFoundException {
        Vendedor vendedor = null;
        int random;
        for (int i = 0; i < 1; i++) {
            random = new Random().nextInt(0,npcRepository.getArrayNPC().size());
            if (npcRepository.getArrayNPC().get(random) instanceof Vendedor && npcRepository.getArrayNPC().get(random).getEra().equalsIgnoreCase(jogo.getEra()) && npcRepository.getArrayNPC().get(random).getTipoNPC().equalsIgnoreCase("vendedor")){
                vendedor = (Vendedor) npcRepository.getArrayNPC().get(random);
                break;
            } else {
                i--;
            }
        }
        if (!vendedor.isConhecido()){
                    imprimirTextoLento("Aproxima-se um desconhecido!");
                    imprimirTextoLento("Boa tarde MORRERAM TODOS! Vi o que fizeste aos bandidos! O meu nome é " + vendedor.getNome() + " tenho algumas coisas que talvez te interessem!");
            vendedor.setConhecido();
        } else {
            imprimirTextoLento("Após a batalha vez o " + vendedor.getNome());
            imprimirTextoLento("Ola outra vez! " + heroi.getNome() + " agora já deves ter mais dinheiro, dá uma espreitadela aqui na minha loja.");
        }

        imprimirLoja(heroi, vendedor);
    }

    /**
     * Metodo para spawnar um Boss, dependendo da era do jogo.
     * @param jogo recebe o jogo para ver a era
     * @param npcRepository recebe o repositório de npc
     * @return retorna um inimigo boss
     */

    public static Inimigo spawnBoss(Jogo jogo, NPCRepository npcRepository){
        Inimigo inimigo = null;
        for (NPC npc : npcRepository.getArrayNPC()){
            if (npc.getEra().equalsIgnoreCase(jogo.getEra()) && npc.getTipoNPC().equalsIgnoreCase("inimigo Boss")){
                inimigo = (Inimigo) npc;
            }
        }
        return inimigo;
    }

    /**
     * Método para spawnar um vendedor lendário, dependendo da era.
     * @param heroi recebe o heroi para usar no método imprimirloja da class VendedorController
     * @param jogo recebe o jogo para ver a era
     * @throws FileNotFoundException
     */
    public static void spawnVendedorLendario(Heroi heroi, Jogo jogo) throws FileNotFoundException {
        NPCRepository npcRepository = new NPCRepository();
        Vendedor vendedor = null;
        for (NPC npc : npcRepository.getArrayNPC()){
            if (npc instanceof Vendedor && npc.getEra().equalsIgnoreCase(jogo.getEra()) && npc.getTipoNPC().equalsIgnoreCase("vendedor lendario")){
                vendedor = (Vendedor) npc;
            }
        }
        if (vendedor.getEra().equalsIgnoreCase("medieval")){
            imprimirTextoLento("Ola homem mais procurado do reino! Sou o +" + vendedor.getNome() +  " sei que não me conheces mas tenho aqui umas prendinhas boas para ti!");
        } else {
            imprimirTextoLento("Hei! Tu que anda a chacinar toda a NexCorp! Sou o " + vendedor.getNome() + " vim aqui para te encontrar, sei que não sabes quem eu sou mas quando tinha a tua idade era professor de malucos como tu!");
            imprimirTextoLento("Sei que vais acabar a tentar matá-lo, escolhe uma das armas que a minha melhor turma de software development desenhou para te ajudar!");
        }

        imprimirLoja(heroi, vendedor);
    }
}
