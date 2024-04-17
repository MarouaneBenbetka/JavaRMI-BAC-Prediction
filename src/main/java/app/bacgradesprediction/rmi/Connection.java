package app.bacgradesprediction.rmi;


import app.bacgradesprediction.rmi.interfaces.DataUploadService;
import app.bacgradesprediction.rmi.interfaces.PredictionService;
import app.bacgradesprediction.rmi.interfaces.TrainingService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;

import static app.bacgradesprediction.utils.Config.*;

public class Connection {
    private PredictionService predictionStub;
    private TrainingService trainingStub ;
    private DataUploadService dataUploadStub;

    public Connection(String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            predictionStub = (PredictionService) registry.lookup(PREDICTION_SERVICE_NAME);
            trainingStub = (TrainingService) registry.lookup(TRAINING_SERVICE_NAME);
            dataUploadStub = (DataUploadService) registry.lookup(DATA_UPLOAD_SERVICE_NAME);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public  Map<String, ?> upload(List<Map<String, ?>> records , String schoolName) {
        try {
            return dataUploadStub.uploadData(records , schoolName);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        return null;
    }

    public Map<String, ?> predict(float grade1, float grade2, float grade3) {
        try {
            return predictionStub.predictBAC( grade1 ,  grade2 , grade3);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, ?> train() {
        try {
            return trainingStub.launchTraining();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

}