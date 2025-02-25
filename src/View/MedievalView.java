package View;

import Domain.Entidades.Heroi;
import Domain.Entidades.Inimigo;
import Domain.Jogo;
import Repository.NPCRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static Controllers.ControllersEntidade.HeroiController.menu;
import static Controllers.ControllersEntidade.HeroiController.morte;
import static Controllers.ControllersEntidade.SpawnController.*;
import static Tools.Impressora.*;

public class MedievalView {
    public MedievalView(Heroi heroi, Jogo jogo) throws InterruptedException, FileNotFoundException {
        imprimirTextoLento("Após ingerir o comprimido azul o teu herói desmaiou e acordou num local estranho.");
        imprimirTextoLento("Estás numa taberna? ");
        imprimirTextoLento("Está tudo tão estranho, será que vim parar a Santa Maria da Feira? ");
        imprimirTextoLento("Mas nem estámos em Agosto para haver viagem medieval?!");
        imprimirTextoLento("Vários sujeitos aproximão-se de ti pelo escanda-lo que estás a causar.");
        imprimirTextoLento("Foste expulso da taberna!");
        imprimirTextoLento("Após olhar ao seu redor percebes que efetivamente estás na era medieval!");
        imprimirTextoLento("Mas que raio de comprimido foste tu tomar? A tua mãe bem te avisou para não aceitares doces de estranhos....");
        imprimirTextoLento("Começas a caminhar pela rua e chegas a uma floresta sinistra!");
        imprimirTextoLento("Quando entras na floresta ficas com uma sensação estranha");
        imprimirTextoLento("É UMA EMBOSCADA!");
        floresta(heroi, jogo);
    }

    private void floresta(Heroi heroi, Jogo jogo) throws InterruptedException, FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int opcao;
        NPCRepository npcRepository = new NPCRepository();
        ArrayList<Inimigo> inimigos;

