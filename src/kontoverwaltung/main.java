package kontoverwaltung;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		ArrayList<Konto> kontenListe = new ArrayList<Konto>();
		ArrayList<Integer> geloeschteKonten = new ArrayList<Integer>();
		int anzahlErstellterKonten = 3; //3 Aufgrund der 3 bereits erstellten Beispiel Konten
		Scanner scan = new Scanner(System.in);
		Scanner scanStr = new Scanner(System.in);
		int auswahl = -1;
		
		//3 Beispiel Konten
		kontenListe.add(new Konto("B_vor","B_nach",200,20,1));
		kontenListe.add(new Konto("A_vor","A_nach",300,30,2));
		kontenListe.add(new Konto("C_vor","C_nach",100,10,3));
		
		while(auswahl != 0){
            auswahl = printMenu();
            
            switch(auswahl){
            //Konto hinzufügen
            case 1:
                Konto neuesKonto = neuesKonto(anzahlErstellterKonten);
                kontenListe.add(neuesKonto);
                anzahlErstellterKonten++;
                break;
                
            //Konto anzeigen
            case 2:
                System.out.println("Welches Konto soll angezeigt werden? (ID angeben)");
                int input1 = scan.nextInt();
                
                //-1 da die IDs bei 1 und nicht bei 0 starten
                if(KontoVorhanden(input1-1, kontenListe)){
                    System.out.println(kontenListe.get(input1-1).Output());
                }
                else{
                    System.out.println("Konto nicht vorhanden!");
                }
                break;
                
            //Ein- oder Auszahlung
            case 3:
                System.out.println("Welches Konto betrifft Ihre Ein/Auszahlung (ID angeben)");
                int konto = scan.nextInt();
                if(KontoVorhanden(konto-1, kontenListe)){
                    
                    System.out.println("Was möchten Sie ausführen?");
                    System.out.println("1 - Einzahlung");
                    System.out.println("2 - Auszahlung");
                    int auswahlZahlung = scan.nextInt();
                    System.out.println("Betrag eingeben");
                    String betragStr = scanStr.nextLine();
                    
                    if(istDouble(betragStr)){
                        double betrag = stringzuDouble(betragStr);
                        if(auswahlZahlung == 1){
                            kontenListe.get(konto-1).Einzahlung(betrag);
                            kontenListe.get(konto-1).einzahlungErfolgt(betrag);
                        }
                        else if(auswahlZahlung == 2){
                            kontenListe.get(konto-1).Auszahlung(betrag);
                            kontenListe.get(konto-1).auszahlungErfolgt(betrag);
                        }
                    }
                    else{
                        System.out.println("Fehlerhafte Eingabe!");
                    }
                }
                else{
                    System.out.println("Konto nicht vorhanden!");
                }
                
                break;
            
            //Überweisung
            case 4:
            	System.out.println("Bitte wählen Sie das Ihnhaber-Konto mit der passenden Konto-ID aus");
            	String inhaberIDStr = scanStr.nextLine();
            	int inhaberID = 0;
            	int empfaengerID = 0;
            	
            	if(istInteger(inhaberIDStr)){
            		inhaberID = stringZuInteger(inhaberIDStr)-1;
            		if(KontoVorhanden(inhaberID, kontenListe) == false){
            		    System.out.println("Konto nicht vorhanden!");
            		    break;
            		}
            	}
            	System.out.println("Bitte geben Sie das Empfänger-Konto mit der passenden Konto-ID ein");
            	String empfaengerIDStr = scanStr.nextLine();
            	if(istInteger(empfaengerIDStr)){
            		empfaengerID = stringZuInteger(empfaengerIDStr)-1;
            		if(KontoVorhanden(empfaengerID, kontenListe) == false){
                        System.out.println("Konto nicht vorhanden!");
                        break;
                    }
            	}
            	System.out.println("Bitte geben Sie den zu überweisenden Betrag ein");
            	String betragStr2 = scanStr.nextLine();
            	double betrag = 0;
            	if(istDouble(betragStr2)){
            		betrag = stringzuDouble(betragStr2);
            	}
            	else{
            	    System.out.println("Fehlerhafte Eingabe!");
            	    break;
            	}
                ueberweisung(kontenListe.get(inhaberID), kontenListe.get(empfaengerID), betrag);
            
            //Alle Konten anzeigen
            case 5:
                alleKontenAusgeben(kontenListe);
                break;
                
            //Konten anzeigen und sortieren
           case 6:
                System.out.println("Wie möchten Sie sortieren?");
                System.out.println("1 - Vorname - aufsteigend");
                System.out.println("2 - Vorname - absteigend");
                System.out.println("3 - Nachname - aufsteigend");
                System.out.println("4 - Nachname - absteigend");
                System.out.println("5 - Kontostand - aufsteigend");
                System.out.println("6 - Kontostand - absteigend");
                int temp3 = scan.nextInt();
               
                ArrayList<Konto> tempKontenListeCase6 = kontenListeOhneGeloeschte(kontenListe);
                Sortieren(temp3, tempKontenListeCase6, kontenListe);
                break;
                
            //Konten durchsuchen
          case 7:
                System.out.println("Nach was möchten Sie suchen?");
                System.out.println("1 - Vorname");
                System.out.println("2 - Nachname");
                System.out.println("3 - Kontostand");
                System.out.println("4 - Kontonummer");
                int inputCase7 = scan.nextInt();
                System.out.println("Geben Sie Ihren Suchbegriff ein");
                String suchbegriff = scanStr.nextLine();
                
                //Geloeschte Konten für die Suche temporär entfernen
                
                ArrayList<Konto> tempKontenListeCase7 = kontenListeOhneGeloeschte(kontenListe);
             
                //String Suche
                if(inputCase7 < 3){
                    Suchen(inputCase7, suchbegriff, -1, tempKontenListeCase7);
                }
                //Integer Suche
                else if(inputCase7 < 5){
                        System.out.println("Bitte definieren Sie Ihre Suche!");
                        System.out.println("1 - Zahl < "+suchbegriff);
                        System.out.println("2 - Zahl > "+suchbegriff);
                        System.out.println("3 - Zahl = "+suchbegriff);
                        int operator = scan.nextInt();
                        Suchen(inputCase7, suchbegriff, operator, tempKontenListeCase7);
                    }
                else{
                    System.err.println("Fehlerhafte Eingabe!");
                }
                
                break;
                
            //Konto löschen
            case 8:
                System.out.println("Welches Konto soll gelöscht werden? (ID angeben)");
                int inputCase8 = scan.nextInt();
                geloeschteKonten = kontoLoeschen(inputCase8, kontenListe, geloeschteKonten);               
                break;
                
            //Programm beenden
            case 0:
                System.exit(0);
                break;
                
            default:
                System.err.println("Fehlerhafte Eingabe!");
                break;
            }
            eingabeAbwarten();
		}
	}
	
	//Zeichnet Menü und gibt Auswahl zurück
	private static int printMenu(){
	    Scanner sc = new Scanner(System.in);
	    System.out.println("-----------------------------------------------");
        System.out.println("Menü");
        System.out.println("-----------------------------------------------");
        System.out.println("1 - Konto hinzufügen");
        System.out.println("2 - Konto anzeigen");
        System.out.println("3 - Ein/Auszahlung");
        System.out.println("4 - Überweisung");
        System.out.println("5 - Alle Konten anzeigen");
        System.out.println("6 - Sortieren");
        System.out.println("7 - Suchen");
        System.out.println("8 - Konto löschen");
        System.out.println("0 - Programm beenden");
        System.out.println("-----------------------------------------------");
        System.out.println("Ihre Auswahl:");
        return sc.nextInt();
	}
		
	private static Konto neuesKonto(int anzahlErstellterKonten){
	    Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        String vorname, name, kontonummerStr, kontostandStr;
        int kontonummer = 0;
        double kontostand = 0;
        
        System.out.println("Vorname des Besitzers eingeben");
        vorname = scan1.nextLine();
        System.out.println("Name des Besitzers eingeben");
        name = scan1.nextLine();
        
        System.out.println("Kontonummer eingeben");
        kontonummerStr = scan1.nextLine();
        System.out.println("Kontostand eingeben");
        kontostandStr = scan2.nextLine();
        
        //Prüfung ob Eingabe Zahlen sind und Umwandung in Int/Double
        kontonummer = stringZuInteger(kontonummerStr);
        kontostand = stringzuDouble(kontostandStr);
        
        //ID wird nach Anzahl der Konten vergeben. Konto 1 = ID 1 etc.
        Konto konto = new Konto(vorname,name,kontonummer,kontostand,anzahlErstellterKonten+1);

        return konto;
	}
	
	private static boolean KontoVorhanden(int index, ArrayList<Konto> kontenListe){
	    try {
	        if(kontenListe.get(index) != null){
	            return true;
	        }
	    }
	    catch (IndexOutOfBoundsException e){
	        return false;
	        }
	    return false;
	}
	
	private static ArrayList<Integer> kontoLoeschen(int index, ArrayList<Konto> kontenListe, ArrayList<Integer> geloeschteKonten){
	    int interneID = index-1;
	    boolean geloescht = false;
	    	        
	        for (Konto konto: kontenListe)
	        {
	            if(konto != null){
	                if(konto.getId() == index){
	                    Konto element = null;
	                    //Konto an Index "interneID" auf null (=element) setzen
	                    kontenListe.set(interneID, element);
	                    geloeschteKonten.add(interneID);
	                    System.out.println("Konto "+index +" erfolgreich gelöscht." );
	                    geloescht = true;
	                    break;
	                }
	            }
	        }
	        if (geloescht == false){
                System.out.println("Eingegebene Konto-ID nicht vorhanden!");
            }
	    return geloeschteKonten;
	}
	
	private static void Suchen(int auswahl, String suchbegriff, int operator, ArrayList<Konto> kontenListe){
	    switch(auswahl){
	    case 1:
	        Algorithmen.SucheNachVornamen(suchbegriff, kontenListe);
	        break;
	        
	    case 2:
	        Algorithmen.SucheNachName(suchbegriff, kontenListe);
	        break;
	        
	    case 3:
	        if(istInteger(suchbegriff)){
	            int suchbegriffInt = stringZuInteger(suchbegriff);
	            Algorithmen.SucheNachKontostand(suchbegriffInt, operator, kontenListe);
	        }
	        else{
	            System.err.println("Fehlerhafte Eingabe!");
	        }
	        break;
	        
	    case 4:
	        if(istInteger(suchbegriff)){
                int suchbegriffInt = stringZuInteger(suchbegriff);
                Algorithmen.SucheNachKontonummer(suchbegriffInt, operator, kontenListe);
            }
            else{
                System.err.println("Fehlerhafte Eingabe!");
            }
	        break;
	        
	    default:
	        System.err.println("Fehlerhafte Eingabe!");
	        break;
	    }
	}
	
	private static void Sortieren(int auswahl, ArrayList<Konto> tempkontenListe, ArrayList<Konto> kontenListe){
	    switch (auswahl){
	    
	    case 1:
	        int[] ergebnisCase1=Algorithmen.NamenAufsteigendSortieren(tempkontenListe, "vorname");
	        for (int i=0; i<ergebnisCase1.length; i++)
	        {
	            System.out.println(kontenListe.get(ergebnisCase1[i]-1).Output());
	        }
	        break;
	        
	    case 2:
	        int[] ergebnisCase2=Algorithmen.NamenAbsteigendSortieren(tempkontenListe, "vorname");
	        for (int i=0; i<ergebnisCase2.length; i++)
	        {
	            System.out.println(kontenListe.get(ergebnisCase2[i]-1).Output());
	        }
	        break;
	          
	    case 3:
	        int[] ergebnisCase3=Algorithmen.NamenAufsteigendSortieren(tempkontenListe, "name");
            for (int i=0; i<ergebnisCase3.length; i++)
            {
                System.out.println(kontenListe.get(ergebnisCase3[i]-1).Output());
            }
            
	        break;
	        
	    case 4:
	        int[] ergebnisCase4=Algorithmen.NamenAbsteigendSortieren(tempkontenListe, "name");
            for (int i=0; i<ergebnisCase4.length; i++)
            {
                System.out.println(kontenListe.get(ergebnisCase4[i]-1).Output());
            }
	        break;
	        
	    case 5:
	        int[] ergebnisCase5=Algorithmen.KontostandAufsteigendSortieren(tempkontenListe);
	        for (int i=0; i<ergebnisCase5.length; i++)
	        {
	            System.out.println(kontenListe.get(ergebnisCase5[i]-1).Output()); 
	        }
	        break;
	        
	    case 6:
	        int[] ergebnisCase6=Algorithmen.KontostandAbsteigendSortieren(tempkontenListe);
	        for (int i=0; i<ergebnisCase6.length; i++)
	        {
	            System.out.println(kontenListe.get(ergebnisCase6[i]-1).Output());  
	        }
	        break;
	        
	    default:
	        System.err.println("Fehlerhafte Auswahl! Nur Zahlen von 1-4 möglich!");
	        break;
	    }
	}
	
	private static void alleKontenAusgeben(ArrayList<Konto> kontenListe){
	    for (Konto konto: kontenListe){
            if(konto != null){
            System.out.println(konto.Output());
            }
        }
	}
	
	private static int stringZuInteger(String eingabe){
	    boolean temp = false;
	    Scanner scan = new Scanner(System.in);
	    
	    while(temp == false){
            if(istInteger(eingabe)){
                temp = true;
            }
            else{
                System.out.println("Fehlerhafte Eingabe! Bitte nur Zahlen eingeben!");
                System.out.println("Kontonummer erneut eingeben");
                eingabe = scan.next();
            }
        }
	    return Integer.parseInt(eingabe);
	}
	
	private static double stringzuDouble(String eingabe){
	    boolean temp = false;
	    Scanner scan = new Scanner(System.in);
	    
        while(temp == false){
            if(istDouble(eingabe)){
                temp = true;
            }
            else{
                System.out.println("Fehlerhafte Eingabe! Bitte nur Zahlen eingeben!");
                System.out.println("Kontostand erneut eingeben");
                eingabe = scan.next();
            }
        }
        return Double.parseDouble(eingabe);
	}
	
    private static boolean istInteger(String eingabe)
    {
        try
        {
            Integer.parseInt(eingabe);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    
    private static boolean istDouble(String eingabe)
    {
        try
        {
            Double.parseDouble(eingabe);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    
    private static void ueberweisung(Konto inhaber, Konto empfaenger, double betrag){
    	inhaber.Auszahlung(betrag);
    	empfaenger.Einzahlung(betrag);
    }
    
    private static ArrayList<Konto> kontenListeOhneGeloeschte(ArrayList<Konto> kontenListe){
        ArrayList<Konto> neueKontenListe = new ArrayList<Konto>();
        
        for (Konto konto: kontenListe)
        {
            if(konto != null){
                neueKontenListe.add(konto);
            }
        }
        return neueKontenListe;
    }
    
    private static void eingabeAbwarten(){
        System.out.println("Enter drücken zum Fortsetzen");
        try
        {
            System.in.read();
        }
        catch (java.io.IOException e)
        {
        }
    }
}