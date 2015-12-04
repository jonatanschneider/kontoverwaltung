package kontoverwaltung;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		ArrayList<Konto> kontenListe = new ArrayList<Konto>();
		int anzahlErstellterKonten = 3;
		Scanner scan = new Scanner(System.in);
		Scanner scanStr = new Scanner(System.in);
		int auswahl = -1;
		
		kontenListe.add(new Konto("B","B",200,20,1));
		kontenListe.add(new Konto("A","A",300,30,2));
		kontenListe.add(new Konto("C","C",100,10,3));
		
		while(auswahl != 0){
            auswahl = printMenu();
            
            switch(auswahl){
            case 1:
                Konto neuesKonto = neuesKonto(anzahlErstellterKonten);
                kontenListe.add(neuesKonto);
                anzahlErstellterKonten++;
                break;
                
            case 2:
                System.out.println("Welches Konto soll angezeigt werden? (ID angeben)");
                int temp = scan.nextInt();
                System.out.println(kontenListe.get(temp-1).Output());
                break;
                
            case 3:
                System.out.println("Welches Konto betrifft Ihre Ein/Auszahlung (ID angeben)");
                int konto = scan.nextInt();
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
                        kontenListe.get(konto-1).Einzahlung(betrag);
                        kontenListe.get(konto-1).einzahlungErfolgt(betrag);
                    }
                }
                else{
                    System.err.println("Fehlerhafte Eingabe!");
                }
                break;
                
            case 4:
                alleKontenAusgeben(kontenListe);
                break;
                
            case 5:
                System.out.println("Wie möchten Sie sortieren?");
                System.out.println("1 - Vorname - aufsteigend");
                System.out.println("2 - Vorname - absteigend");
                System.out.println("3 - Nachname - aufsteigend");
                System.out.println("4 - Nachname - absteigend");
                System.out.println("5 - Kontostand - aufsteigend");
                System.out.println("6 - Kontostand - absteigend");
                int temp3 = scan.nextInt();
                Sortieren(temp3, kontenListe);                
                break;
                
            case 6:
                System.out.println("Nach was möchten Sie suchen?");
                System.out.println("1 - Vorname");
                System.out.println("2 - Nachname");
                System.out.println("3 - Kontostand");
                System.out.println("4 - Kontonummer");
                int temp4 = scan.nextInt();
                System.out.println("Geben Sie Ihren Suchbegriff ein");
                String suchbegriff = scanStr.nextLine();

                if(temp4 < 3){
                    Suchen(temp4, suchbegriff, -1, kontenListe);
                }
                else if(temp4 < 5){
                    System.out.println("Bitte definieren Sie Ihre Suche!");
                    System.out.println("1 - Zahl < "+suchbegriff);
                    System.out.println("2 - Zahl > "+suchbegriff);
                    System.out.println("3 - Zahl = "+suchbegriff);
                    int operator = scan.nextInt();
                    Suchen(temp4, suchbegriff, operator, kontenListe);
                }
                else{
                    System.err.println("Fehlerhafte Eingabe!");
                }
                break;
                
            case 7:
                System.out.println("Welches Konto soll gelöscht werden? (ID angeben)");
                int temp5 = scan.nextInt();
                kontoLoeschen(temp5, kontenListe);
                break;
                
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
	
	private static int printMenu(){
	    Scanner sc = new Scanner(System.in);
	    System.err.println("-----------------------------------------------");
        System.out.println("Menü");
        System.out.println("-----------------------------------------------");
        System.out.println("1 - Konto hinzufügen");
        System.out.println("2 - Konto anzeigen");
        System.out.println("3 - Ein/Auszahlung");
        System.out.println("4 - Alle Konten anzeigen");
        System.out.println("5 - Sortieren");
        System.out.println("6 - Suchen");
        System.out.println("7 - Konto löschen");
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
        
        kontonummer = stringZuInteger(kontonummerStr);
        kontostand = stringzuDouble(kontostandStr);
        
        Konto konto = new Konto(vorname,name,kontonummer,kontostand, anzahlErstellterKonten+1);

        return konto;
	}
		
	private static void kontoLoeschen(int index, ArrayList<Konto> kontenListe){
	    kontenListe.remove(index-1);
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
	
	private static void Sortieren(int auswahl, ArrayList<Konto> kontenListe){
	    switch (auswahl){
	    
	    case 1:
	        int[] temp=Algorithmen.NamenAufsteigendSortieren(kontenListe, "vorname");
	        for (int i=0; i<temp.length; i++)
	        {
	            System.out.println(kontenListe.get(temp[i]-1).Output());
	        }
	        break;
	        
	    case 2:
	        int[] temp2=Algorithmen.NamenAbsteigendSortieren(kontenListe, "vorname");
	        for (int i=0; i<temp2.length; i++)
	        {
	            System.out.println(kontenListe.get(temp2[i]-1).Output());
	        }
	        break;
	          
	    case 3:
	        int[] temp3=Algorithmen.NamenAufsteigendSortieren(kontenListe, "name");
            for (int i=0; i<temp3.length; i++)
            {
                System.out.println(kontenListe.get(temp3[i]-1).Output());
            }
            
	        break;
	        
	    case 4:
	        int[] temp4=Algorithmen.NamenAbsteigendSortieren(kontenListe, "name");
            for (int i=0; i<temp4.length; i++)
            {
                System.out.println(kontenListe.get(temp4[i]-1).Output());
            }
	        break;
	        
	    case 5:
	        int[] temp5=Algorithmen.KontostandAufsteigendSortieren(kontenListe);
	        for (int i=0; i<temp5.length; i++)
	        {
	            System.out.println(kontenListe.get(temp5[i]-1).Output()); 
	        }
	        break;
	        
	    case 6:
	        int[] temp6=Algorithmen.KontostandAbsteigendSortieren(kontenListe);
	        for (int i=0; i<temp6.length; i++)
	        {
	            System.out.println(kontenListe.get(temp6[i]-1).Output());  
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