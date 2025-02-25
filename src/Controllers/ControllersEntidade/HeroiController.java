package Controllers.ControllersEntidade;

import Controllers.AtackStrategy.*;
import Domain.Entidades.Heroi;
import Domain.Jogo;
import View.StarterView;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static Controllers.AtackStrategy.AtaqueStrategy.pots;
import static Tools.Impressora.imprimirTextoLento;

public class HeroiController {

    /**
     * metodo para o utilizador distribuir pontos entre vida e força após criar o seu personagem e escolher a dificuldade do jogo.
     * @param jogo Recebe  o jogo para saber a dificuldade
     * @param heroi recebe o heroi que vai receber os pontos
     */
    public static void distribuirPontos(Jogo jogo, Heroi heroi) {
        Scanner input = new Scanner(System.in);
        int opcao, pontos;

        if (jogo.getDificuldade().equalsIgnoreCase("facil")) {
            pontos = 10;
            heroi.setGold(50);
            do {
                System.out.println("Tem " + pontos + " pontos para distribuir entre vida e primary stat.");
                System.out.println("Escolha em qual quer colocar pontos");
                System.out.println("1. Vida.");
                System.out.println("2. Primary Stat.");
                opcao = input.nextInt();
                System.out.println("\n");
                if (opcao == 1) {
                    System.out.println("Quantos pontos deseja atribuir a vida? ");
                    opcao = input.nextInt();
                    if (opcao <= pontos && opcao > 0) {
                        System.out.println("Pontos atribuidos com sucesso.");
                        heroi.setMaxHP(heroi.getMaxHP() + opcao);
                        heroi.setHP();
                        pontos -= opcao;
                    } else {
                        System.out.println("Opcao inválida.");
                    }
                } else if (opcao == 2) {
                    System.out.println("Quantos pontos deseja atribuir a Primary Stat? ");
                    opcao = input.nextInt();
                    if (opcao <= pontos && opcao > 0) {
                        if (heroi.getClassType() instanceof BruteStrategy || heroi.getClassType() instanceof BarbarianStrategy) {
                            heroi.setStrength(heroi.getStrength() + (opcao * 5));
                            pontos -= opcao;
                        } else if (heroi.getClassType() instanceof RangerStrategy || heroi.getClassType() instanceof SoldierStrategy) {
                            heroi.setDexterity(heroi.getStrength() + (opcao * 5));
                            pontos -= opcao;
                        } else if (heroi.getClassType() instanceof WizardStrategy || heroi.getClassType() instanceof TechieStrategy) {
                            heroi.setInteligence(heroi.getStrength() + (opcao * 5));
                            pontos -= opcao;
                        }
                    } else System.out.println("Opção inválida.");
                } else System.out.println("Opção inválida.");
                System.out.println("\n");
            } while (pontos > 0);

        } else if (jogo.getDificuldade().equalsIgnoreCase("dificil")) {
            pontos = 3;
            heroi.setGold(15);
            do {
                System.out.println("Tem " + pontos + " pontos para distribuir entre vida e primary stat.");
                System.out.println("Escolha em qual quer colocar pontos");
                System.out.println("1. Vida.");
                System.out.println("2. Primary Stat.");
                opcao = input.nextInt();
                System.out.println("\n");
                if (opcao == 1) {
                    System.out.println("Quantos pontos deseja atribuir a vida? ");
                    opcao = input.nextInt();
                    if (opcao <= pontos && opcao > 0) {
                        System.out.println("Pontos atribuidos com sucesso.");
                        heroi.setMaxHP(heroi.getMaxHP() + opcao);
                        heroi.setHP();
                        pontos -= opcao;
                    } else {
                        System.out.println("Opcao inválida.\n");
                    }
                } else if (opcao == 2) {
                    System.out.println("Quantos pontos deseja atribuir a Primary Stat? ");
                    opcao = input.nextInt();
                    if (opcao <= pontos && opcao > 0) {
                        if (heroi.getClassType() instanceof BruteStrategy || heroi.getClassType() instanceof BarbarianStrategy) {
                            heroi.setStrength(heroi.getStrength() + (opcao * 5));
                            pontos -= opcao;
                        } else if (heroi.getClassType() instanceof RangerStrategy || heroi.getClassType() instanceof SoldierStrategy) {
                            heroi.setDexterity(heroi.getDexterity() + (opcao * 5));
                            pontos -= opcao;
                        } else if (heroi.getClassType() instanceof WizardStrategy || heroi.getClassType() instanceof TechieStrategy) {
                            heroi.setInteligence(heroi.getInteligence() + (opcao * 5));
                            pontos -= opcao;
                        }
                    } else System.out.println("Opção inválida.");
                } else System.out.println("Opção inválida.");
                System.out.println("\n");
            } while (pontos > 0);

        }
    }

    /**
     * Menu de heroi para ser usado entre salas
     * @param heroi recebe o heroi
     */
    public static void menu(Heroi heroi){
        Scanner input = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\t\t***Menu***");
            System.out.println("1. Continuar");
            System.out.println("2. Poções");
            System.out.println("3. Ver detalhes do personagem");
            System.out.println("4. Inventário");
            opcao= input.nextInt();
            System.out.println("\n");

            switch (opcao){
                case 1:
                    break;
                case 2:
                    pots(heroi);
                    System.out.println("\n");
                    break;
                case 3:
                    heroi.exibirDetalhes();
                    System.out.println("1. Sair do menu | Qualquer outro numero para continuar.");
                    opcao= input.nextInt();
                    System.out.println("\n");
                    break;
                case 4:
                    heroi.imprimirInventario();
                    System.out.println("1. Sair do menu | Qualquer outro numero para continuar.");
                    opcao= input.nextInt();
                    System.out.println("\n");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 1);
    }

    /**
     * Menu de morte do heroi.
     * @return retorna a escolha do utilizador.
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public static int morte() throws FileNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        int opcao;
        imprimirTextoLento("Morreste, paciencia... Escolhe o que queres fazer");
        do {
            System.out.println("1.Continuar no ultimo checkpoint. 2.Criar novo personagem 3.Fechar programa");
            opcao = input.nextInt();

            if (opcao == 1){
                return 1;
            } else if (opcao == 2){
                StarterView.starterMenu();
            } else if (opcao == 3) {
                return 3;
            }
        } while (opcao <1 || opcao > 3);
        return 3;
    }
}