        inimigos = spawnInimigos(jogo);
        for (Inimigo inimigoAtual : inimigos) {
            if (heroi.getHP() > 0) {
                heroi.getClassType().atacar(inimigoAtual, heroi);

            } else {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Floresta'");
                        heroi.setHP();
                        floresta(heroi, jogo);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro morte.");
                }

            }
        }
        spawnVendedor(heroi, jogo, npcRepository);
        heroi.addSala(1);

        menu(heroi);
        imprimirTextoLento("Despedes-te do vendedor e continuas o teu caminho em direção a uma montanha.");
        imprimirTextoLento("Quando chegas perto da montanha deparas-te com duas opções de caminho.");
        imprimirTextoLento("Um caminho leva-te para uma caverna e o outro para uma torre abandonada no topo da montanha.");
        Thread.sleep(1000);
        do {
            System.out.println("Qual escolhes? 1.Caverna  2.Torre Abandonada");
            opcao = input.nextInt();
            if (opcao == 1) {
                cavernas(heroi, jogo, npcRepository);
            } else if (opcao == 2) {
                torreAbandonada(heroi, jogo, npcRepository);
            }
        } while (opcao < 1 || opcao > 2);

    }

    private void cavernas(Heroi heroi, Jogo jogo, NPCRepository npcRepository) throws FileNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        ArrayList<Inimigo> inimigos;
        String opcao;
        int opcaoInt;
        imprimirTextoLento("Entras na caverna e reparas que está a ser usada como base dos rebeldes que planam destronar o rei!");
        imprimirTextoLento("Detetaram a tua presença! Prepara-te para os derrotar!!");
        Thread.sleep(1000);

        inimigos = spawnInimigos(jogo);
        for (Inimigo inimigoAtual : inimigos) {
            if (heroi.getHP() > 0) {
                heroi.getClassType().atacar(inimigoAtual, heroi);

            } else {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Cavernas'");
                        heroi.setHP();
                        cavernas(heroi, jogo, npcRepository);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro morte.");
                }

            }
        }
        heroi.addSala(2);
        spawnVendedor(heroi, jogo, npcRepository);
        menu(heroi);
        int random = new Random().nextInt(0, 2);
        if (random == 2) {
            do {
                imprimirTextoLento("Continuas o teu caminho e notas um pequeno buraco atrás de uma pedra.");
                System.out.println("Queres entrar? (sim)(nao)");
                opcao = input.next();
                if (opcao.equalsIgnoreCase("sim")) {
                    spawnVendedorLendario(heroi, jogo);
                }
            } while (!(opcao.equalsIgnoreCase("nao") || opcao.equalsIgnoreCase("sim")));
        }
        imprimirTextoLento("Escolhe o proximo caminho.");
        if (heroi.getSalas().contains(3)) {
            castelo(heroi, jogo, npcRepository);
        } else {
            do {
                System.out.println("1.Torre Abandonada  2.Castelo Assombrado");
                opcaoInt = input.nextInt();
                if (opcaoInt == 1) {
                    torreAbandonada(heroi, jogo, npcRepository);
                } else if (opcaoInt == 2) {
                    castelo(heroi, jogo, npcRepository);
                }
            } while (opcaoInt < 1 || opcaoInt > 2);
        }
    }

    private void torreAbandonada(Heroi heroi, Jogo jogo, NPCRepository npcRepository) throws FileNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        ArrayList<Inimigo> inimigos;
        int opcaoInt;
        imprimirTextoLento("Sobes ao topo da montanha onde está a torre abandonada.");
        imprimirTextoLento("Entras na torre mas ao entrar ouves uma conversa em que estavam a falar de ti.");
        imprimirTextoLento("Parece que chamas-te a atenção dos rebeldes na tua batalha na floresta.");
        imprimirTextoLento("É melhor tentares pôr ja um fim á propagação das informações sobre ti!");
        imprimirTextoLento("Entras na torre e preparas-te para os derrotar!");
        Thread.sleep(1000);
        inimigos = spawnInimigos(jogo);
        for (Inimigo inimigoAtual : inimigos) {
            if (heroi.getHP() > 0) {
                heroi.getClassType().atacar(inimigoAtual, heroi);

            } else {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Torre Abandonada'");
                        heroi.setHP();
                        torreAbandonada(heroi, jogo, npcRepository);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro morte.");
                }

            }
        }
        heroi.addSala(3);
        spawnVendedor(heroi, jogo, npcRepository);
        menu(heroi);
        imprimirTextoLento("Escolhe o proximo caminho.");
        if (heroi.getSalas().contains(2)) {
            castelo(heroi, jogo, npcRepository);
        } else {
            do {
                imprimirTextoLento("1.Caverna(recomendado))  2.Castelo Assombrado");
                opcaoInt = input.nextInt();
                if (opcaoInt == 1) {
                    cavernas(heroi, jogo, npcRepository);
                } else if (opcaoInt == 2) {
                    castelo(heroi, jogo, npcRepository);
                }
            } while (opcaoInt < 1 || opcaoInt > 2);
        }
    }

    private void castelo(Heroi heroi, Jogo jogo, NPCRepository npcRepository) throws InterruptedException, FileNotFoundException {
        Inimigo inimigo = spawnBoss(jogo, npcRepository);
        ;
        imprimirTextoLento("Após esta longa jornada num mundo novo, encontras o castelo mais sinistro que alguma vez viste.");
        imprimirTextoLento("Como a tua mãe não educou um cobarde vais até a entrada do castélo");
        imprimirTextoLento("Secalhar educou mesmo um cobarde, porque ficas-te a porta durante 3 horas com medo de ir embora e de entrar......");
        imprimirTextoLento("Finalmente percebes que ou entras... ou entras... Oupa lá pra dentro!");
        imprimirTextoLento("Entras e começas a ouvir um cavalgar ensurdecedor!");

        Thread.sleep(1000);
        if (heroi.getHP() > 0) {
            heroi.getClassType().atacar(inimigo, heroi);
            if (heroi.getHP() <= 0) {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Castelo Assombrado'");
                        heroi.setHP();
                        castelo(heroi, jogo, npcRepository);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro morte.");
                }
            }
        }
        imprimirTextoLento(textoVermelho + " COMPLETAS-TE O JOGO! ");
        imprimirTextoLento("CREDITOS: " + pararCor);
        imprimirTextoLento(textoAzul + "Gonçalo Pereira" + pararCor);
        return;
    }
}
