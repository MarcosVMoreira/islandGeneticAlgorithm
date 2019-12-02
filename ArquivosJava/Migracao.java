

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Migracao extends Remote {
    public ArrayList<Individuo> getIndividuosMigracao(int migrantes) throws RemoteException;
    public boolean getReady() throws RemoteException;
    
    public boolean getHas() throws RemoteException;

    public void setReady(boolean ready) throws RemoteException;
    public void setHas(boolean has) throws RemoteException;

    public void setPopulacao(ArrayList<Individuo> populacao) throws RemoteException;
    public ArrayList<Individuo> getPopulacao() throws RemoteException;
}