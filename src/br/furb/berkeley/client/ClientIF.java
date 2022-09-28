package br.furb.berkeley.client;

import java.util.Map;

import br.furb.berkeley.common.Connection;
import br.furb.berkeley.common.Time;
import br.furb.berkeley.server.ServerIF;

/**
 * 
 * @author Wagner Ribeiro
 *
 */
public interface ClientIF {

    Map<Connection, ServerIF> getServers();

    void setConnections(String ip, int port);

    String setSincronizaRelogios(Map<Connection, ServerIF> servers, Time timeClient);

}
