package Domain.Entidades;

import Domain.Items.Armas;
import Domain.Items.ArmasLendarias;
import Domain.Items.ItemHeroi;
import Repository.ItemRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Vendedor extends NPC{

    private boolean conhecido;
    public Vendedor(String tipoNPC, String nome, int maxHP, int HP, int strength, int inteligence, int dexterity, Armas primaryWeapon, String era) throws FileNotFoundException {
        super(tipoNPC, nome, maxHP, HP, strength, inteligence, dexterity, primaryWeapon, era);
        this.inventario = inventarioVendedor(tipoNPC);
        this.conhecido=false;
    }

    /**
     * Metodo para colocar todos os itens no inventário do vendedor dependendo da sua era
     * @param tipoNPC recebe uma string com o tipo do npc (vendedor, vendedor lendario)
     * @return retorna o ArrayList de ItemHeroi que vai ser o inventário do vendedor
     * @throws FileNotFoundException
     */
    private ArrayList<ItemHeroi> inventarioVendedor(String tipoNPC) throws FileNotFoundException {
        ArrayList<ItemHeroi> arrayInventario = new ItemRepository().getArrayItems();
        ArrayList<ItemHeroi> inventario = new ArrayList<>();

        if (getEra().equalsIgnoreCase("medieval") && tipoNPC.equalsIgnoreCase("vendedor")){
            for (ItemHeroi itemHeroi : arrayInventario) {
                if (!((itemHeroi instanceof ArmasLendarias) || (itemHeroi.getEra().equalsIgnoreCase("2077")))) {
                    inventario.add(itemHeroi);
                }
            }
        } else if (getEra().equalsIgnoreCase("2077") && tipoNPC.equalsIgnoreCase("vendedor")){
            for (ItemHeroi itemHeroi : arrayInventario) {
                if (!((itemHeroi instanceof ArmasLendarias) || (itemHeroi.getEra().equalsIgnoreCase("medieval")))) {
                    inventario.add(itemHeroi);
                }
            }
        } else if (tipoNPC.equalsIgnoreCase("vendedor lendario") && getEra().equalsIgnoreCase("2077")) {
            for (ItemHeroi itemHeroi : arrayInventario) {
                if (itemHeroi.getTipo().equalsIgnoreCase("Arma Lendaria") && itemHeroi.getEra().equalsIgnoreCase("2077")) {
                    inventario.add(itemHeroi);
                }
            }
        }else if (tipoNPC.equalsIgnoreCase("vendedor lendario") && getEra().equalsIgnoreCase("medieval")){
            for (ItemHeroi itemHeroi : arrayInventario) {
                if (itemHeroi.getTipo().equalsIgnoreCase("Arma Lendaria") && itemHeroi.getEra().equalsIgnoreCase("medieval")) {
                    inventario.add(itemHeroi);
                }
            }
        }
        return inventario;
    }

    public void setConhecido() {
        this.conhecido = true;
    }

    public boolean isConhecido() {
        return conhecido;
    }

    /**
     * Metodo para retirar um item do inventário do vendedor
     * @param item
     */
    public void retirarItem(ItemHeroi item){
        this.inventario.remove(item);
    }
}
