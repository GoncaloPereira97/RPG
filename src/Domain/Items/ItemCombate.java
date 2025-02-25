package Domain.Items;

public class ItemCombate extends Consumivel{

    private int ataqueInstantaneo;

    public ItemCombate(String tipo, String nome, int preco, String era, int ataqueInstantaneo) {
        super(tipo, nome, preco, era);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    @Override
    public void itemDetails() {
        super.itemDetails();
        System.out.println(String. format("%-10s", "Ataque: " + this.ataqueInstantaneo)+" |");
    }

    public int getAtaqueInstantaneo() {
        return ataqueInstantaneo;
    }
}
