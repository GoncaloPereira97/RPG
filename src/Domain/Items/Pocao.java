package Domain.Items;

public class Pocao extends Consumivel{

    private int vida;

    private int primaryStat;

    public Pocao(String tipo, String nome, int preco, String era, int vida, int primaryStat) {
        super(tipo, nome, preco, era);
        this.vida = vida;
        this.primaryStat = primaryStat;
    }

    @Override
    public void itemDetails() {
        super.itemDetails();
        System.out.println(String. format("%-10s", "Vida: " + this.vida) + " | Primary Stat: " + this.primaryStat);
    }

    public int getVida() {
        return vida;
    }

    public int getPrimaryStat() {
        return primaryStat;
    }
}
