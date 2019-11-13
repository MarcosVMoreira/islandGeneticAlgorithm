/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.agilha;

import java.util.Iterator;

public class Util {

    public int[] converteRacionalArray(float racional) {

        int[] array = {};

        String stringDecimal = Float.toString(racional);

        String[] ar = stringDecimal.split("");


        return array;
    }

    public float converteArrayDecimal(String[] array) {

        String stringDecimal = array.toString();

        return Float.parseFloat(stringDecimal);

    }

}
