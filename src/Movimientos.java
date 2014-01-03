/**
 * @author Hector Carrasco Burgos
 */

// Clase que contiene la ejecucion de los movimientos
public class Movimientos {
    
    // Calcula los movimientos posibles hacia adelante
    public Punto irDerecha (int xactual, int yactual){
        
        Punto p = new Punto (xactual, yactual);
        p.x = p.x+1;
        return p;
    }
    // Calcula los movimientos posibles hacia atrás
    public Punto irIzquierda    (int xactual, int yactual){
        Punto p = new Punto (xactual, yactual);
        p.x = p.x-1;      
        return p;
    }
    // Calcula los movimientos posibles hacia abajo
    public Punto irAbajo    (int xactual, int yactual){
        Punto p = new Punto (xactual, yactual);
        p.y = p.y+1;       
        return p;
    }
    // Calcula los movimientos posibles hacia arriba
    public Punto irArriba   (int xactual, int yactual){
        Punto p = new Punto (xactual, yactual);
        p.y = p.y-1; 
        return p;
    }
            
}
