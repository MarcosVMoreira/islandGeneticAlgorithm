

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MigracaoServer extends UnicastRemoteObject implements Migracao {

    private boolean ready;
    private boolean hasGetted;
    private ArrayList<Individuo> populacao;
    private int migracao = 0;
    
    public ArrayList<Individuo> getIndividuosMigracao(int migrantes) throws RemoteException{
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
        this.hasGetted = true;
        return melhoresIndividuos;
        
    }

    public void setPopulacao(ArrayList<Individuo> populacao) throws RemoteException{
        this.populacao = populacao;
    }

    public ArrayList<Individuo> getPopulacao() throws RemoteException{
        return this.populacao;
    }
    public MigracaoServer() throws RemoteException{
        this.ready=false;
        this.hasGetted=false;
        migracao=0;
    }

    public boolean getReady() throws RemoteException{
        return this.ready;
    }
    
    public boolean getHas() throws RemoteException{
        return this.hasGetted;
    }

    public void setReady(boolean ready) throws RemoteException{
        this.ready=ready;
    }

    public int getMigracao() throws RemoteException{
        return this.migracao;
    }

    public void setMigracao(int migracao) throws RemoteException{
        this.migracao=migracao;
    }
    public void setHas(boolean has) throws RemoteException{
        this.hasGetted=has;
    }
}