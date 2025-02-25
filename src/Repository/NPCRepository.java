package Repository;

import Domain.Entidades.Inimigo;
import Domain.Entidades.NPC;
import Domain.Entidades.Vendedor;
import Tools.CSVNpcReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class NPCRepository {

    private ArrayList<NPC> arrayNPC;

    private String filePath = "Files/NPC.csv";

    public NPCRepository() throws FileNotFoundException {
        CSVNpcReader csvNpcReader = new CSVNpcReader(filePath);
        this.arrayNPC = csvNpcReader.readCSVToRepository();
    }
    public ArrayList<NPC> getArrayNPC() {
        return arrayNPC;
    }


}
