package com.mycompany.agilha;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico {

    private ArrayList<Individuo> populacao;
    private int probabilidadeMutacao;

    public AlgoritmoGenetico(int tamanhoPopulacao,
            int probabilidadeMutacao
    ) {
        populacao = new ArrayList<Individuo>();
        this.probabilidadeMutacao = probabilidadeMutacao;
        inicializarPopulacao(tamanhoPopulacao);
    }

    public void evoluir(int numGeracoes) {
        Operacoes op = new Operacoes();
        while (numGeracoes > 0) {
            System.out.println("Geração (" + numGeracoes + ")");
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

        int tamanhoCromossomo = 4;

        for (int i = 0; i < tamanhoPopulacao; ++i) {
            int cromossomo[] = new int[tamanhoCromossomo];
            for (int j = 0; j < tamanhoCromossomo; ++j) {
                if (j == 0) {
                    cromossomo[j] = r.nextInt(2) - 1;
                } else if (j == 2) {
                    cromossomo[j] = -9;
                } else {
                    cromossomo[j] = r.nextInt(9);
                }
            }
            IndividuoArray individuo = new IndividuoArray(cromossomo);
            populacao.add(individuo);
        }
    }

}
