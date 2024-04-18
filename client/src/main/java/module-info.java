module app.bacgradesprediction {
    requires java.rmi;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.csv;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.commons.io;


    opens app.bacgradesprediction to javafx.fxml;
    exports app.bacgradesprediction;
}