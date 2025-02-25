package Controllers.AtackStrategy;

import Domain.Entidades.Heroi;
import Domain.Entidades.Inimigo;
import Domain.Entidades.NPC;

import java.util.Random;

import static Tools.Impressora.pararCor;
import static Tools.Impressora.textoVermelho;

public class AtaqueNPC {

    /**
     * Função que faz o ataque do npc dependendo da FightStrategy do npc e do heroi
     * @param npc recebe o npc que vai fazer o ataque
     * @param heroi recebe o heroi que vai atacar
     * @throws InterruptedException
     */
    public static void ataqueNPC(Inimigo npc, Heroi heroi) throws InterruptedException {
      String classNPC = npc.getClassNPC().toLowerCase();
      int damage = 0;
      int random = new Random().nextInt(0,15);
        switch (classNPC){
            case "brute":
                damage = npc.getPrimaryWeapon().getAtaque() + npc.getStrength();
                if (heroi.getClassType() instanceof SoldierStrategy || heroi.getClassType() instanceof RangerStrategy){
                    damage = (int) (damage + (damage * 0.1));
                }
                if (random == 1){
                    damage = (npc.getPrimaryWeapon().getAtaqueEspecial() * 2) + npc.getStrength();
                    System.out.println(npc.getNome() + " prepara um ataque especial!!");
                    System.out.println("Ativa o modo turbo do seu " + npc.getPrimaryWeapon().getNome() + "!");
                    System.out.println("Ergue o martelo e bate com ele no chão com maxima força causando uma onda de choque!");
                } else {
                    System.out.println(npc.getNome() + " da swing com o " + npc.getPrimaryWeapon().getNome() + "!");
                }
                System.out.println(textoVermelho + "damage: " + damage + pararCor);
                heroi.levarDMG(damage);
                Thread.sleep(1000);
                break;

            case "techie":
                damage = npc.getPrimaryWeapon().getAtaque() + npc.getInteligence();
                if (heroi.getClassType() instanceof SoldierStrategy || heroi.getClassType() instanceof RangerStrategy){
                    damage = (int) (damage + (damage * 0.1));
                }
                if (random == 1){
                    damage =  npc.getPrimaryWeapon().getAtaqueEspecial() + npc.getInteligence();
                    System.out.println(npc.getNome() + " prepara um ataque especial!!");
                    System.out.println("Dispara  " + npc.getPrimaryWeapon().getNome() + " usando como um missil!");
                } else {
                    System.out.println(npc.getNome() + " executa um uppercut intenso com " + npc.getPrimaryWeapon().getNome() + "!");
                }
                System.out.println(textoVermelho + "damage: " + damage + pararCor);
                heroi.levarDMG(damage);
                Thread.sleep(1000);
                break;

            case "soldier":
                damage = npc.getPrimaryWeapon().getAtaque() + npc.getDexterity();
                if (heroi.getClassType() instanceof SoldierStrategy || heroi.getClassType() instanceof RangerStrategy){
                    damage = (int) (damage + (damage * 0.1));
                }
                if (random == 1){
                    damage =  npc.getPrimaryWeapon().getAtaqueEspecial() + npc.getDexterity();
                    System.out.println(npc.getNome() + " prepara um ataque especial!!");
                    System.out.println("A " + npc.getPrimaryWeapon().getNome() +" ganha uma luz intensa e começa a disparar balas explosivas!");
                } else {
                    System.out.println(npc.getNome() + " Faz mira e descarrega um carregador mas apenas algumas balas te acertam!");
                }
                System.out.println(textoVermelho + "damage: " + damage + pararCor);
                heroi.levarDMG(damage);
                Thread.sleep(1000);
                break;

            case "barbarian":
                damage = npc.getPrimaryWeapon().getAtaque() + npc.getStrength();
                if (heroi.getClassType() instanceof SoldierStrategy || heroi.getClassType() instanceof RangerStrategy){
                    damage = (int) (damage + (damage * 0.1));
                }
                if (random == 1){
                    damage = npc.getPrimaryWeapon().getAtaqueEspecial() + npc.getStrength();
                    System.out.println(npc.getNome() + " prepara um ataque especial!!");
                    System.out.println("Entra em frenzy e dá umá serie de ataques consecutivos!");
                    for (int i = 0; i < 4; i++) {
                        System.out.println(textoVermelho + "damage: " + damage + pararCor);
                        Thread.sleep(1000);
                    }
                    damage *=4;
                } else {
                    System.out.println(npc.getNome() + " da swing com " + npc.getPrimaryWeapon().getNome() + "!");
                    System.out.println(textoVermelho + "damage: " + damage + pararCor);
                }
                heroi.levarDMG(damage);
                Thread.sleep(1000);
                break;

            case "wizard":
                damage = npc.getPrimaryWeapon().getAtaque() + npc.getInteligence();
                if (heroi.getClassType() instanceof SoldierStrategy || heroi.getClassType() instanceof RangerStrategy){
                    damage = (int) (damage + (damage * 0.1));
                }
                if (random == 1){
                    damage = npc.getPrimaryWeapon().getAtaqueEspecial() + npc.getInteligence();
                    System.out.println(npc.getNome() + " prepara um ataque especial!!");
                    System.out.println("Aponta " + npc.getPrimaryWeapon().getNome() + " para o ceu e um raio cai em ti!");
                } else {
                    System.out.println(npc.getNome() + " aponta o seu bastão e lança um raio!");
                }
                System.out.println(textoVermelho + "damage: " + damage + pararCor);
                heroi.levarDMG(damage);
                Thread.sleep(1000);
                break;
            case "ranger":
                damage = npc.getPrimaryWeapon().getAtaque() + npc.getDexterity();
                if (heroi.getClassType() instanceof SoldierStrategy || heroi.getClassType() instanceof RangerStrategy){
                    damage = (int) (damage + (damage * 0.1));
                }
                if (random == 1){
                    damage = npc.getPrimaryWeapon().getAtaqueEspecial() + npc.getDexterity();
                    System.out.println(npc.getNome() + " prepara um ataque especial!!");
                    System.out.println("Vai lançar 3 flechas de uma vez!");
                } else {
                    System.out.println(npc.getNome() + " faz a sua flecha pegar fogo e dispara em ti!");
                }
                System.out.println(textoVermelho + "damage: " + damage + pararCor);
                heroi.levarDMG(damage);
                Thread.sleep(1000);
                break;

            default:
                System.out.println("ERRO NA CLASS DO NPC");
        }
    }

    /**
     * Metodo para reduzir o primary stat do inimigo quando o heroi é da FightStrategy Brute ou Barbarian
     * @param npc recebe o npc que vai perder os stats
     */

    public static void diminuirStat(Inimigo npc){
       String classNPC = npc.getClassNPC().toLowerCase();
        switch (npc.getClassNPC()){
            case "brute":
            case "barbarian":
                npc.setStrength((int) (npc.getStrength() * 0.80));
                break;
            case "techie":
            case "wizard":
                npc.setInteligence((int) (npc.getInteligence() * 0.80));
                break;
            case "ranger":
            case "soldier":
                npc.setDexterity((int)(npc.getDexterity() * 0.80));
                break;
        }
    }
}
