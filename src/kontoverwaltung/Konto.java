package kontoverwaltung;

public class Konto
{
    private String vorname;
    private String name;
    private double kontostand;
    private int kontonummer;
    private int id;

    public Konto(String vorname, String name, int kontonummer, double kontostand, int ID)
    {
        this.setVorname(vorname);
        this.setName(name);
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
        this.id = ID;
    }

    public int getId()
    {
        return id;
    }
    
    private void setVorname(String vorname)
    {
        if( vorname!=null && vorname.length()>0){
        this.vorname=vorname;
        }
    }
    
    public String getVorname()
    {
        return vorname;
    }

    private void setName(String name)
    {
        if (name!=null && name.length()>0)
        {
            this.name=name;
        }
    }

    public String getName()
    {
        return name;
    }

    public double getKontostand()
    {
        return kontostand;
    }

    public int getKontonummer()
    {
        return kontonummer;
    }

    public void Auszahlung(double betrag)
    {
        this.kontostand = this.kontostand - betrag;
    }

    public void Einzahlung(double betrag)
    {
        this.kontostand = this.kontostand + betrag;
    }
   
    public String Output(){
        return "ID: " +this.id +" Kontoinhaber: " +this.vorname +" " +this.name +" Kontonummer: "+this.kontonummer +" Kontostand: "+this.kontostand;
    }

    public String einzahlungErfolgt(double betrag){
        return "Einzahlung von "+betrag +"€ auf Konto " +this.id +" erfolgt. Neuer Kontostand " +this.kontostand+"€";
    }
    
    public String auszahlungErfolgt(double betrag){
        return "Auszahlung von "+betrag +"€ von Konto " +this.id +" erfolgt. Neuer Kontostand " +this.kontostand+"€";
    }
}
