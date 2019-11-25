/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) throws IOException {

        AlgoritmoGenetico ag = new AlgoritmoGenetico(40, 7);
        ag.mostrarPopulacao();
        ag.evoluir(50,5);
        
        System.out.println("\n\nPopulação após evolução: ");
        
        ag.mostrarPopulacao();



    }
}
