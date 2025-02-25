package Domain.Entidades;

import Domain.Items.Armas;
import Domain.Items.ArmasLendarias;
import Domain.Items.ItemHeroi;
import Repository.ItemRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Inimigo extends NPC{

    private String classNPC;


    public Inimigo(String tipoNPC, String nome, int maxHP, int HP, int strength, int inteligence, int dexterity, Armas primaryWeapon, String era, String classNPC) {
        super(tipoNPC, nome, maxHP, HP, strength, inteligence, dexterity, primaryWeapon, era);
        this.classNPC = classNPC;
    }

    public String getClassNPC() {
        return classNPC;
    }
}
