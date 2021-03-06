package MainFunctions;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import Olioluokka.ParityoTestilomake;

/**
 * 
 *<h1>Lääkiksen ilmoittautumisohjelma proto</h1>
 * 
 *Main(Pääohjelma ja GUI)
 * 
 *Käynnistää olut-reseptikirjasto ohjelman graafisen käyttöliittymän
 * 
 *<p>
 *
 *@author Juusoha
 *@version 1.0
 *@since 2016-06-18
 *
 *@param Scene scene Aloitusikkunan Scene
 *@param Stage AloitusIkkuna Pääohjelman aloitus ikkuna 
 * 
 *@param Gridpane paavalikko Päävalikon paneeli
 */

public class ParityoGUI extends Application{
	
	//Luodaan globaali olio ParityoTestilomake luokasta
	ParityoTestilomake lomake = new ParityoTestilomake();
			
	public void start(Stage AloitusIkkuna){
		//Luodaan paneeli päävalikkoa varten
		
		GridPane paavalikko = new GridPane();
		
		//Luodaan painike kirjautumista varten
		
		Button log_in = new Button("Log In");
		
		//Luodaan tekstikentät kirjautumistiedoille
		
		final TextField kayttajatunnus = new TextField();
		 
		kayttajatunnus.setPromptText("Käyttäjätunnus:");
		kayttajatunnus.setPrefColumnCount(30);
		kayttajatunnus.getText();
		GridPane.setConstraints(kayttajatunnus, 0, 0);
		paavalikko.getChildren().add(kayttajatunnus);
		
		final TextField salasana = new TextField();
		 
		salasana.setPromptText("Salasana:");
		salasana.setPrefColumnCount(30);
		salasana.getText();
		GridPane.setConstraints(salasana, 0, 1);
		paavalikko.getChildren().add(salasana);
		
		
		//Kiinnitetään button päävalikkoon
		
		paavalikko.add(log_in, 0,3);
		
		//Luodaan paneeli ilmoittautumistietojen kyselyä varten
		GridPane kyselypaneeli = new GridPane();
		kyselypaneeli.setPadding(new Insets(10, 10, 10, 10));
		kyselypaneeli.setHgap(5);
		kyselypaneeli.setVgap(5);
		
		
		
		//Käynnistetään sisäänkirjautuminen, metodissa tietojen syöttö
		log_in.setOnAction(new EventHandler<ActionEvent>() {
			@Override
		    public void handle(ActionEvent e) {
				
				//Opiskelijanumeron kysely
				final TextField opiskelijanro = new TextField();
				 
				opiskelijanro.setPromptText("Opiskelijanumero:");
				opiskelijanro.setPrefColumnCount(6);
				opiskelijanro.getText();
				GridPane.setConstraints(opiskelijanro, 0, 0);
				kyselypaneeli.getChildren().add(opiskelijanro);
				
				
				//Etunimen kysely
				final TextField etunimi = new TextField();
				 
				etunimi.setPromptText("Etunimi:");
				etunimi.setPrefColumnCount(30);
				etunimi.getText();
				GridPane.setConstraints(etunimi, 0, 1);
				kyselypaneeli.getChildren().add(etunimi);
				
				//Sukunimen kysely
				final TextField sukunimi = new TextField();
				 
				sukunimi.setPromptText("Sukunimi:");
				sukunimi.setPrefColumnCount(30);
				sukunimi.getText();
				GridPane.setConstraints(sukunimi, 0, 2);
				kyselypaneeli.getChildren().add(sukunimi);
				
				//Luodaan buttoni tietojen tallennukseen
				Button tallenna = new Button("Tallenna tiedot");
				
				//Lisätään tallennus-buttoni kyselypaneeliin
				kyselypaneeli.add(tallenna, 0, 4);
				
				//Luodaan paneeli tallennettujen tietojen näyttämiselle
				
										
				//Käynnistetään tietojen tallennus
				//Tallennukseen käytetään olio-luokan ParityoTestilomake ilmentymää
				tallenna.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent e) {
						
						//Luodaan Vbox tekstille
						VBox tekstille = new VBox();
						tekstille.setPadding(new Insets (10));
						tekstille.setSpacing(8);
						Text otsikko = new Text("Tarkista tietosi");
					    otsikko.setFont(Font.font("Arial", FontWeight.BOLD, 10));
						    tekstille.getChildren().add(otsikko);
						    
						//Asetetaan tiedot olioluokan kautta
						lomake.setOpiskelijanumero(Integer.parseInt(opiskelijanro.getText()));
						lomake.setEtunimi(etunimi.getText());	 
						lomake.setSukunimi(sukunimi.getText());
						
						//Siirretään lomakkeen tiedot main-luokan muuttujaan
						ParityoMain.lomake = lomake;
						
						
						//Luodaan lista tietojen tallennukseen
						ArrayList<String> tiedot = new ArrayList<String>();
						tiedot.add(Integer.toString(lomake.getOpiskelijanumero()));
						tiedot.add(lomake.getEtunimi());
						tiedot.add(lomake.getSukunimi());
						
						System.out.print(tiedot);
						
						
						
						//Käydään lista läpi for loopissa ja tulostetaan tallennetut tiedot erillisessä ikkunassa
						for(int i = 0; i < tiedot.size(); i++){
							
							String tieto = tiedot.get(i);
							Text teksti = new Text(tieto);
							tekstille.getChildren().add(teksti);
																								
						}
						
						//Luodaan uusi ikkuna tallennettujen tietojen näyttämiselle
						Stage tallennetut = new Stage();
						tallennetut.setTitle("Tallentamasi tiedot: ");	
						tallennetut.setScene(new Scene(tekstille, 300, 200));
						tallennetut.show();
						
						//Exit Program + tehdään erillinen button joka asetetaan vielä uuteen ikkunaan
						//Uudessa ikkunassa näytetään palvelimelle lähetetyt tiedot
						Platform.exit();
						
					}
				});
				//Luodaan uusi ikkuna tietojen keräämiseen ja tallennukseen
				Stage tietojenkysely = new Stage();
				tietojenkysely.setTitle("Tietotojen täyttö");
				tietojenkysely.setScene(new Scene(kyselypaneeli, 300, 250));
				tietojenkysely.show();
			}
		});
		
		//Määritellään aloitusikkuna ja sen asetukset
		Scene scene = new Scene(paavalikko, 300, 300);
		
		AloitusIkkuna.setTitle("Ilmoittatumisjärjestelmä");
		AloitusIkkuna.setScene(scene);
		AloitusIkkuna.show();
	}
	
}