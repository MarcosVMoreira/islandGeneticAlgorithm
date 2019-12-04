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
import java.util.Scanner;

/**
 *
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) throws IOException, NotBoundException, MalformedURLException, RemoteException {
        System.setProperty("java.rmi.server.hostname", "172.16.104.39");
        MigracaoServer migracaoServer = new MigracaoServer();
        Naming.rebind("rmi://172.16.104.39/Ilha", migracaoServer);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        AlgoritmoGenetico ag = new AlgoritmoGenetico(10, 8);
        ag.mostrarPopulacao();
        ag.evoluir(50,5, migracaoServer);
        
        System.out.println("\n\nPopulação após evolução: ");
        
        ag.mostrarPopulacao();
    }
}
