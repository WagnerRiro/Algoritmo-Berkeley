package br.furb.berkeley.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author Wagner Ribeiro
 *
 */
public class Server {

    public static void main(String[] args) {
        final int serverPort = 2400;

        try {
            ServerIF severTime = new ServerImpl();
            Registry registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind("ServerImpl", severTime);

            System.out.println("Servidor Active \n" + registry.toString());


        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
