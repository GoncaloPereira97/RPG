package View;

import Domain.Entidades.Heroi;
import Domain.Entidades.Inimigo;
import Domain.Jogo;
import Repository.NPCRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static Controllers.ControllersEntidade.HeroiController.menu;
import static Controllers.ControllersEntidade.HeroiController.morte;
import static Controllers.ControllersEntidade.SpawnController.*;
import static Tools.Impressora.*;

public class CyberView {

    public CyberView(Heroi heroi, Jogo jogo) throws FileNotFoundException, InterruptedException {

        imprimirTextoLento("Após ingerir o comprimido vermelho o teu herói desmaiou e acordou num local estranho.");
        imprimirTextoLento("Parece que estás numa sala cheia de computadores..");
        imprimirTextoLento("Estes computadores são esquesitos, nunca viste algo assim");
        imprimirTextoLento("Aproximas-te se um para ver melhor o que é que se passa");
        imprimirTextoLento("Começas a ouvir passos na tua direção, alguem vem ai a correr!!");
        imprimirTextoLento("\"ALTO AI! QUEM ÉS TU PARA MEXERES NOS NOSSOS XPTO3000?!?!? VAIS MORRER AQUI!\" ");

        salaPC(heroi,jogo);
    }

    private void salaPC(Heroi heroi, Jogo jogo) throws FileNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        int opcao;
        NPCRepository npcRepository = new NPCRepository();
        ArrayList<Inimigo> inimigos;

