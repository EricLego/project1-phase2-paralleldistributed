package edu.ksu.election;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Election extends Remote {
    void castVote(String response) throws RemoteException;
    String getResult() throws RemoteException;
}
