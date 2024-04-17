package app.bacgradesprediction.rmi.interfaces;

import java.rmi.Remote;
import java.util.List;
import java.util.Map;

public interface DataUploadService extends Remote {

    Map<String, ?> uploadData(List<Map<String,?>> records , String schoolName) throws Exception;
}

