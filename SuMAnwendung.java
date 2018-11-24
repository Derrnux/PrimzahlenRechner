import sum.ereignis.*;
import sum.komponenten.*;
import sum.werkzeuge.*;
import sum.strukturen.*;
/**
 * @author
 * @version
 */
public class SuMAnwendung extends EBAnwendung
{
    // Bezugsobjekte

    Knopf starteRechnung; // Knopf, um errechnen von primzahlen zu starten

    Auswahl aLaenge; // Dropdown zur Auswahl der "Sortierlänge"
    //Textfeld laenge;
    
    
    Primzahlen prim; //deklarierung der klasse Primzahlen()

    Etikett status; // Etikett, dass einen Statusbericht anzeigt (Bsw. "Berechne" oder "fertig")
    Etikett Seite;

   
    
    
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
        //laenge = new Textfeld(110,10,100,25,"max eingeben");
        
        status = new Etikett(1200,3,100,30,"Warte auf Start");
        status.setzeSchriftFarbe(1);
        Seite = new Etikett(350,675,50,30,"Seite: 0");

        
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
            
            
    public void bStarteRechnungGeklickt()
    {
        status.setzeSchriftFarbe(1); // Status zeigt hier in der Farbe Blau  an, das die Sortierung begonnen werden kann
        status.setzeInhalt("Warte auf Start");
        
        status.setzeSchriftFarbe(10); // Status zeigt hier in der Farbe Rot an, das nun berechnet wird
        status.setzeInhalt("Berechne...");
        
        prim.pruefePrimzahl(aLaenge.index() * 100 - 100);
        resetAndFillE();
        
        status.setzeSchriftFarbe(5); // Status zeigt hier in der Farbe Grün an, das die Sortierung abgeschlossen ist
        status.setzeInhalt("FERTIG");
        
    }

    // public void bBubbleGeklickt()
    // {
        // status.setzeSchriftFarbe(10); // Status zeigt hier in der Farbe Rot an, das nun berechnet wird
        // status.setzeInhalt("Berechne...");
        // zeit1.setzeInhalt("");
        // if(sorted)
        // {
            // bubble.resetE();
            // bubble.resetS();
            // insertion.resetE();
            // insertion.resetS();
            // selection.resetE();
            // selection.resetS();
            // quick.resetE();
            // quick.resetS();
            // merge.resetE();
            // merge.resetS();
        // }
        // if(test.inhaltAlsText()!="") // Damit der Knopf erst verwendet werden kann, wenn es auch was zum Sortieren gibt
        // {
            // lastUsed = "bubble";
            // bubble.bubbleSort(array, n);
            // zeit1.setzeInhalt("BubbleSort:"+bubble.time(array, n)+"ms");
            // status.setzeSchriftFarbe(5); // Status zeigt hier in der Farbe Grün an, das die Sortierung abgeschlossen ist
            // status.setzeInhalt("FERTIG");
            // maxSeite=1;
            // getMaxSeite();
            // //System.out.println(maxSeite+" Seite(n) (BubbleSort)");
            // first();
            // sorted = true;
        // }
    // }

    
    public void resetAndFillE()
    {
        prim.resetE();
        for(int i=1;i<20;i++)
        {            
                prim.e.geheZuPosition(i);
                prim.schritte.geheZuPosition(19*(seite-1)+i);
                prim.e.aktuelles().setzeInhalt(prim.schritte.aktuelles());            
        }
    }

    public void getMaxSeite()
    {
            int i = 1;
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
        this.getMaxSeite();
        if(seite<maxSeite)
        {
            seite = seite + 1;
            Seite.setzeInhalt("Seite:" + seite);
            resetAndFillE();
        }
    }

    public void last()
    {
        this.getMaxSeite();
        if(seite<maxSeite)
        {
            seite = r.gerundet(maxSeite);
            Seite.setzeInhalt("Seite:" + seite);
            resetAndFillE();
        }
    }
}
