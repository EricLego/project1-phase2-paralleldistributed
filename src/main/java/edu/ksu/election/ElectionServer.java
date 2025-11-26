package edu.ksu.election;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Locale;

public class ElectionServer extends UnicastRemoteObject implements Election {
    private int yes = 0, no = 0, dontCare = 0;

    protected ElectionServer() throws RemoteException { super(); }

    @Override
    public synchronized void castVote(String response) throws RemoteException {
        if (response == null) throw new RemoteException("Null response");
        String r = response.trim().toLowerCase(Locale.ROOT);
        switch (r) {
            case "yes": yes++; break;
            case "no": no++; break;
            case "dontcare": dontCare++; break;
            default: throw new RemoteException("Unknown response: " + response + " (use yes, no, or dontcare)");
        }
        System.out.println("Vote cast for " + r + " -> [yes=" + yes + ", no=" + no + ", dontcare=" + dontCare + "]");
    }

    @Override
    public synchronized String getResult() throws RemoteException {
        return yes + " yes, " + no + " no, " + dontCare + " don't care";
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
