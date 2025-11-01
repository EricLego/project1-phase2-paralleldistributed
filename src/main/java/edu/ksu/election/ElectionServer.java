package edu.ksu.election;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Locale;

public class ElectionServer extends UnicastRemoteObject implements Election {
    private final int[] votes = {0, 0};

    protected ElectionServer() throws RemoteException { super(); }

    @Override
    public synchronized void castVote(String candidateName) throws RemoteException {
        if (candidateName == null) throw new RemoteException("Null candidate");
        String c = candidateName.trim().toUpperCase(Locale.ROOT);
        switch (c) {
            case "A": votes[0]++; break;
            case "B": votes[1]++; break;
            default: throw new RemoteException("Unknown candidate: " + candidateName + " (use A or B)");
        }
        System.out.println("Vote cast for " + c + " -> [A=" + votes[0] + ", B=" + votes[1] + "]");
    }

    @Override
    public synchronized int[] getResult() throws RemoteException {
        return new int[]{votes[0], votes[1]};
    }

    public static void main(String[] args) {
        String serviceName = "ElectionService";
        String hostname = (args.length > 0) ? args[0] : "localhost";
        try {
            System.setProperty("java.rmi.server.hostname", hostname);

            // Start local registry
            try { LocateRegistry.createRegistry(1099); } catch (RemoteException ignored) {}

            ElectionServer server = new ElectionServer();
            Naming.rebind("rmi://" + hostname + "/" + serviceName, server);
            System.out.println("Election Server ready at rmi://" + hostname + "/" + serviceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
