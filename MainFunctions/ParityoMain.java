package MainFunctions;

import Olioluokka.ParityoTestilomake;
import javafx.application.Application;

public class ParityoMain {
    //GUI:n käynnistys
        /*Login ikkuna(tiedot, yhteys, tietojen lähetys)
         *Lomake auki (Yhteys ja autentikointi suoritettu)
         *Tietojen syöttö GUI:ssa ParityoTestilomakkeeseen
         *Lomake lähetetään palvelimelle(odotetaan vastaus lomakkeen saapumisesta)
    */
    
    public static void main(String[] args){
    	Application.launch(ParityoGUI.class, args);
        ParityoTestilomake lomake = new ParityoTestilomake();
        ParityoAsiakas asiakas = new ParityoAsiakas();
        asiakas.asiakas_palvelin_yhteys(lomake);
        
        
    }
}