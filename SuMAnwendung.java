import sum.ereignis.*;
import sum.komponenten.*;
import sum.werkzeuge.*;
import sum.strukturen.*;
/**
 * @author Dagh Zeppenfeld
 * @version
 */
public class SuMAnwendung extends EBAnwendung
{
    // Bezugsobjekte

    Knopf starteRechnung; // Knopf, um errechnen von primzahlen zu starten

    Auswahl aLaenge; // Dropdown zur Auswahl der "Sortierlänge"
    
    protected Liste<Etikett> e;

    Primzahlen prim; //deklarierung der klasse Primzahlen()
    Etikett status; // Etikett, dass einen Statusbericht anzeigt (Bsw. "Berechne" oder "fertig")
    Etikett Seite;
    Etikett Info;
    Etikett Info1;
    Etikett Info2;
    Etikett Info3;

    Rechner r;
    // Attribute
    int n; // Anzahl der Zahlen im Array
    int seite = 1;
    int maxSeite = 1;
    boolean sorted = false; 
    String lastUsed;

    // Konstruktor
    public SuMAnwendung()
    {
        super(1300,710);

        prim = new Primzahlen();

        starteRechnung = new Knopf(10,10,95,25,"Berechnen","bStarteRechnungGeklickt");

        aLaenge = new Auswahl(110,10,100,25); 
        this.fillAuswahlen();
        
        e = new Liste<Etikett>();
        for(int i = 1; i<20; i++)
        {
            e.haengeAn(new Etikett(10,70+30*i,1200,25,""));
            e.zumEnde();
            e.aktuelles().setzeSchriftgroesse(20);
        }

        status = new Etikett(1200,3,100,30,"Warte auf Start");
        status.setzeSchriftFarbe(1);
        Seite = new Etikett(350,675,100,30,"Seite: 0");
        Info = new Etikett(1000,200,300,50,"Infotext:   ");
        Info1 = new Etikett(1000,220,300,50,"1. Auswählen bis wo Primzahlen gesucht werden sollen.");
        Info2 = new Etikett(1000,240,300,50,"2. Linken Knopf drücken um die Suche zu starten.");
        Info3 = new Etikett(1000,260,300,50,"3. mit den unteren Knöpen durch die Anzeige Navigieren");

        Knopf bFirst = new Knopf(25,675,75,25,"<<-","first");
        Knopf bPrevious = new Knopf(100,675,75,25,"<","previous");
        Knopf bNext = new Knopf(175,675,75,25,">","next");
        Knopf bLast = new Knopf(250,675,75,25,"->>","last");

        r = new Rechner();
    }

    // Dienste
    public void fillAuswahlen()
    {
        for(int i=0; i<=10000000; i = i + 100)
        {
            aLaenge.haengeAn("Bis:"+i);
        }
    }
    
    public void resetE()
    {
        for(int i=1;i<20;i++)
        {
            e.geheZuPosition(i);
            e.aktuelles().setzeInhalt("");
        }
    }

    public void bStarteRechnungGeklickt()
    {
        status.setzeSchriftFarbe(1); // Status zeigt hier in der Farbe Blau  an, das die Sortierung begonnen werden kann
        status.setzeInhalt("Warte auf Start");

        status.setzeSchriftFarbe(10); // Status zeigt hier in der Farbe Rot an, das nun berechnet wird
        status.setzeInhalt("Berechne...");

        prim.pruefePrimzahl(aLaenge.index() * 100 - 100);
        resetAndFillE();
        
        getMaxSeite();

        status.setzeSchriftFarbe(5); // Status zeigt hier in der Farbe Grün an, das die Sortierung abgeschlossen ist
        status.setzeInhalt("FERTIG");

    }

    public void resetAndFillE()
    {
        resetE();
        for(int i=1;i<20;i++)
        {    
            if(prim.schritte.laenge()>=19*(seite-1)+i)
            {
                e.geheZuPosition(i);
                prim.schritte.geheZuPosition(19*(seite-1)+i);
                e.aktuelles().setzeInhalt(prim.schritte.aktuelles()); 
            }
        }
    }

    public void getMaxSeite()
    {
        int i = 1;
        maxSeite = 1;
        while(true)
        {
            if(prim.schritte.laenge()>19*i)
            {
                maxSeite+=1;
                i+=1;
            }
            else
            {break;}
        }
    }

    public void first()
    {   
        seite = 1;
        Seite.setzeInhalt("Seite:" + seite);
        resetAndFillE();
    }

    public void previous()
    {
        if(seite>1)
        {
            seite = seite - 1;
            Seite.setzeInhalt("Seite:" + seite);
            resetAndFillE();
        }
    }

    public void next()
    {
        if(seite<maxSeite)
        {
            seite = seite + 1;
            Seite.setzeInhalt("Seite:" + seite);
            resetAndFillE();
        }
    }

    public void last()
    {
        if(seite<maxSeite)
        {
            seite = maxSeite;
            Seite.setzeInhalt("Seite:" + seite);
            resetAndFillE();
        }
    }
}
