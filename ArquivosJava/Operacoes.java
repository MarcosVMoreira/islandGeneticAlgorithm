package pacote;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// Classe com as operações de Algoritmos Genéticos
public class Operacoes {

    // Efetua o crossover entre dois indivíduos
    public IndividuoArray[] crossover(IndividuoArray i1, IndividuoArray i2) {

        int tam = i1.getTamanhoCromossomo();

        Random r = new Random();
        int pontoCorte = r.nextInt(tam);

        IndividuoArray ni1 = i1.clonar();

        IndividuoArray ni2 = i2.clonar();

        for (int i = pontoCorte; i < tam; ++i) {
            ni1.setGene(i, i2.getGene(i));
            ni2.setGene(i, i1.getGene(i));
        }

        return new IndividuoArray[] { ni1, ni2 };

    }

    // Método para efetuar a mutação sobre um indivíduo
    // o parâmetro probabilidade assume um valor [0,100]
    public void mutacao(IndividuoArray individuo, int probabilidade) {

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

    public ArrayList<Individuo> migrarPrimeiraIlha(ArrayList<Individuo> populacao, int migrantes,
            int serverPortProxIlha, int serverPort) throws IOException {
        DatagramSocket aSocket = new DatagramSocket(serverPort);
        InetAddress serverIP = InetAddress.getByName("localhost");
        ArrayList<Individuo> melhoresIndividuos = new ArrayList<Individuo>();
        while(migrantes>0){
            float maior = populacao.get(0).getAptidao();
            IndividuoArray melhor = (IndividuoArray) populacao.get(0);
            int index = 0;
            for(int i=0;i<populacao.size();i++){
                if(populacao.get(i).getAptidao()>maior){
                    maior = populacao.get(i).getAptidao();
                    melhor = (IndividuoArray) populacao.get(i);
                    index = i;
                }
            }
            melhoresIndividuos.add(melhor);
            populacao.remove(index);
            migrantes--;
        }
        Gson gson = new Gson();
        String json = gson.toJson(melhoresIndividuos);
        byte[] buffer = json.getBytes();
        DatagramPacket send =   new DatagramPacket(buffer, buffer.length, serverIP, serverPortProxIlha);
        aSocket.send(send);
        byte[] receber = new byte[1000];
        DatagramPacket request = new DatagramPacket(receber, receber.length);
        aSocket.receive(request);
        json = new String(request.getData(),0,request.getLength());
        IndividuoArray[] melhores = gson.fromJson(json, IndividuoArray[].class);
        for(int i=0;i<melhores.length;i++){
            populacao.add(melhores[i]);
        }
        aSocket.close();
        return populacao;
    }

    public ArrayList<Individuo> migrarIlha(ArrayList<Individuo> populacao, int migrantes,
            int serverPortProxIlha, int serverPort) throws IOException {
        DatagramSocket aSocket = new DatagramSocket(serverPort);
        InetAddress serverIP = InetAddress.getByName("localhost");
        ArrayList<Individuo> melhoresIndividuos = new ArrayList<Individuo>();
        while(migrantes>0){
            float maior = populacao.get(0).getAptidao();
            IndividuoArray melhor = (IndividuoArray) populacao.get(0);
            int index = 0;
            for(int i=0;i<populacao.size();i++){
                if(populacao.get(i).getAptidao()>maior){
                    maior = populacao.get(i).getAptidao();
                    melhor = (IndividuoArray) populacao.get(i);
                    index = i;
                }
            }
            melhoresIndividuos.add(melhor);
            populacao.remove(index);
            migrantes--;
        }
        Gson gson = new Gson();
        byte[] receber = new byte[1000];
        DatagramPacket request = new DatagramPacket(receber, receber.length);
        aSocket.receive(request);
        String json = new String(request.getData(),0,request.getLength());
        String envio = gson.toJson(melhoresIndividuos);
        byte[] buffer = envio.getBytes();
        DatagramPacket send =   new DatagramPacket(buffer, buffer.length, serverIP, serverPortProxIlha);
        aSocket.send(send);
        IndividuoArray[] melhores = gson.fromJson(json, IndividuoArray[].class);
        for(int i=0;i<melhores.length;i++){
            populacao.add(melhores[i]);
        }
        aSocket.close();
        return populacao;
    }

}
