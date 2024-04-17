package app.bacgradesprediction.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    void trainModel(String schoolName, String filePath) throws RemoteException;
    String predictPerformance(float grade1, float grade2, float grade3) throws RemoteException;
}