package Controllers.AtackStrategy;

import Domain.Entidades.Heroi;
import Domain.Entidades.Inimigo;
import Domain.Entidades.NPC;

public interface FightStrategy {
    void atacar(Inimigo inimigo, Heroi heroi) throws InterruptedException;

    void ataqueNormal(Inimigo inimigo, Heroi heroi);

    void ataqueEspecial(Inimigo inimigo, Heroi heroi) throws InterruptedException;


}
