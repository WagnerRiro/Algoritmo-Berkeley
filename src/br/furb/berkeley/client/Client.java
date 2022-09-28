package br.furb.berkeley.client;

import br.furb.berkeley.common.Time;

/**
 * 
 * @author Wagner Ribeiro
 *
 */
public class Client {

    public static void main(String[] args) {
        try {
            final Time timeClient = new Time();
            System.out.println("Client time: " + timeClient);

            ClientImpl clientImpl = new ClientImpl();
            clientImpl.setConnections("192.168.0.10", 2400);
            //clientImpl.setConnections("IP", PORT); 
            
            System.out.println(clientImpl.setSincronizaRelogios(clientImpl.getServers(), timeClient));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
