package controller;

import simu.model.OmaMoottori;
import view.ISimulaattorinUI;
import view.Visualisointi;

public interface IKontrolleriForV {

    // Rajapinta, joka tarjotaan  käyttöliittymälle:

    public void kaynnistaSimulointi();
    public void nopeuta();
    public void hidasta();

}
