package Repository;

import Domain.Items.ItemHeroi;
import Tools.CSVItemReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ItemRepository {

    private ArrayList<ItemHeroi> arrayItemHerois;

    private String filePath = "Files/ItensHeroiRPG.csv";

    public ItemRepository() throws FileNotFoundException {
        CSVItemReader csvItemReader = new CSVItemReader(this.filePath);
        this.arrayItemHerois = csvItemReader.readCSVToRepository();
    }

    /**
     * Metodo para procurar um item especifico pelo nome
     * @param nome recebe o nome do item.
     * @return retorna o item se encontrado ou null se não.
     */
    public ItemHeroi procurarItem(String nome){
        for (ItemHeroi itemHeroi : arrayItemHerois){
            if (itemHeroi.getNome().equalsIgnoreCase(nome)){
                return itemHeroi;
            }
        }
        System.out.println("Item não encontrado");
        return null;
    }

    public ArrayList<ItemHeroi> getArrayItems() {
        return arrayItemHerois;
    }
}
