package app.bacgradesprediction.server;


import app.bacgradesprediction.db.DatabaseUtil;
import app.bacgradesprediction.rmi.interfaces.DataUploadService;
import app.bacgradesprediction.server.utils.DecisionTreeModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUploadServiceImpl extends UnicastRemoteObject implements DataUploadService {

    public DataUploadServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Map<String, ?> uploadData(List<Map<String, ?>> records, String schoolName) throws Exception {

        DatabaseUtil db = new DatabaseUtil();
        db.insertStudentGrades(records,schoolName);

        Map<String, Object> result = new HashMap<>();
        result.put("TrainingStatus", "Success");
        return result;
    }
}
