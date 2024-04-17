package app.bacgradesprediction.rmi;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private RemoteInterface stub;

    public Client(String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            stub = (RemoteInterface) registry.lookup("RemoteInterface");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void train(String schoolName, String filePath) {
        try {
            stub.trainModel(schoolName, filePath);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public String predict(float grade1, float grade2, float grade3) {
        try {
            return stub.predictPerformance(grade1, grade2, grade3);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }
}