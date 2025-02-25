package Controllers.ControllersEntidade;

import Controllers.AtackStrategy.*;
import Domain.Entidades.Heroi;
import Domain.Entidades.NPC;
import Domain.Entidades.Vendedor;
import Domain.Items.Armas;
import Domain.Items.ItemHeroi;
import Repository.NPCRepository;

import java.io.FileNotFoundException;
import java.util.*;

import static Tools.Impressora.*;

public class VendedorController {


    /**
     * Método para criar uma loja com 10 itens do inventário do vendedor, se o vendedor não tiver mais de 10 itens retorna todos
     * @param vendedor recebe o vendedor
     * @return retorna um ArrayList de inteiros com os index dos itens no inventário do vendedor
     */
    public static ArrayList<Integer> criarLoja(Vendedor vendedor) {
        ArrayList<Integer> arrayRandomIndex = new ArrayList<>();
        if (vendedor.getTipoNPC().equalsIgnoreCase("vendedor")){
            if (vendedor.getInventario().size()>=10){
                for (int i = 0; arrayRandomIndex.size() < 10; i++) {
                    int random = new Random().nextInt(0, vendedor.getInventario().size());
                    if (!arrayRandomIndex.contains(random)) {
                        arrayRandomIndex.add(random);
                    } else i--;
                }
            } else {
                for (int i = 0; arrayRandomIndex.size() < vendedor.getInventario().size(); i++) {
                    int random = new Random().nextInt(0, vendedor.getInventario().size());
                    if (!arrayRandomIndex.contains(random)) {
                        arrayRandomIndex.add(random);
                    } else i--;
                }
            }

        } else{
            for (int i = 0; arrayRandomIndex.size() < 3; i++) {
                    arrayRandomIndex.add(i);
            }
        }

        return arrayRandomIndex;
    }

    /**
     * Método para imprimir uma loja com 10 itens do inventário do vendedor e fazer toda a interação com o utilizador para comprar ou vender itens ao vendedor usando o método criarLoja e vender.
     * @param heroi recebe o heroi que vai interagir com o vendedor
     * @param vendedor recebe o vendedor que contem os itens
     */
    public static void imprimirLoja(Heroi heroi, Vendedor vendedor) {
        ArrayList<Integer> loja = criarLoja(vendedor);
        Scanner input = new Scanner(System.in);
        String opcao;
        int itemCompra;
        Collections.sort(loja);
        do {
            System.out.println("Tem " + heroi.getGold() + " gold.");
            System.out.println("O que pretende fazer? (comprar)(vender)(sair)");
            opcao = input.next();
            switch (opcao) {
                case "comprar":
                    System.out.println(cabecalho);
                    for (int i = 0; i < loja.size(); i++) {
                        System.out.print(textoVermelho+ String. format("%-13s", "Item " + (i + 1)  +" ")+pararCor);
                        vendedor.getInventario().get(loja.get(i)).itemDetails();
                    }
                    System.out.println("Qual item deseja comprar? (1 - " + loja.size() + ")");
                    itemCompra = input.nextInt();
                    if (itemCompra>loja.size() || itemCompra < 0){
                        System.out.println("Item não encontrado");
                    } else  {
                        ItemHeroi itemVendido = vendedor.getInventario().get(loja.get(itemCompra - 1));
                        vender(heroi, vendedor,itemVendido);
                        vendedor.getInventario().remove(itemVendido);
                        for (int i = 0; i < loja.size(); i++) {
                            if (loja.get(i) > loja.get(itemCompra - 1)){
                                loja.set(i, loja.get(i) - 1);
                            }
                        }
                        loja.remove(itemCompra - 1);

                        System.out.println("Quer sair da loja? (sim)(nao)");
                        opcao = input.next();
                        if (opcao.equalsIgnoreCase("sim")){
                            opcao = "sair";
                        }
                    }
                    break;
                case "vender":
                    int itemVender;
                    heroi.imprimirInventario();
                    System.out.println("Tem estes itens no inventário, qual deseja vender? (1 - " + heroi.getInventario().size() + ")");
                    itemVender = input.nextInt();
                    if (itemVender > 0 && itemVender <= heroi.getInventario().size()){
                        heroi.setGold(heroi.getGold() + heroi.getInventario().get(itemVender - 1).getPreco());
                        heroi.removerItem(itemVender - 1);
                    } else System.out.println("Opcão inválida.");
                    break;
                case "sair":
                    break;
                default:
                    System.out.println("Opcão inválida.");


            }
        } while (!opcao.equalsIgnoreCase("sair"));
    }

    /**
     * Método para vender itens ao heroi.
     * @param heroi recebe o heroi para verificar o seu ouro, adicionar itens ao inventário e verificar a sua class caso queira equipar o item
     * @param vendedor recebe o vendedor que contem os itens
     * @param itemVender recebe o item a ser vendido
     */
    public static void vender(Heroi heroi, Vendedor vendedor, ItemHeroi itemVender) {
        Scanner input = new Scanner(System.in);
        String opcao;
        String nomeClass = "";

        if (heroi.getClassType() instanceof BruteStrategy){
            nomeClass = "Brute";
        } else if (heroi.getClassType() instanceof BarbarianStrategy){
            nomeClass = "Barbarian";
        }else if (heroi.getClassType() instanceof SoldierStrategy){
            nomeClass = "Soldier";
        }else if (heroi.getClassType() instanceof RangerStrategy){
            nomeClass = "Ranger";
        }else if (heroi.getClassType() instanceof TechieStrategy){
            nomeClass = "Techie";
        }else if (heroi.getClassType() instanceof WizardStrategy){
            nomeClass = "Wizard";
        }

        if (heroi.getGold() > itemVender.getPreco()) {
            if (itemVender instanceof Armas){
                System.out.println("Item comprado com sucesso deseja equipar? (sim)(nao)");
                opcao = input.next();
                if (opcao.equalsIgnoreCase("sim") && itemVender.getHeroisPermitidos().contains(nomeClass)) {
                    heroi.addItemInventario(heroi.getPrimaryWeapon());
                    heroi.setPrimaryWeapon((Armas) itemVender);
                    System.out.println("Arma equipada!");
                    System.out.println("Arma antiga adicionada ao inventário!");
                } else if (opcao.equalsIgnoreCase("sim") && !itemVender.getHeroisPermitidos().contains(nomeClass)){
                    System.out.println("Não tens os poderes necessários para usar esta arma");
                    System.out.println("Arma adicionada ao inventário.");
                    heroi.addItemInventario(itemVender);
                } else {
                    System.out.println("Arma adicionada ao inventário.");
                    heroi.addItemInventario(itemVender);
                }
            } else {
                System.out.println("Item comprado!");
                heroi.addItemInventario(itemVender);
            }
            heroi.setGold(heroi.getGold() - itemVender.getPreco());
            itemVender.setPreco(itemVender.getPreco() / 2);

            vendedor.retirarItem(itemVender);
        } else System.out.println("Não tem dinheiro suficiente.");
        System.out.println("\n");
    }

}
