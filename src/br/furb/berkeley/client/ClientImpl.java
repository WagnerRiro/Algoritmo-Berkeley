package br.furb.berkeley.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.furb.berkeley.common.Connection;
import br.furb.berkeley.common.Time;
import br.furb.berkeley.server.ServerIF;

/**
 * 
 * @author Wagner Ribeiro
 *
 */
public class ClientImpl implements ClientIF {
    List<Connection> connections;

    public ClientImpl() {
        this.connections = new ArrayList<>();
    }

    @Override
    public Map<Connection, ServerIF> getServers() {
        try {
            final Map<Connection, ServerIF> servers = new HashMap<>();

            for (Connection c : connections) {
                Registry registry = LocateRegistry.getRegistry(c.getAddress(), c.getPort());
                ServerIF serverTime = (ServerIF) registry.lookup("ServerImpl");
                System.out.println(String.format("Server time %s: %s", c.getAddress(), serverTime.getTime().getTime()));
                servers.put(c, serverTime);
            }

            return servers;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setConnections(String ip, int port) {
        this.connections.add(new Connection(ip, port));
    }

    @Override
    public String setSincronizaRelogios(Map<Connection, ServerIF> servers, Time timeClient) {
        try {
            long coordinatorSeconds = timeClient.getEpochSeconds();
            long diffServer = 0;
            long sum = 0;
            long timeServer = 0;
            String result = "";

            for (Map.Entry<Connection, ServerIF> entry : servers.entrySet()) {
                timeServer = (entry.getValue().getTime().getEpochSeconds());
                diffServer = timeServer - coordinatorSeconds;
                sum += diffServer;
            }
            long average = sum / (servers.size() + 1);

            long resultSeconds = (average + (-1 * diffServer) + timeServer);

            LocalDateTime resultDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(resultSeconds), ZoneOffset.UTC);
            result += "\nResult Berkeley: " + resultDateTime + "\n\n";

            for (Map.Entry<Connection, ServerIF> entry : servers.entrySet()) {
                entry.getValue().setTime(resultSeconds);
                result += String.format("Server time update %s: %s ", entry.getKey().getAddress(), entry.getValue().getTime().getTime()) + "\n";
            }
            timeClient.setTime(resultDateTime);
            result += "Client time update: " + timeClient + "\n";

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return "Não foi possível calcular a diferença de horarios, poís ocorreram erros.";
        }
    }

}