        inimigos = spawnInimigos(jogo);
        for (Inimigo inimigoAtual : inimigos) {
            if (heroi.getHP() > 0) {
                heroi.getClassType().atacar(inimigoAtual, heroi);
                if (heroi.getHP() <= 0){
                    switch (morte()) {
                        case 1:
                            System.out.println("Respawn no ultimo checkpoint! 'Sala de PCs'");
                            heroi.setHP();
                            salaPC(heroi, jogo);
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Erro morte.");
                    }
                }
            } else {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Sala de PCs'");
                        heroi.setHP();
                        salaPC(heroi, jogo);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro morte.");
                }

            }
        }
        spawnVendedor(heroi, jogo, npcRepository);
        menu(heroi);

        imprimirTextoLento("Despedes-te do vendedor e reparas que um dos telemoveis dos inimigos que derrotaste tinha a localização de uma base secreta subterranea.");
        imprimirTextoLento("Escolhe o que queres fazer.");
        Thread.sleep(1000);
        do {
            System.out.println("1.Base Secreta  2.Seguir para a rua deserta");
            opcao = input.nextInt();
            if (opcao == 1) {
                baseSecreta(heroi, jogo, npcRepository);
            } else if (opcao == 2) {
                ruaDeserta(heroi, jogo, npcRepository);
            }
        } while (opcao < 1 || opcao > 2);

    }

    private void baseSecreta(Heroi heroi, Jogo jogo, NPCRepository npcRepository) throws FileNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        ArrayList<Inimigo> inimigos;
        int opcao;
        imprimirTextoLento("Chegas-te a base secreta na esperança de descobrir o que aconteceu contigo.");
        imprimirTextoLento("Rapidamente depois de entrares os alarmes começam a tocar!");

        inimigos = spawnInimigos(jogo);
        for (Inimigo inimigoAtual : inimigos) {
            if (heroi.getHP() > 0) {
                heroi.getClassType().atacar(inimigoAtual, heroi);
                if (heroi.getHP() <= 0){
                    switch (morte()) {
                        case 1:
                            System.out.println("Respawn no ultimo checkpoint! 'Base Secreta'");
                            heroi.setHP();
                            baseSecreta(heroi, jogo, npcRepository);
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Erro morte.");
                    }
                }
            } else {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Base Secreta'");
                        heroi.setHP();
                        baseSecreta(heroi, jogo, npcRepository);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro morte.");
                }
            }
        }
        heroi.addSala(1);
        spawnVendedor(heroi, jogo, npcRepository);
        menu(heroi);

        imprimirTextoLento("Após investigares a base secreta, não obtiveste quaisquer informações sobre o que te aconteceu..");



        Thread.sleep(1000);
        if (heroi.getSalas().contains(2)){
            imprimirTextoLento("Decides seguir caminho para o arranha ceus de onde eles recebem as ordens!");
            arranhaCeus(heroi, jogo, npcRepository);
        } else {
            do {
                imprimirTextoLento("Descobriste que estas pessoas são responsaveis por varios atos criminosos e tambem a localização de onde eles recebem as ordens!");
                System.out.println("1.Arranha Ceus  2.Seguir para a rua deserta");
                opcao = input.nextInt();
                if (opcao == 1) {
                    arranhaCeus(heroi, jogo, npcRepository);
                } else if (opcao == 2) {
                    ruaDeserta(heroi, jogo, npcRepository);
                }
            } while (opcao < 1 || opcao > 2);
        }

    }

    private void ruaDeserta(Heroi heroi, Jogo jogo, NPCRepository npcRepository) throws FileNotFoundException, InterruptedException {
        Scanner input = new Scanner(System.in);
        ArrayList<Inimigo> inimigos;
        int opcao;
        imprimirTextoLento("Vais caminhando pela rua deserta, estranhamente deserta mesmo..");
        imprimirTextoLento("Parece que já não passa aqui ninguem à anos.");
        imprimirTextoLento("\"ESTA RUA É DOMINADA PELA NEXCORP!! VAIS TER QUE PAGAR POR INVADIRES O NOSSO TERRENO! \"");

        inimigos = spawnInimigos(jogo);
        for (Inimigo inimigoAtual : inimigos) {
            if (heroi.getHP() > 0) {
                heroi.getClassType().atacar(inimigoAtual, heroi);
                if (heroi.getHP() <= 0){
                    switch (morte()) {
                        case 1:
                            System.out.println("Respawn no ultimo checkpoint! 'Rua Deserta'");
                            heroi.setHP();
                            ruaDeserta(heroi, jogo, npcRepository);
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Erro morte.");
                    }
                }
            } else {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Rua Deserta'");
                        heroi.setHP();
                        ruaDeserta(heroi, jogo, npcRepository);
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

        imprimirTextoLento("Depois de seres atacado por simplesmente andar na rua");



        Thread.sleep(1000);
        if (heroi.getSalas().contains(1)){
            imprimirTextoLento("Decides seguir caminho para o arranha ceus de onde eles recebem as ordens!");
            arranhaCeus(heroi, jogo, npcRepository);
        } else {
            imprimirTextoLento("Descobriste que estas pessoas são responsaveis por varios atos criminosos e tambem a localização de onde eles recebem as ordens!");
            imprimirTextoLento("Decides ir para..");
            do {
                System.out.println("1.Base Secreta  2.Arranha Ceus");
                opcao = input.nextInt();
                if (opcao == 1) {
                    arranhaCeus(heroi, jogo, npcRepository);
                } else if (opcao == 2) {
                    baseSecreta(heroi, jogo, npcRepository);
                }
            } while (opcao < 1 || opcao > 2);
        }

    }

    private void arranhaCeus(Heroi heroi, Jogo jogo, NPCRepository npcRepository) throws FileNotFoundException, InterruptedException {
        spawnVendedorLendario(heroi, jogo);
        imprimirTextoLento("Após receberes uma arma que nem sabes bem para que é que vais precisar, segues caminho para dentro do arranha ceus");
        imprimirTextoLento("Algo está estranho aqui.. Porquê que não está ninguem na entrada?");
        imprimirTextoLento("Vieste aqui com um objetivo, segues para o elevador e vais para o ultimo andar.");
        imprimirTextoLento("\"Estavamos a tua espera vaijante do tempo! Não penses que te vamos deixar chegar ao nosso patrão!\"");
        ArrayList<Inimigo> inimigos = spawnInimigos(jogo);
        for (Inimigo inimigoAtual : inimigos) {
            if (heroi.getHP() > 0) {
                heroi.getClassType().atacar(inimigoAtual, heroi);
                if (heroi.getHP() <= 0){
                    switch (morte()) {
                        case 1:
                            System.out.println("Respawn no ultimo checkpoint! 'Rua Deserta'");
                            heroi.setHP();
                            ruaDeserta(heroi, jogo, npcRepository);
                            break;
                        case 3:
                            return;
                        default:
                            System.out.println("Erro morte.");
                    }
                }
            } else {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Rua Deserta'");
                        heroi.setHP();
                        ruaDeserta(heroi, jogo, npcRepository);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro morte.");
                }
            }
        }



        Inimigo inimigo = spawnBoss(jogo, npcRepository);
        imprimirTextoLento(inimigo.getNome() + ": \"Mas que tipo de monstro és tu! Não vais levar o meu império!\"");
        Thread.sleep(1000);
        if (heroi.getHP() > 0) {
            heroi.getClassType().atacar(inimigo, heroi);
            if (heroi.getHP() <= 0) {
                switch (morte()) {
                    case 1:
                        System.out.println("Respawn no ultimo checkpoint! 'Arranha Ceus'");
                        heroi.setHP();
                        arranhaCeus(heroi, jogo, npcRepository);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Erro Morte");
                }
            }
        }
        imprimirTextoLento(textoVermelho + "\n\nCOMPLETAS-TE O JOGO! ");
        imprimirTextoLento("CREDITOS: " + pararCor);
        imprimirTextoLento(textoAzul + "Gonçalo Pereira" + pararCor);
        return;
    }
}
