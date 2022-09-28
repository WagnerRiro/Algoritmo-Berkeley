package br.furb.berkeley.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import br.furb.berkeley.common.Time;

/**
 * 
 * @author Wagner Ribeiro
 *
 */
public class ServerImpl extends UnicastRemoteObject implements ServerIF {

	private static final long serialVersionUID = 1L;
	private Time time;


	public ServerImpl() throws RemoteException {
		time = new Time();
		System.out.println("Server time: " + time);
	}

	@Override
	public Time getTime() throws RemoteException {
		return time;
	}

	@Override
	public void setTime(long epochSeconds) throws RemoteException {
		time.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSeconds), ZoneOffset.UTC));
		System.out.println("Updated time to: " + time);
	}
}