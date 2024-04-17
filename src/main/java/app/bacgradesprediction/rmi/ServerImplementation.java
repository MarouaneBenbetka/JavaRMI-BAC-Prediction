package app.bacgradesprediction.rmi;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ServerImplementation extends UnicastRemoteObject implements RemoteInterface {

    public ServerImplementation() throws RemoteException {
        super();
    }

    @Override
    public void trainModel(String schoolName, String filePath) throws RemoteException {
        // Logic to handle training with given data
        System.out.println("Training model with data from: " + filePath);
    }

    @Override
    public String predictPerformance(float grade1, float grade2, float grade3) throws RemoteException {
        // Prediction logic based on grades
        return "Prediction based on input grades.";
    }
}