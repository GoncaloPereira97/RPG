package Controllers.ControllersEntidade;

import Domain.Entidades.Heroi;
import Domain.Items.ItemCombate;
import Domain.Items.ItemHeroi;
import Domain.Items.Pocao;

import java.util.ArrayList;

import static Tools.Impressora.pararCor;
import static Tools.Impressora.textoVermelho;

public class InventarioHeroi {

    /**
     * metodo para imprimir os consumiveis de combate do heroi.
     * @param heroi recebe o heroi
     * @return retorna um ArrayList de inteiros que são os index dos itens no inventário
     */
    public static ArrayList<Integer> imprimirConsumiveisCombate(Heroi heroi){
        int i = 1;
        ArrayList<Integer> localConsumivel = new ArrayList<>();
        for (ItemHeroi itemAtual : heroi.getInventario()) {
            if (itemAtual instanceof ItemCombate){
                localConsumivel.add(heroi.getInventario().indexOf(itemAtual));
                System.out.print(textoVermelho+ String. format("%-13s", "Item " + i++  +" ")+pararCor);
                itemAtual.itemDetails();
            }
        }
        return localConsumivel;
    }

    /**
     * metodo para imprimir as poções do heroi.
     * @param heroi recebe o heroi
     * @return retorna um ArrayList de inteiros que são os index dos itens no inventário
     */

    public static ArrayList<Integer> imprimirPocao(Heroi heroi){
        int i = 1;
        ArrayList<Integer> localConsumivel = new ArrayList<>();
        for (ItemHeroi itemAtual : heroi.getInventario()) {
            if (itemAtual instanceof Pocao){
                localConsumivel.add(heroi.getInventario().indexOf(itemAtual));
                System.out.print(textoVermelho+ String. format("%-13s", "Item " + i++  +" ")+pararCor);
                itemAtual.itemDetails();
            }
        }
        System.out.println("\n");
        return localConsumivel;
    }
}
