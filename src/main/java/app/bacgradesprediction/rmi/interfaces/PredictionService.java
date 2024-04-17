package app.bacgradesprediction.rmi.interfaces;

import java.rmi.Remote;
import java.util.Map;

public interface PredictionService extends Remote {
    Map<String, ?> predictBAC(float grade1, float grade2, float grade3) throws Exception;
}

