package com.mycompany.agilha;


import java.util.Locale;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 14161000231
 */
public class IndividuoArray extends Individuo<int[], Float> {

    private double intervalo[];
    Util util = new Util();
    private AlgoritmoGenetico ag = new AlgoritmoGenetico();
    
    public IndividuoArray(int[] cromossomo) {
        super(cromossomo);
        this.intervalo = intervalo;

    }

    @Override
    public Float getFenotipo() {

        float xChapeu = 0;
        
        double valorDecimal = util.converteArrayParaInteiro(cromossomo);
        
        valorDecimal = valorDecimal/(Math.pow(10,(ag.getTamanhoCromossomo()-1)));
        
        xChapeu = (float) (valorDecimal * Math.sin(10 * valorDecimal * Math.PI) + 1);

        Locale.setDefault(Locale.ENGLISH);

        return xChapeu;
    }

    @Override
    public float getAptidao() {
        return (float) (1  / (Math.pow(getFenotipo(), 2) + 0.001));
    }

    // Retorna o valor do gene em uma determinada posição
    public int getGene(int pos) {
        return cromossomo[pos];
    }

    public int getTamanhoCromossomo() {
        return cromossomo.length;
    }

    public IndividuoArray clonar() {
        
        int cromossomoClonado[] = new int[cromossomo.length];
        for (int i = 0; i < cromossomo.length; ++i) {
            cromossomoClonado[i] = cromossomo[i];
        }
        IndividuoArray clone = new IndividuoArray(cromossomoClonado);
        return clone;
    }

    // Método para modificar o valor de um gene no cromossomo.
    public void setGene(int pos, int valor) {
        cromossomo[pos] = valor;
    }

    @Override
    public void mostrarIndividuo() {
        System.out.print("I =");
        for (int i = 0; i < cromossomo.length; ++i) {
            System.out.print(" " + cromossomo[i]);
        }
        System.out.printf(" Fenótipo = " + getFenotipo());

        System.out.printf(" Aptidão = " + getAptidao());

        System.out.println();
    }
}
