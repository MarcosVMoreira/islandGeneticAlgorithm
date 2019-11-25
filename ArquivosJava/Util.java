/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote;

import java.util.Iterator;

public class Util {

    public int[] converteInteiroParaArray(int racional) {

        int[] array;

        String stringDecimal = Integer.toString(racional);

        String[] ar = stringDecimal.split("");
        
        array = new int[ar.length];
        
        for (int i = 0; i < ar.length; i++) {
            array[i] = Integer.parseInt(ar[i]);
        }

        return array;
    }

    public int converteArrayParaInteiro(int[] array) {

        String stringDoArray = "";

        for (int f : array) {
            stringDoArray = stringDoArray.concat(Integer.toString(f));
        }

        return Integer.parseInt(stringDoArray);
    }
    
    

}
