package edu.ksu.election;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Election extends Remote {
    void castVote(String candidateName) throws RemoteException;
    int[] getResult() throws RemoteException;
}
