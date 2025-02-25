package Controllers.CaracterCreation;

import Domain.Entidades.Heroi;

import java.io.FileNotFoundException;

public interface CreationInterface {
    Heroi caracterCreator() throws FileNotFoundException;
}
