package Tools;

import Domain.Items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVItemReader {

    private String filePath;

    public CSVItemReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Metodo para ler todos os itens de um ficheiro csv.
     * @return Retorna um arraylist com todos os itens
     * @throws FileNotFoundException
     */
    public ArrayList<ItemHeroi> readCSVToRepository() throws FileNotFoundException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        ItemHeroi currentItemHeroi = null;
        ArrayList<ItemHeroi> arrayItemHerois = new ArrayList<>();
        String linha = scanner.nextLine();
        while (scanner.hasNextLine()){
            linha = scanner.nextLine();
            String[] splitLinha = linha.split(";");
            String tipo = splitLinha[0];
            String nome = splitLinha[1];
            int preco = Integer.parseInt(splitLinha[2]);
            String heroisPermitidos = splitLinha[3].replace("[", "");
            heroisPermitidos =  heroisPermitidos.replace("]", "");
            String[] splitHeroisPermitidos = heroisPermitidos.split(",");
            int ataque = Integer.parseInt(splitLinha[4]);
            int ataqueEspecial = Integer.parseInt(splitLinha[5]);
            int ataqueInstantaneo = Integer.parseInt(splitLinha[6]);
            int vida = Integer.parseInt(splitLinha[7]);
            int primaryStat = Integer.parseInt(splitLinha[8]);
            String era = splitLinha[9];

            if (tipo.equalsIgnoreCase("Arma Principal")){
                currentItemHeroi = new Armas(tipo, nome, preco,era, ataque, ataqueEspecial,vida, primaryStat);
            } else if (tipo.equalsIgnoreCase(("Consumivel Combate"))) {
                currentItemHeroi = new ItemCombate(tipo, nome, preco,era, ataqueInstantaneo);
            } else if (tipo.equalsIgnoreCase("Pocao")) {
                currentItemHeroi = new Pocao(tipo, nome, preco,era,vida,primaryStat);
            } else if (tipo.equalsIgnoreCase("Arma Lendaria")) {
                currentItemHeroi = new ArmasLendarias(tipo, nome, preco,era, ataque, ataqueEspecial,vida, primaryStat);
            }

            for (String heroiAtual: splitHeroisPermitidos) {
                currentItemHeroi.addHeroiPermitido(heroiAtual);
            }
            arrayItemHerois.add(currentItemHeroi);
        }
        return arrayItemHerois;
    }
}
