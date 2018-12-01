import sum.komponenten.*;
import sum.werkzeuge.*;
import sum.strukturen.*;
/**
 * @author 
 * @version 
 */
public class Primzahlen
{
    // Bezugsobjekte
 
    protected Liste<Integer> schritte;

    // Attribute
    protected int seite = 1;
    protected int n;
    protected double t;
    protected double f;
    protected int step;
    protected int c = 0; 
    // Attribute
    
    

    // Konstruktor
    public Primzahlen()
    {
        schritte = new Liste<Integer>();
    }

    // Dienste
    public void pruefen(int current) 
     {  
         if(this.isPrimzahl(current) == true)
         {
             schritte.haengeAn(current);
            }
         
     }
    
        public boolean isPrimzahl(int current) 
    {  
         if (current <= 2) { 
            return (current == 2); 
        } 
        for (long i = 2; i * i <= current; i++) { 
            if (current % i == 0) { 
                return false; 
            } 
        }
        return true;
        
    }
    
    public void save(int current)
    {
        schritte.haengeAn(current);
    }
    
    public void resetS()
    {
        schritte.entferneAlleElemente();
    }

       public void pruefePrimzahl(int max) 
    {        
       c = c + max;
       this.resetS();
       for(int m = 2; m <= c; m++)
       {
           this.pruefen(m);
       }
    }
     

}
    
