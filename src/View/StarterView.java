package View;

import Controllers.CaracterCreation.CyberStrategy;
import Controllers.CaracterCreation.MedievalStrategy;
import Domain.Entidades.Heroi;
import Domain.Jogo;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static Controllers.ControllersEntidade.HeroiController.distribuirPontos;
import static Tools.Impressora.*;

public class StarterView {
    public static void starterMenu() throws InterruptedException, FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("||        ||||||||  |||||||  ||||||        ||||||||  ||||||||      ||||||||  ||    ||  ||||||||");
        System.out.println("||        ||    ||  ||  ||   ||    ||      ||    ||  ||               ||     ||    ||  ||");
        System.out.println("||        ||    ||  ||||     ||    ||      ||    ||  |||||            ||     ||||||||  ||||");
        System.out.println("||        ||    ||  ||  ||   ||    ||      ||    ||  ||               ||     ||    ||  ||");
        System.out.println("||||||||  ||||||||  ||   ||  ||||||        ||||||||  ||               ||     ||    ||  ||||||||");

        System.out.println("\n");

        System.out.println("||||||||  ||||||||  ||    ||  ||||||||  ||||||||      ||||||||  |||||||| ||||||||  ||||||||");
        System.out.println("||  ||      ||||    ||||  ||  ||        ||                  ||  ||    ||       ||        ||");
        System.out.println("||||        ||||    ||  ||||  ||  ||||  ||||||||      ||||||||  ||    ||       ||        ||");
        System.out.println("||  ||      ||||    ||    ||  ||    ||        ||      ||        ||    ||       ||        ||");
        System.out.println("||   ||   ||||||||  ||    ||  ||||||||  ||||||||      ||||||||  ||||||||       ||        ||");


        Heroi heroi = null;
        String pill = "";
        System.out.println("\t\t\t\t\t\tBem Vindo ao Lord of The Rings 2077\n");
        Jogo jogo = null;
        do {
            System.out.println("Escolha em que dificuldade pretende jogar. (facil)(dificil)");
            pill = input.next().toLowerCase();
            System.out.println("\n");

            if (pill.equalsIgnoreCase("facil") || pill.equalsIgnoreCase("dificil")){
                jogo = new Jogo(pill);
            } else {
                System.out.println("Opção inválida.");
            }

        } while (!((pill.equalsIgnoreCase("facil")) || (pill.equalsIgnoreCase("dificil"))));

        do {
            System.out.println("Escolhe um comprimido: ("+textoVermelho+"RED"+pararCor+")("+textoAzul+"BLUE"+pararCor+")");
            pill = input.next().toLowerCase();
            System.out.println("\n");

            switch (pill){
                case "red":
                    jogo.setEra("2077");
                    CyberStrategy caracterCreatorC = new CyberStrategy();
                    heroi=caracterCreatorC.caracterCreator();
                    distribuirPontos(jogo,heroi);
                    CyberView cyberView = new CyberView(heroi, jogo);
                    return;
                case "blue":
                    jogo.setEra("medieval");
                    MedievalStrategy caracterCreatorM = new MedievalStrategy();
                    heroi=caracterCreatorM.caracterCreator();
                    distribuirPontos(jogo,heroi);
                    MedievalView medievalView = new MedievalView(heroi, jogo);
                    return;
                default:
                    System.out.println("Escolha inválida");
            }
        }while (!(pill.equalsIgnoreCase("red") || pill.equalsIgnoreCase("blue")));
    }
}
