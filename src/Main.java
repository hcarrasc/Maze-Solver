

/**
 * @author Hector Carrasco Burgos
 * @version 1.0
 */

/*
 *  Nombre: Hector Carrasco
 *  Profesor: Clemente Rubio
 *  Asigantura: Inteligencia Artificial
 *  Carrera: Ingenieria Civil en Informatica
 *  Universidad del Biobío.
 * 
 */

/* Descripción General de Modelado:
 * 
 * La presente es la Clase Main del Algoritmo de búsqueda
 * en tablero de 10x10 en anchura, modelado como 
 * una matriz de 10x10, donde los caminos
 * válidos para avanzar a través del laberinto son expresados
 * como "0" [cero] y las paredes o zonas no validas para
 * avanzar son expresadas como "1" [uno] y la ruta ya recorrida
 * es mostrada como "5" [cinco]
 */

public class Main {
    
    public static void main( String args[] ){
        
        // La matriz mapa, que sera usada para representar el laberinto
        int [][] mapa = new int[10][10];
        // Coordenadas de inicio del recorrido o entrada del lab.
        int xinicial = 1;
        int yinicial = 0;
        // Coordenadas de fin del recorrido o salida del lab.
        int xfinal   = 9;
        int yfinal   = 7;
        
        // crea un objeto de tipo laberinto Solucion "labSolucion"
        labSolucion s = new labSolucion(xinicial, yinicial, xfinal, yfinal);
        // Inicializacion del laberinto ( creacion de rutas y paredes )
        s.crearMapa(mapa);
        s.verTablero(mapa);
        // Ejecucion de la busqueda de la salida, 
        // siguiendo una metodologia de busqueda en anchura
        s.Buscarsolucion(mapa);
    }   
}
