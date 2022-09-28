package br.furb.berkeley.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import br.furb.berkeley.common.Time;

/**
 * 
 * @author Wagner Ribeiro
 *
 */
public interface ServerIF extends Remote {

	Time getTime() throws RemoteException;

	void setTime(long epochSeconds) throws RemoteException;
}