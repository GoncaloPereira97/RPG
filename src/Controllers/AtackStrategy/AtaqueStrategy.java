package Controllers.AtackStrategy;

import Domain.Entidades.Heroi;
import Domain.Entidades.Inimigo;
import Domain.Items.ItemCombate;
import Domain.Items.ItemHeroi;
import Domain.Items.Pocao;

import java.util.ArrayList;
import java.util.Scanner;

import static Controllers.AtackStrategy.ConsumivelController.usarConsumivelCombate;
import static Controllers.AtackStrategy.ConsumivelController.usarPocao;
import static Controllers.ControllersEntidade.InventarioHeroi.imprimirConsumiveisCombate;
import static Controllers.ControllersEntidade.InventarioHeroi.imprimirPocao;
import static Tools.Impressora.cabecalho;

public class AtaqueStrategy {

    /**
     * Metodo que faz um ataque com um consumivel do inventário do heroi, usando um outro metodo que está na class ConsumivelController
     * @param inimigo recebe o inimigo que vai ser atacado
     * @param heroi recebe o heroi para ser lido o seu inventário
     * @return retorna true se o ataque tiver sido executado ou false se não, para saber se o heroi vai ser atacado pelo inimigo ou não após usar o consumivel
     */
    public static boolean ataqueConsumivel(Inimigo inimigo, Heroi heroi) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> localConsumivel;
        int opcao = 0;
        boolean temConsumiveis=false;
        for (ItemHeroi itemHeroi : heroi.getInventario()){
            if (itemHeroi instanceof ItemCombate){
                temConsumiveis = true;
            }
        }
        if (!temConsumiveis){
            System.out.println("Não tem itens de combate");
            return false;
        } else {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t**** Consumiveis Combate ****");
            System.out.println(cabecalho);
            localConsumivel = imprimirConsumiveisCombate(heroi);
            System.out.println("\n");
            System.out.println("Quer usar algum ou sair? 1.Usar 2.sair ");
            opcao = input.nextInt();
            if (opcao == 1){
                System.out.println("Escolhe um item combate. (1 - " + localConsumivel.size() + ")");
                opcao = input.nextInt();
                opcao--;
                usarConsumivelCombate((ItemCombate) heroi.getInventario().get(localConsumivel.get(opcao)), inimigo);
                heroi.getInventario().remove(heroi.getInventario().get(localConsumivel.get(opcao)));
                System.out.println("\n");
                return true;
            } else if (opcao == 2) {
                return false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        return false;
    }

    /**
     * Metodo para usar uma poção do inventário do heroi
     * @param heroi recebe o heroi para ser lido o seu inventário
     */
    public static void pots(Heroi heroi) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> localConsumivel;
        int opcao = 0;
        boolean temConsumiveis=false;
        for (ItemHeroi itemHeroi : heroi.getInventario()){
            if (itemHeroi instanceof Pocao){
                temConsumiveis = true;
            }
        }
        if (!temConsumiveis){
            System.out.println("Não tem poções");
        } else {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t**** Poções ****");
            System.out.println(cabecalho);
            localConsumivel = imprimirPocao(heroi);
            System.out.println("Quer usar alguma ou sair? 1.Usar 2.sair ");
            opcao = input.nextInt();
            if (opcao == 1){
                System.out.println("\n");
                System.out.println("Escolhe uma poção. (1 - " + localConsumivel.size()+")");
                opcao = input.nextInt();
                opcao--;
                usarPocao((Pocao) heroi.getInventario().get(localConsumivel.get(opcao)), heroi);
                heroi.getInventario().remove(heroi.getInventario().get(localConsumivel.get(opcao)));
                System.out.println("\n");
            } else if (opcao == 2) {
            }

        }

    }
}

