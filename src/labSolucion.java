
import java.util.ArrayList;

/**
 * @author Héctor Carrasco Burgos
 * @version 1.0
 */

/*
 * Clase que contiene los metodos para ejecutar la busqueda en anchura
 * en un mapa o tablero de 10x10 que representa un laberinto
*/
public class labSolucion {
    
    int xinicial ;
    int yinicial ;
    int xfinal   ;
    int yfinal   ;
    int tiempo = 0;
    int [][] mapa2 ;
    
    ArrayList <Punto> puntosVisitados = new ArrayList();
    ArrayList <Punto> puntosValidos   = new ArrayList();
    
    // Constructor de la clase
    public labSolucion(int xinicial, int yinicial, int xfinal, int yfinal){
        
        this.xinicial = xinicial;
        this.yinicial = yinicial;
        this.xfinal = xfinal;
        this.yfinal = yfinal;
    }
    
     /* 
      * Método que crea un tablero virtual, que asigna ceros o unos
        por cada fila del laberinto, donde los 1 significan las 
        paredes y los 0 significan los caminos por donde se puede
        circular en el laberinto.
     */
    public int[][] crearMapa(int[][] mapa){
        //crea la fila 0
        for(int i=0; i<10;i++){
            mapa[0][i] = 1;
        }
        // crea la fila 1
        for(int i=0; i<10;i++){
            if(i!=9){
                mapa[1][i] = 0;
            }else{
                mapa[1][i] = 1; 
            }
        }
        // crea la fila2
        for(int i=0; i<10;i++){
            if(i==2||i==8){
                mapa[2][i] = 0;
            }else{
                mapa[2][i] = 1; 
            }
        }
        // crea la fila3
        for(int i=0; i<10;i++){
            if(i==0||i==1||i==3||i==4||i==9){
                mapa[3][i] = 1;
            }else{
            mapa[3][i] = 0; 
            }
        }
        //fila4
        for(int i=0; i<10;i++){
            if(i==2||i==3||i==4||i==5){
                mapa[4][i] = 0;
            }else{
                mapa[4][i] = 1; 
            }
        }
        // crea la fila5
        for(int i=0; i<10;i++){
            if(i==2||i==5){
                mapa[5][i] = 0;
            }else{
                mapa[5][i] = 1; 
            }
        }
        //fila6
        for(int i=0; i<10;i++){
            if(i==5||i==6||i==7||i==8){
                mapa[6][i] = 0;
            }else{
                mapa[6][i] = 1; 
            }
        }
        // crea la fila7
        for(int i=0; i<10;i++){
            if(i==8){
                mapa[7][i] = 0;
            }else{
                mapa[7][i] = 1; 
            }
        }
        // crea la fila8
        for(int i=0; i<10;i++){
            if(i==0||i==1||i==9){
                mapa[8][i] = 1;
            }else{
                mapa[8][i] = 0; 
            }
        }
        // crea la fila9
        for(int i=0; i<10;i++){
            if(i==7){
                mapa[9][7] = 0;
            }else{
                mapa[9][i] = 1; 
            }
        }
        return mapa;
    }
    
    /* Metodo que mostrará el recorrido parcial en el laberinto
       marcando la ruta recorrida por medio de cincos (5)
     */
    public void mostrarRecorrido (int x, int y){
        mapa2[x][y] = 5;
        verTablero(mapa2);
    }
    
