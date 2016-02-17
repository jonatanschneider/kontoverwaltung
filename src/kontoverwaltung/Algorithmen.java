package kontoverwaltung;
import java.util.ArrayList;

public class Algorithmen {

    //Groﬂ kleinschreibung
    
	public static void SucheNachKontonummer(int suchbegriff, int operator, ArrayList<Konto> kontenListe){
	    System.out.println("Folgende Treffer wurden gefunden:");
	    for (Konto konto: kontenListe)
        {
	        switch(operator){
	        case 1:
	            if(konto.getKontonummer() < suchbegriff){               
                    System.out.println(konto.Output());
                }
	            break;
	        case 2:
	            if(konto.getKontonummer() > suchbegriff){               
                    System.out.println(konto.Output());
                }
	            break;
	        case 3:
	            if(konto.getKontonummer() == suchbegriff){               
	                System.out.println(konto.Output());
	            }
	            break;
	        default:
	            System.out.println("Fehlerhafte Auswahl!");
	            break;
	        }
        }
	}
	
	public static void SucheNachKontostand(int suchbegriff, int operator, ArrayList<Konto> kontenListe){
	    System.out.println("Folgende Treffer wurden gefunden:");
        for (Konto konto: kontenListe)
        {
            switch(operator){
            case 1:
                if(konto.getKontostand() < suchbegriff){               
                    System.out.println(konto.Output());
                }
                break;
            case 2:
                if(konto.getKontostand() > suchbegriff){               
                    System.out.println(konto.Output());
                }
                break;
            case 3:
                if(konto.getKontostand() == suchbegriff){               
                    System.out.println(konto.Output());
                }
                break;
            default:
                System.out.println("Fehlerhafte Auswahl!");
                break;
            }
        }
	}
	
	public static void SucheNachVornamen(String suchbegriff, ArrayList<Konto> kontenListe){
	    System.out.println("Folgende Treffer wurden gefunden:");
        for(Konto konto : kontenListe){
            if(konto.getVorname().contains(suchbegriff)){
                System.out.println(konto.Output());
            }
        }
	}
	
	public static void SucheNachName(String suchbegriff, ArrayList<Konto> kontenListe){
		System.out.println("Folgende Treffer wurden gefunden:");
		for(Konto konto : kontenListe){
		    if(konto.getName().contains(suchbegriff)){
		        System.out.println(konto.Output());
		    }
		}
	}
	
	public static int[] NamenAufsteigendSortieren(ArrayList<Konto> kontenListe, String kriterium){
	    String[] namen = new String[kontenListe.size()];
        int[] id = new int[kontenListe.size()];
        
        if(kriterium.equals("name")){
            for (int i=0; i<namen.length; i++)
            {
                namen[i] = kontenListe.get(i).getName();
                id[i] = kontenListe.get(i).getId();
            }
        }
        else {
            for (int i=0; i<namen.length; i++)
            {
                namen[i] = kontenListe.get(i).getVorname();
                id[i] = kontenListe.get(i).getId();
            }
        }

        for(int i=0; i < namen.length; i++){
            for(int j=0; j<namen.length-1; j++){
                if(namen[i].compareTo(namen[j])>0){
                    String tempName = namen[i];
                    namen[i] = namen[j];
                    namen[j] = tempName;
                    
                    int tempID = id[i];
                    id[i] = id[j];
                    id[j] = tempID;
                }
            }
        }
        return id;
    }
	
	public static int[] NamenAbsteigendSortieren(ArrayList<Konto> kontenListe, String kriterium){
	    String[] namen = new String[kontenListe.size()];
        int[] id = new int[kontenListe.size()];
        
        if(kriterium.equals("name")){
            for (int i=0; i<namen.length; i++)
            {
                namen[i] = kontenListe.get(i).getName();
                id[i] = kontenListe.get(i).getId();
            }
        }
        else {
            for (int i=0; i<namen.length; i++)
            {
                namen[i] = kontenListe.get(i).getVorname();
                id[i] = kontenListe.get(i).getId();
            }
        }
        

        for(int i=0; i < namen.length; i++){
            for(int j=0; j<namen.length-1; j++){
                if(namen[i].compareTo(namen[j])<0){
                    String tempName = namen[i];
                    namen[i] = namen[j];
                    namen[j] = tempName;
                    
                    int tempID = id[i];
                    id[i] = id[j];
                    id[j] = tempID;
                }
            }
        }
        return id;
    }
	
	public static int[] KontostandAufsteigendSortieren(ArrayList<Konto> kontenListe){
	    double[] kontostand = new double[kontenListe.size()];
        int[] id = new int[kontenListe.size()];
        
        for (int i=0; i<kontostand.length; i++)
        {
            kontostand[i] = kontenListe.get(i).getKontostand();
            id[i] = kontenListe.get(i).getId();
        }
        

        for(int i=0; i < kontostand.length; i++){
            for(int j=0; j<kontostand.length-1; j++){
                if(kontostand[i] < kontostand[j]){
                    double temp = kontostand[i];
                    kontostand[i] = kontostand[j];
                    kontostand[j] = temp;
                    
                    int tempID = id[i];
                    id[i] = id[j];
                    id[j] = tempID;
                }
            }
        }
        return id;
	}
	
	public static int[] KontostandAbsteigendSortieren(ArrayList<Konto> kontenListe){
	    double[] kontostand = new double[kontenListe.size()];
        int[] id = new int[kontenListe.size()];
        
        for (int i=0; i<kontostand.length; i++)
        {
            kontostand[i] = kontenListe.get(i).getKontostand();
            id[i] = kontenListe.get(i).getId();
        }
        

        for(int i=0; i < kontostand.length; i++){
            for(int j=0; j<kontostand.length-1; j++){
                if(kontostand[i] > kontostand[j]){
                    double temp = kontostand[i];
                    kontostand[i] = kontostand[j];
                    kontostand[j] = temp;
                    
                    int tempID = id[i];
                    id[i] = id[j];
                    id[j] = tempID;
                }
            }
        }
        return id;
	}
}
