package com.mycompany.agilha;

import java.util.ArrayList;
import java.util.Random;

// Classe com as operações de Algoritmos Genéticos
public class Operacoes {

    // Efetua o crossover entre dois indivíduos
    public IndividuoArray[] crossover(
            IndividuoArray i1,
            IndividuoArray i2) {

        int tam = i1.getTamanhoCromossomo();

        Random r = new Random();
        int pontoCorte = r.nextInt(tam);

        IndividuoArray ni1 = i1.clonar();
        i2.clonar();
        
        IndividuoArray ni2 = i2.clonar();

        for (int i = pontoCorte; i < tam; ++i) {
            ni1.setGene(i, i2.getGene(i));
            ni2.setGene(i, i1.getGene(i));
        }

        return new IndividuoArray[]{ni1, ni2};

    }

    // Método para efetuar a mutação sobre um indivíduo
    // o parâmetro probabilidade assume um valor [0,100]
    public void mutacao(IndividuoArray individuo,
            int probabilidade) {

        Random r = new Random();

        int valorAleatorio = r.nextInt(101);
        if (probabilidade >= valorAleatorio) {
            int tam = individuo.getTamanhoCromossomo();
            int pos = r.nextInt(tam);
            
            while (pos == 0) {
                pos = r.nextInt(tam);
            }
            
            int caracter = r.nextInt(10);
            
            individuo.setGene(pos, caracter);

        }

    }

    // Método da Roleta para determinar os indivíduos mais aptos
    public Individuo roleta(ArrayList<Individuo> populacao) {
        double aptidaoTotal = 0.0;
        // Soma todas as aptidões...
        for (Individuo i : populacao) {
            aptidaoTotal += i.getAptidao();
        }

        Random r = new Random();

        double valorAleatorio = r.nextDouble() * aptidaoTotal;

        double aptidaoAcumulada = 0.0;

        for (Individuo i : populacao) {
            aptidaoAcumulada += i.getAptidao();
            if (valorAleatorio <= aptidaoAcumulada) {
                return i;
            }
        }

        return populacao.get(0);

    }

}
