package Palvelin;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import Olioluokka.ParityoTestilomake;

/**
 *
 * @author TotteK & Juusoha
 */
public class ParityoPalvelin {
// PalvelinEsimerkki.java
// Kuuntelee porttia, yhteydest� otta vastaan kokonaislukuja, vastaa neli�im�ll�
// sulkee yhteyden jollei tule kokonaislukua


    // kuunteleva palvelint�pseli
    ServerSocket ss = null;

    public static void main(String[] args) {

        ParityoPalvelin p;

        if (args.length > 0)
            p = new ParityoPalvelin(Integer.valueOf(args[0]));
        else
            p = new ParityoPalvelin();

        p.kuuntele();

    } // main()


    // konstruktorit avaavat yhteyden kuuntelulle
    public ParityoPalvelin(int portti) {

        try {
            ss = new ServerSocket(portti);
            System.out.println("Kuunnellaan porttia " + portti);
        } catch (Exception e) {
            System.err.println(e);
            ss = null;
        }
    }

    public ParityoPalvelin() {
        this(1234);
    }


    // kuuntelu "s�ie" (s�ikeistet��n my�hemmin kurssilla)
    public void kuuntele() {

        try {
            while (true) {

                // odotetaan uutta yhteytt�
                Socket cs = ss.accept();

                // luodaan ja k�ynnistet�� uusi palvelijas�ie
                new Palvelija(cs).start();
            }
        } catch (Exception e) {
            System.err.println(e);
            ss = null;
        }
    }   // kuuntele()

    // palvelee yhden yhteyden
    public class Palvelija extends Thread {

        Socket asiakas;

        public Palvelija(Socket cs) {
            //super ajaa Thread luokan constructorin
            super();
            asiakas = cs;
        }

        public void run() {

            try {
                Thread.State state = Thread.currentThread().getState();
                System.out.println(Thread.currentThread().getName());
                System.out.println("state = " + state);

                System.out.println("Uusi yhteys: " + asiakas.getInetAddress() +
                                   ":" + asiakas.getPort());

                // virrat k�ytt�kelpoiseen muotoon
                ObjectOutputStream os = new ObjectOutputStream(asiakas.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(asiakas.getInputStream());
                
                ParityoTestilomake lomake = (ParityoTestilomake)is.readObject();
                
                int opiskelijanumero = lomake.getOpiskelijanumero();
                String etunimi = lomake.getEtunimi();
                String sukunimi = lomake.getSukunimi();
                System.out.println(opiskelijanumero);
                System.out.println(etunimi);
                System.out.println(sukunimi);
                        
                //Scanner input = new Scanner(is);
                
                // luetaan "pyynt�"
               

                // l�hetet��n vastaus
                os.writeObject(lomake);
                
                

                asiakas.close();

            } catch (Exception e) {
                System.err.println(e);
            }

        }   // run()

    } // class Palvelija

}   // class
