package Tools;

import Controllers.AtackStrategy.BruteStrategy;
import Domain.Entidades.Inimigo;
import Domain.Entidades.NPC;
import Domain.Entidades.Vendedor;
import Domain.Items.*;
import Repository.ItemRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVNpcReader {

    private String filePath;

    public CSVNpcReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Metodo para ler todos os npc de um ficheiro csv.
     * @return Retorna um arraylist com todos os npc
     * @throws FileNotFoundException
     */

    public ArrayList<NPC> readCSVToRepository() throws FileNotFoundException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        ItemRepository itemRepository = new ItemRepository();
        ArrayList<NPC> inimigos = new ArrayList<>();
        NPC npc = null;
        String linha = scanner.nextLine();
        Armas arma = null;
        while (scanner.hasNextLine()){
            linha = scanner.nextLine();
            String[] splitLinha = linha.split(";");
            String tipo = splitLinha[0];
            String nome = splitLinha[1];
            int maxHP = Integer.parseInt(splitLinha[2]);
            int HP = Integer.parseInt(splitLinha[3]);
            int strength = Integer.parseInt(splitLinha[4]);
            int inteligence = Integer.parseInt(splitLinha[5]);
            int dexterity = Integer.parseInt(splitLinha[6]);
            String primaryWeapon = splitLinha[7];
            String era = splitLinha[8];

            for (ItemHeroi itemAtual : itemRepository.getArrayItems()) {
                if (itemAtual.getNome().equalsIgnoreCase(primaryWeapon)){
                    arma = (Armas) itemAtual;
                    break;
                }
            }
            if (tipo.contains("inimigo")){
                if (arma.getHeroisPermitidos().contains("Brute")){
                    npc = new Inimigo(tipo, nome, maxHP, HP, strength, inteligence, dexterity, arma,era ,"Brute");
                } else if (arma.getHeroisPermitidos().contains("Barbarian")) {
                    npc = new Inimigo(tipo, nome, maxHP, HP, strength, inteligence, dexterity, arma,era, "Barbarian");
                } else if (arma.getHeroisPermitidos().contains("Ranger")) {
                    npc = new Inimigo(tipo, nome, maxHP, HP, strength, inteligence, dexterity, arma,era, "Ranger");
                } else if (arma.getHeroisPermitidos().contains("Soldier")) {
                    npc = new Inimigo(tipo, nome, maxHP, HP, strength, inteligence, dexterity, arma,era, "Soldier");
                }else if (arma.getHeroisPermitidos().contains("Wizard")) {
                    npc = new Inimigo(tipo, nome, maxHP, HP, strength, inteligence, dexterity, arma,era, "Wizard");
                }else if (arma.getHeroisPermitidos().contains("Techie")) {
                    npc = new Inimigo(tipo, nome, maxHP, HP, strength, inteligence, dexterity, arma,era, "Techie");
                }
                inimigos.add(npc);
            } else if (tipo.contains("vendedor")) {
                npc = new Vendedor(tipo, nome, maxHP, HP, strength, inteligence, dexterity, arma,era);
                inimigos.add(npc);
            }


        }
        return inimigos;
    }

}
