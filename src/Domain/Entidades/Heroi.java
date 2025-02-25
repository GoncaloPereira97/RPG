package Domain.Entidades;

import Controllers.AtackStrategy.FightStrategy;
import Domain.Items.Armas;
import Domain.Items.ItemHeroi;

import java.util.ArrayList;

public class Heroi extends Entidade{

    private static Heroi instance;

    private FightStrategy classType;

    private ArrayList<Integer> salas;

    private Heroi(String nome, int maxHP, int HP, int strength, int inteligence, int dexterity, Armas primaryWeapon, FightStrategy classType) {
        super(nome, maxHP, HP, strength, inteligence, dexterity, primaryWeapon);
        this.classType = classType;
        this.salas = new ArrayList<>();
    }

    public static Heroi getInstance(String nome, int maxHP, int HP, int strength, int inteligence, int dexterity, Armas primaryWeapon, FightStrategy classType) {
        if (instance == null){
        instance = new Heroi(nome, maxHP, HP, strength, inteligence, dexterity, primaryWeapon, classType);
        }
        return instance;
    }

    public FightStrategy getClassType() {
        return classType;
    }

    /**
     * Exibir detalhes do heroi
     */
    public void exibirDetalhes(){
        System.out.println("Nome: " + this.getNome());
        System.out.println("MaxHP: " + this.maxHP);
        System.out.println("HP atual: " + this.HP);
        System.out.println("Força: " + this.strength);
        System.out.println("Inteligência: " + this.inteligence);
        System.out.println("Destreza: " + this.dexterity);
        System.out.println("Arma: " + this.primaryWeapon.getNome());
        System.out.println("Ouro: " + this.gold);
    }

    /**
     * Adicionar uma sala completa pelo heroi
     * @param sala recebe um numero que identifica a sala completa
     */
    public void addSala(int sala) {
        this.salas.add(sala);
    }

    public ArrayList<Integer> getSalas() {
        return salas;
    }
}
