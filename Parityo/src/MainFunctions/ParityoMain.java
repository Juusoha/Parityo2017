package MainFunctions;

import Olioluokka.ParityoTestilomake;
import javafx.application.Application;

public class ParityoMain {
	public static ParityoTestilomake lomake = new ParityoTestilomake();
    //GUI:n k�ynnistys
        /*Login ikkuna(tiedot, yhteys, tietojen l�hetys)
         *Lomake auki (Yhteys ja autentikointi suoritettu)
         *Tietojen sy�tt� GUI:ssa ParityoTestilomakkeeseen
         *Lomake l�hetet��n palvelimelle(odotetaan vastaus lomakkeen saapumisesta)
    */
    
    public static void main(String[] args){
    	Application.launch(ParityoGUI.class, args);
        ParityoAsiakas asiakas = new ParityoAsiakas();
        asiakas.asiakas_palvelin_yhteys(lomake);
        
        
    }
   
    
    
}