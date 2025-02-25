package Domain;

public class Jogo {

    private String dificuldade;

    private String era;
    public Jogo(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public String getEra() {
        return era;
    }
}
