package app.bacgradesprediction.server;


import app.bacgradesprediction.rmi.interfaces.DataUploadService;
import app.bacgradesprediction.rmi.interfaces.PredictionService;
import app.bacgradesprediction.rmi.interfaces.TrainingService;
import app.bacgradesprediction.server.utils.DecisionTreeModel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static DecisionTreeModel model = new DecisionTreeModel();
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(446);
            TrainingService trainingService = new TrainingServiceImpl(model);

            PredictionService predictionService = new PredictionServiceImpl(model);

            DataUploadService dataUploadService = new DataUploadServiceImpl();

            registry.rebind("PredictionService", predictionService);
            registry.rebind("TrainingService", trainingService);
            registry.rebind("DataUploadService", dataUploadService);

            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
