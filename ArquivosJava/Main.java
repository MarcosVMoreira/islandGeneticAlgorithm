/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

/**
 *
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) throws IOException, NotBoundException, MalformedURLException, RemoteException {

        AlgoritmoGenetico ag = new AlgoritmoGenetico(40, 7);
        ag.mostrarPopulacao();
        ag.evoluir(50,5);
        
        System.out.println("\n\nPopulação após evolução: ");
        
        ag.mostrarPopulacao();



    }
}
