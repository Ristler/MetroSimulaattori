package view;


import view.SimulaattorinGUI;
/**
 * Luokka toimii simulaattorin aloitus luokkana.
 * <p>
 * Luokka aloittaa koko simulaattori ohjelman
 *
 * @author Ryhmä 10
 * @version 1.0
 */
public class Main {
    // JavaFX-sovelluksen (käyttöliittymän) käynnistäminen
    public static void main(String[] args) {
        view.SimulaattorinGUI.launch(SimulaattorinGUI.class);
    }
}