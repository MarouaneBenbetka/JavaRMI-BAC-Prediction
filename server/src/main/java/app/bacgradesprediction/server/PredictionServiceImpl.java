package app.bacgradesprediction.server;


import app.bacgradesprediction.rmi.interfaces.PredictionService;
import app.bacgradesprediction.server.utils.DecisionTreeModel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class PredictionServiceImpl extends UnicastRemoteObject implements PredictionService {
    private DecisionTreeModel model;

    public PredictionServiceImpl(DecisionTreeModel model) throws RemoteException {
        super();
        this.model = model;
    }

    @Override
    public Map<String, ?> predictBAC(float grade1, float grade2, float grade3) throws Exception {
        if (model == null) {
            throw new IllegalStateException("Model has not been initialized.");
        }

        String prediction = model.predict(grade1, grade2, grade3);
        Map<String, Object> result = new HashMap<>();
        result.put("BACMention", prediction);
        return result;
    }
}
