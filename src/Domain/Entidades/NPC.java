package Domain.Entidades;

import Domain.Items.Armas;
import Domain.Items.ArmasLendarias;
import Domain.Items.ItemHeroi;
import Repository.ItemRepository;

import java.util.ArrayList;
import java.util.Random;

public abstract class NPC extends Entidade{

    private String tipoNPC;

    private String era;

    public NPC(String tipoNPC, String nome, int maxHP, int HP, int strength, int inteligence, int dexterity, Armas primaryWeapon,  String era) {
        super(nome, maxHP, HP, strength, inteligence, dexterity, primaryWeapon);
        this.tipoNPC = tipoNPC;
        this.era = era;
    }

    public String getTipoNPC() {
        return tipoNPC;
    }

    public String getEra() {
        return era;
    }
}
