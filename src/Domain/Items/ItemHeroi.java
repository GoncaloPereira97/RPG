package Domain.Items;

import java.util.ArrayList;

public abstract class ItemHeroi {

    private String tipo;
    private String nome;
    private int preco;
    private ArrayList <String> heroisPermitidos;

    private String era;

    public ItemHeroi(String tipo, String nome, int preco, String era) {
        this.tipo = tipo;
        this.nome = nome;
        this.preco = preco;
        this.heroisPermitidos = new ArrayList<>();
        this.era = era;
    }

    public void addHeroiPermitido(String atackStrategy){
        heroisPermitidos.add(atackStrategy);
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public ArrayList<String> getHeroisPermitidos() {
        return heroisPermitidos;
    }

    public String getEra() {
        return era;
    }

    public void itemDetails(){
        System.out.print(String. format("%-20s", this.tipo) + " | " + String. format("%-20s", this.nome) + " | " + String. format("%-6s", this.preco) + " | " + String. format("%-28s", heroisPermitidos) + " | ");
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
