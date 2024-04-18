package app.bacgradesprediction;

import app.bacgradesprediction.rmi.Connection;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static app.bacgradesprediction.utils.Config.HOSTNAME;
import static app.bacgradesprediction.utils.Config.PORT;

public class TrainingPage  extends VBox {
    private Button trainButton;
    private Label resultLabel;

    public TrainingPage() {
        initUI();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void initUI() {

        trainButton = new Button("Train");
        trainButton.getStyleClass().add("button");
        trainButton.setOnAction(e -> trainModel());

        resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        resultLabel.setVisible(false);

        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.getChildren().addAll(trainButton, resultLabel);
    }

    private void trainModel() {
        Connection connection = new Connection(HOSTNAME,PORT);
        connection.train();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Training Complete");
        alert.setHeaderText(null);
        alert.setContentText("Decision Tree Model Trained Succesfully!");
        alert.showAndWait();
    }
}
