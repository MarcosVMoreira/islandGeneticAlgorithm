package com.mycompany.agilha;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico {

    private ArrayList<Individuo> populacao;
    private int probabilidadeMutacao;
    private int tamanhoCromossomo = 6;

    public AlgoritmoGenetico(int tamanhoPopulacao,
            int probabilidadeMutacao
    ) {
        populacao = new ArrayList<Individuo>();
        this.probabilidadeMutacao = probabilidadeMutacao;
        inicializarPopulacao(tamanhoPopulacao);
    }
    
    
    public AlgoritmoGenetico () {
        
    }

    public void evoluir(int numGeracoes) {
        Operacoes op = new Operacoes();
        while (numGeracoes > 0) {
            //System.out.println("Geração (" + numGeracoes + ")");
            ArrayList<Individuo> novaPopulacao = new ArrayList<Individuo>();
            while (novaPopulacao.size() < populacao.size()) {
                IndividuoArray i1 = (IndividuoArray) op.roleta(populacao);
                IndividuoArray i2 = (IndividuoArray) op.roleta(populacao);
                IndividuoArray filhos[] = op.crossover(i1, i2);
                op.mutacao(filhos[0], probabilidadeMutacao);
                op.mutacao(filhos[1], probabilidadeMutacao);
                novaPopulacao.add(filhos[0]);
                novaPopulacao.add(filhos[1]);
            }
            populacao = novaPopulacao;
            numGeracoes--;
        }
    }

    public void mostrarPopulacao() {
        for (Individuo i : populacao) {
            i.mostrarIndividuo();
        }
    }

    private void inicializarPopulacao(int tamanhoPopulacao) {
        Random r = new Random();
        int primeiroAlgorismo, randomSinal;

        for (int i = 0; i < tamanhoPopulacao; ++i) {
            int cromossomo[] = new int[getTamanhoCromossomo()];
            for (int j = 0; j < getTamanhoCromossomo(); ++j) {
                if (j == 0) {
                    primeiroAlgorismo = r.nextInt(2);

                    if (primeiroAlgorismo == 0) {
                        randomSinal = r.nextInt(2);
                        if (randomSinal == 0) {
                            primeiroAlgorismo = primeiroAlgorismo * -1;
                        }
                    }
                    cromossomo[j] = primeiroAlgorismo;

                } else {
                    cromossomo[j] = r.nextInt(10);
                }
            }
            IndividuoArray individuo = new IndividuoArray(cromossomo);
            populacao.add(individuo);
        }
        
    }

    /**
     * @return the tamanhoCromossomo
     */
    public int getTamanhoCromossomo() {
        return tamanhoCromossomo;
    }

}
