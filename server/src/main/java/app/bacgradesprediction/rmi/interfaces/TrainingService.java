package app.bacgradesprediction.rmi.interfaces;

import java.rmi.Remote;
import java.util.List;
import java.util.Map;

public interface TrainingService extends Remote {
    Map<String, ?> launchTraining() throws Exception;
}
