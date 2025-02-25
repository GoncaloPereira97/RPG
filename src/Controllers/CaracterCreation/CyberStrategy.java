package Controllers.CaracterCreation;

import Controllers.AtackStrategy.*;
import Domain.Entidades.Heroi;
import Domain.Items.Armas;
import Domain.Items.ItemHeroi;
import Domain.Jogo;
import Repository.ItemRepository;

import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;


public class CyberStrategy implements CreationInterface{

    /**
     * Metodo para a criar um heroi, pergunta o nome e a classe do heroi e  preenche todos os outros campos automaticamente.
     * @return Retorna um heroi
     * @throws FileNotFoundException
     */
    @Override
    public Heroi caracterCreator() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ItemRepository itemRepository = new ItemRepository();
        String classHeroi, nome;
        Heroi heroi = null;
        int maxHP,strength,inteligence,dexterity;
        Armas primaryWeapon;

        System.out.println("\t***Caracter Creation***");
        System.out.println("Escolhe o nome do heroi: ");
        nome = input.next();

        do {
            System.out.println("Caracter creation: ");
            System.out.println("Escolhe a classe do teu heroi: (BRUTE)(TECHIE)(SOLDIER)");
            classHeroi = input.next().toLowerCase(Locale.ROOT);
            System.out.println("\n");
            switch (classHeroi){
                case "brute":
                    maxHP = 220;
                    strength = 10;
                    inteligence = 5;
                    dexterity = 5;
                    primaryWeapon = (Armas) itemRepository.procurarItem("Neo Hammer");
                    heroi= Heroi.getInstance(nome, maxHP, maxHP, strength, inteligence, dexterity, primaryWeapon,new BruteStrategy());
                    break;
                case "techie":
                    maxHP = 220;
                    strength = 5;
                    inteligence = 10;
                    dexterity = 5;
                    primaryWeapon = (Armas) itemRepository.procurarItem("Braco Robotico");
                    heroi= Heroi.getInstance(nome, maxHP, maxHP, strength, inteligence, dexterity, primaryWeapon,new TechieStrategy());
                    break;
                case "soldier":
                    maxHP = 220;
                    strength = 5;
                    inteligence = 5;
                    dexterity = 10;
                    primaryWeapon = (Armas) itemRepository.procurarItem("Holger 554");
                    heroi= Heroi.getInstance(nome, maxHP, maxHP, strength, inteligence, dexterity, primaryWeapon,new SoldierStrategy());

                default:
                    System.out.println("Classe inválida.");
            }

        } while (heroi == null);

        return heroi;

    }


}
