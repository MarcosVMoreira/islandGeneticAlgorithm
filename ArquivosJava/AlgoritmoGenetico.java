
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico implements Serializable {

    private ArrayList<Individuo> populacao;
    private int probabilidadeMutacao;
    private int tamanhoCromossomo = 6;

    public AlgoritmoGenetico(int tamanhoPopulacao, int probabilidadeMutacao) {
        populacao = new ArrayList<Individuo>();
        this.probabilidadeMutacao = probabilidadeMutacao;
        inicializarPopulacao(tamanhoPopulacao);
    }

    public AlgoritmoGenetico() {

    }

    public void evoluir(int numGeracoes, int momentoMigracao, MigracaoServer migracaoServer)
            throws IOException, NotBoundException, MalformedURLException, RemoteException {
        Operacoes op = new Operacoes();
        int count = 0;
        int countM = 0;
        while (numGeracoes > 0) {
            if (count == momentoMigracao) {
                count = 0;
                countM++;
                System.out.println("Migracao " + countM);
                migracaoServer.setPopulacao(populacao);
                migracaoServer.setReady(true);
                Migracao remoteObjectReference = (Migracao) Naming.lookup("rmi://172.16.104.32/Ilha");
                while (true) {
                    if (remoteObjectReference.getReady())
                        break;
                }
                ArrayList<Individuo> melhores = remoteObjectReference.getIndividuosMigracao(10);
                System.out.println("Novos Individuos");
                for (Individuo i : melhores) {
                    i.mostrarIndividuo();
                }

                while (true) {
                    if (migracaoServer.getHas())
                        break;
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                migracaoServer.setHas(false);
                populacao = migracaoServer.getPopulacao();
                populacao.addAll(melhores);
                System.out.println("Nova Populacao");
                mostrarPopulacao();
            }
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
            count++;
        }
    }

    public void mostrarPopulacao() {
        for (Individuo i : populacao) {
            i.mostrarIndividuo();
        }
    }

    private void inicializarPopulacao(int tamanhoPopulacao) {
        Random r = new Random();
        int primeiroAlgarismo, randomSinal;

        for (int i = 0; i < tamanhoPopulacao; ++i) {
            int cromossomo[] = new int[getTamanhoCromossomo()];
            for (int j = 0; j < getTamanhoCromossomo(); ++j) {
                if (j == 0) {
                    primeiroAlgarismo = r.nextInt(2);

                    if (primeiroAlgarismo == 0) {
                        randomSinal = r.nextInt(2);
                        if (randomSinal == 0) {
                            primeiroAlgarismo *= -1;
                        }
                    }
                    cromossomo[j] = primeiroAlgarismo;

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
