package app.bacgradesprediction.server;


import app.bacgradesprediction.db.DatabaseUtil;
import app.bacgradesprediction.rmi.interfaces.TrainingService;
import app.bacgradesprediction.server.utils.DecisionTreeModel;
import weka.core.DenseInstance;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.bacgradesprediction.db.DatabaseUtil.insertStudentGrades;
import static app.bacgradesprediction.server.utils.Constants.*;

public class TrainingServiceImpl extends UnicastRemoteObject implements TrainingService {
    private DecisionTreeModel model;

    public TrainingServiceImpl(DecisionTreeModel model) throws RemoteException {
        super();
        this.model = model;  // Initialize the model
    }

    @Override
    public Map<String, ?> launchTraining() throws Exception {

//        saving in MySQL
        DatabaseUtil db = new DatabaseUtil();
        List<Map<String, ?>> records = db.retrieveStudentGrades();


        model.train(records);
        Map<String, Object> result = new HashMap<>();
        result.put("TrainingStatus", "Success");
        return result;
    }
}