    // imprime un laberinto pasado por parametro 
    public void verTablero(int[][] mapa){
        
        // imprimir el tablero del laberinto en la salida standart
        int j=0;
        for(int i=0; i<=9;i++){
            for(j=0; j<10; j++){
                System.out.print(mapa[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    //Busqueda de la solucion
    public void Buscarsolucion(int[][] mapa ){
        
        Punto pa = new Punto(xinicial, yinicial);
        Punto pf = new Punto(xfinal, yfinal);

        Punto[] paux;
        mapa2 = mapa;
        puntosValidos.add(pa);
        System.out.println("\nruta valida: "+puntosValidos.get(0).x+","+puntosValidos.get(0).y);
        
        while(true){
            for(int k=0; k < puntosValidos.size(); k++){
                
                // se extrae el primer punto valido y se asigna a punto actual "pa"
                pa = puntosValidos.get(0);
                // se agrega el punto actual a la lista de lugares visitados
                puntosVisitados.add(pa);
                // se imprime el recorrido parcial
                mostrarRecorrido(pa.x, pa.y);
                System.out.println("\n-------------------------------\n");

                // si el punto actual es distinto a la salida, se calculan sus sucesores
                if(pa.x != pf.x || pa.y != pf.y){
                    paux = calcularMovimientos(pa.x, pa.y);
                    // se valida que los sucesores sean caminos validos y no repetidos
                    validarMovimientos(paux, mapa);
                }
                
                // ingresa aqui cuando se ha llegado a la salida del laberinto
                if(pa.x == pf.x && pa.y == pf.y){
                    System.out.println("¡Se ha encontrado la salida exitosamente!\n\n");
                    System.exit(0);
                }
                // se extrae de la pila el punto ya analizado recientemente
                puntosValidos.remove(0);
            }
        }
        
    }
    
    /* 
     * calcula los movimientos validos segun la posicion del punto actual
     * guardando los movimientos en un array de Puntos, los cuales 
     * posteriormente seran analizados para comprobar si son validos
     */
    public Punto[] calcularMovimientos(int x, int y){
        Punto aux = new Punto(x,y);
        Punto puntos[] = new Punto[5];
        Movimientos mov = new Movimientos();
        
        if(x==0){
            if(y==0){
                puntos[0] = mov.irDerecha(x, y);
                puntos[1] = mov.irAbajo(x, y);
                puntos[2] = null;
                return puntos;
            }
            if(y==9){
                puntos[0] = mov.irDerecha(x, y);
                puntos[1] = mov.irArriba(x, y);
                puntos[2] = null;
                return puntos;
            }
            if((y<10) && (y>0)){
                puntos[0] = mov.irDerecha(x, y);
                puntos[1] = mov.irArriba(x, y);                
                puntos[2] = mov.irAbajo(x, y);
                puntos[3] = null;
                return puntos;
            }
        }else{
            if(y==0){
                if(x==9){
                    puntos[0] = mov.irIzquierda(x, y);
                    puntos[1] = mov.irAbajo(x, y);
                    puntos[2] = null;
                    return puntos;
                }
                if(x<10 && x>0){
                    puntos[0] = mov.irIzquierda(x, y);
                    puntos[1] = mov.irDerecha(x, y);                
                    puntos[2] = mov.irAbajo(x, y);
                    puntos[3] = null;
                    return puntos;
                }
            
            }else{
                if(x==9){
                    if(y==9){
                        puntos[0] = mov.irIzquierda(x, y);
                        puntos[1] = mov.irArriba(x, y);
                        puntos[2] = null;
                        return puntos;
                    }
                    if(y<9 &&y>0){
                        puntos[0] = mov.irIzquierda(x, y);
                        puntos[1] = mov.irArriba(x, y);                
                        puntos[2] = mov.irAbajo(x, y);
                        puntos[3] = null;
                        return puntos;
                    }
                } else{
                    if(y==9 && (x<9) && (x>0)){
                        puntos[0] = mov.irIzquierda(x, y);
                        puntos[1] = mov.irArriba(x, y);                
                        puntos[2] = mov.irDerecha(x, y);
                        puntos[3] = null;
                        return puntos;
                    }
                }
            }
        }
        puntos[0] = mov.irIzquierda(x, y);
        puntos[1] = mov.irArriba(x, y);                
        puntos[2] = mov.irDerecha(x, y);
        puntos[3] = mov.irAbajo(x, y);
        puntos[4] = null;
        return puntos;

    }
    
    /*
     * Se valida que los movimientos calculados en el metodo "calcularMovimientos"
     * correspondan a caminos permitidos y que no sean caminos ya recorridos anteriormente
     */
    public void validarMovimientos(Punto[] paux, int[][] mapa){
        
        int i=0;
        while (paux[i]!=null){
           
            // si el camino es valido continua
            if( mapa[paux[i].x][paux[i].y]==0){
                // si no es un camino repetido continua
                if (!comprobarExistencia(paux[i].x, paux[i].y)){
                    // se guarda como un camino valido
                    puntosValidos.add(paux[i]);
                    // se muestra en salida standard el punto valido encontrado
                    System.out.println("ruta valida: "+puntosValidos.get(1).x+","+puntosValidos.get(1).y);
                }
            }
            i++;
        }
    }
    
    /*
     * Encargado de comprobar si el punto x,y pasado por
     * parámetro es un punto que ya habia sido visitado anteriormente
     */
    public boolean comprobarExistencia(int x, int y){
        
        int indice = 0;
        while(puntosVisitados.size()>indice){
            if(puntosVisitados.get(indice).x==x){
                if(puntosVisitados.get(indice).y==y){
                    return true;
                }
            }
            indice++;
        }
        return false;
    }
}

