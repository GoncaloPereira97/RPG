package Controllers.AtackStrategy;

import Controllers.AtackStrategy.*;
import Domain.Entidades.Heroi;
import Domain.Entidades.Inimigo;
import Domain.Items.ItemCombate;
import Domain.Items.Pocao;

import static Tools.Impressora.*;

public class ConsumivelController {

    /**
     * Metodo para usar um consumivel de combate
     * @param itemCombate recebe o item de combate que vai ser usado
     * @param inimigo recebe o inimigo que vai ser atacado com o item
     */
    public static void usarConsumivelCombate(ItemCombate itemCombate, Inimigo inimigo) {

        if (itemCombate.getNome().equalsIgnoreCase("Bomba de Espinhos") || itemCombate.getNome().equalsIgnoreCase("Granada Explosiva")) {
            System.out.println("Usou " + itemCombate.getNome() + " e deu "+ textoVermelho + itemCombate.getAtaqueInstantaneo() + " damage!" + pararCor);
            inimigo.levarDMG(itemCombate.getAtaqueInstantaneo());
        } else if (itemCombate.getNome().equalsIgnoreCase("Bomba de Gás") || itemCombate.getNome().equalsIgnoreCase("Granada de Gás")) {
            System.out.println("Usou Bomba de Gás vai dar " + itemCombate.getAtaqueInstantaneo() + " damage por segundo durante 4 segundos!");
            for (int i = 0; i < 4; i++) {
                System.out.println(textoVermelho + itemCombate.getAtaqueInstantaneo() + " damage" + pararCor);
                inimigo.levarDMG(itemCombate.getAtaqueInstantaneo());
            }
        } else {
            System.out.println("Opção inválida");
        }
    }

    /**
     * Metodo para usar uma poção
     * @param pocao recebe a poção que vai ser usada
     * @param heroi recebe o heroi que vai receber os beneficios
     */
    public static void usarPocao(Pocao pocao, Heroi heroi) {

        if (pocao.getNome().equalsIgnoreCase("Garrafa de Favaios") || pocao.getNome().equalsIgnoreCase("Gasolina 95")) {
            System.out.println("Usou " + pocao.getNome() + " recebeu " + textoVerde +pocao.getVida() + " HP" + pararCor);
            heroi.receberHeal(pocao.getVida());
        } else if (pocao.getNome().equalsIgnoreCase("Sangue de Bruxa") || pocao.getNome().equalsIgnoreCase("Injecao Atómica")) {
            System.out.println("Usou " + pocao.getNome() + " sacrificou " + textoVermelho + pocao.getVida() + " HP" + pararCor + " por " + textoVerde +pocao.getPrimaryStat() +pararCor+ " primary stat");
            heroi.receberHeal(pocao.getVida());
            if (heroi.getClassType() instanceof BarbarianStrategy || heroi.getClassType() instanceof BruteStrategy) {
                heroi.setStrength(heroi.getStrength() + pocao.getPrimaryStat());
            } else if (heroi.getClassType() instanceof RangerStrategy || heroi.getClassType() instanceof SoldierStrategy) {
                heroi.setDexterity(heroi.getDexterity() + pocao.getPrimaryStat());
            } else if (heroi.getClassType() instanceof WizardStrategy || heroi.getClassType() instanceof TechieStrategy) {
                heroi.setInteligence(heroi.getInteligence() + pocao.getPrimaryStat());
            }
        }
    }
}
