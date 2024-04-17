package app.bacgradesprediction.rmi;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface TrainingService extends Remote {

    Map<String, ?> launchTraining(List<Map<String, ?>> records) throws RemoteException;

}

