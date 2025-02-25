package Domain.Entidades;

import Domain.Items.Armas;
import Domain.Items.ItemHeroi;

import java.util.ArrayList;
import java.util.Scanner;

import static Tools.Impressora.*;

public abstract class Entidade {

    protected String nome;

    protected int maxHP;

    protected int HP;

    protected int strength;

    protected int inteligence;

    protected int dexterity;

    protected int level;

    protected int gold;

    protected Armas primaryWeapon;

    protected int xp;

    protected ArrayList<ItemHeroi> inventario;

    public Entidade(String nome, int maxHP, int HP, int strength, int inteligence, int dexterity, int level, int gold, Armas primaryWeapon) {
        this.nome = nome;
        this.maxHP = maxHP;
        this.HP = HP;
        this.strength = strength;
        this.inteligence = inteligence;
        this.dexterity = dexterity;
        this.level = level;
        this.gold = gold;
        this.xp = 0;
        this.primaryWeapon = primaryWeapon;
        this.inventario = new ArrayList<>();
    }

    public Entidade(String nome, int maxHP, int HP, int strength, int inteligence, int dexterity, Armas primaryWeapon) {
        this.nome = nome;
        this.maxHP = maxHP;
        this.HP = HP;
        this.strength = strength;
        this.inteligence = inteligence;
        this.dexterity = dexterity;
        this.level = 1;
        this.gold = 0;
        this.primaryWeapon = primaryWeapon;
        this.xp = 1;
        this.inventario = new ArrayList<>();
    }

    public void setXp() {
        this.xp += 2;
        if (xp >= 5){
            this.level++;
            this.strength += 2;
            this.dexterity += 2;
            this.inteligence += 2;
            this.maxHP += 10;
            this.xp -= 5;
            System.out.println(textoAzul + "Subiu de nivel! As suas stats aumentaram" + pararCor);
        }
    }

   // Inicio Getter e Setter

    public String getNome() {
        String nome = "";
        if (this instanceof Heroi){
            nome = textoVerde + this.nome + pararCor;
        } else if (this instanceof Inimigo){
            nome = textoAmarelo + this.nome + pararCor;
        } else if (this instanceof Vendedor) {
            nome = textoAzul + this.nome + pararCor;
        }
        return nome;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHP() {
        return HP;
    }

    public int getStrength() {
        return strength;
    }

    public int getInteligence() {
        return inteligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public int getXp() {return xp;}
    public Armas getPrimaryWeapon() {
        return primaryWeapon;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }




    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setInteligence(int inteligence) {
        this.inteligence = inteligence;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setPrimaryWeapon(Armas primaryWeapon) {
        this.primaryWeapon = primaryWeapon;
    }


    public ArrayList<ItemHeroi> getInventario() {
        return inventario;
    }

    public void setHP() {
        this.HP = maxHP;
    }

    // Fim Getter e Setter

    /**
     * Imprime todos os itens do inventário da entidade
     */
    public void imprimirInventario(){
        if (!this.getInventario().isEmpty()){
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t**** Inventário ****");
            System.out.println(cabecalho);
            for (int i = 0; i < this.inventario.size(); i++) {
                System.out.print(textoVermelho+ String. format("%-13s", "Item " + (i + 1)  +" ")+pararCor);
                inventario.get(i).itemDetails();
            }
            System.out.println("\n");
        } else {
            System.out.println("Inventário vazio.");
        }

    }

    /**
     * Remover um item especifico pelo seu index.
     * @param item recebe o index do item
     */
    public void removerItem(int item){
        this.inventario.remove(item);
    }

    /**
     * Adicionar um item ao iventário
     * @param item recebe o item
     */
    public void addItemInventario(ItemHeroi item){
        this.inventario.add(item);
    }

    /**
     * Metodo para dar damage a uma entidade
     * @param dmg recebe o dmg a dar
     */
    public void levarDMG(int dmg){
        if (this.HP - dmg <= 0){
            // TODO: 20/12/2023 PROGRAMAR A MORTE!
            this.HP = 0;
            System.out.println(" ");
        } else {
            this.HP -= dmg;
        }
    }

    /**
     * Metodo para dar heal
     * @param heal recebe o heal a dar
     */
    public void receberHeal(int heal) {
        Scanner input = new Scanner(System.in);
        int opcao;
        if (this.HP + heal > this.maxHP) {
            System.out.println("Irá desperdiçar " + textoVerde + ((this.HP + heal) - this.maxHP) + " heal" + pararCor + " quer usar na mesma? 1.sim");
            opcao = input.nextInt();
            if (opcao == 1){
                this.HP = this.maxHP;
                System.out.println("Vida restaurada ao maximo");
            }

        } else {
            this.HP += heal;
        }
    }
}
