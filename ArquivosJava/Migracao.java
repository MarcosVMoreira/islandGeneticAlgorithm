

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Migracao extends Remote {
    public ArrayList<Individuo> getIndividuosMigracao(ArrayList<Individuo> populacao, int migrantes) throws RemoteException;
    public boolean getReady() throws RemoteException;
    
    public boolean getHas() throws RemoteException;

    public void setReady(boolean ready) throws RemoteException;
    public void setHas(boolean has) throws RemoteException;
}