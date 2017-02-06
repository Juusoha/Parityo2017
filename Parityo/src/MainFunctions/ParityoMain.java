package MainFunctions;

import Olioluokka.ParityoTestilomake;
import javafx.application.Application;

public class ParityoMain {
	public static ParityoTestilomake lomake = new ParityoTestilomake();
    //GUI:n käynnistys
        /*Login ikkuna(tiedot, yhteys, tietojen lähetys)
         *Lomake auki (Yhteys ja autentikointi suoritettu)
         *Tietojen syöttö GUI:ssa ParityoTestilomakkeeseen
         *Lomake lähetetään palvelimelle(odotetaan vastaus lomakkeen saapumisesta)
    */
    
    public static void main(String[] args){
    	Application.launch(ParityoGUI.class, args);
        ParityoAsiakas asiakas = new ParityoAsiakas();
        asiakas.asiakas_palvelin_yhteys(lomake);
        
        
    }
   
    
    
}