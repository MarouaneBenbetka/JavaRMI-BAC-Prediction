package app.bacgradesprediction;

import app.bacgradesprediction.rmi.Connection;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Map;

import static app.bacgradesprediction.utils.Config.HOSTNAME;
import static app.bacgradesprediction.utils.Config.PORT;

public class PredictPage extends VBox {
    private TextField grade1Input, grade2Input, grade3Input;
    private Button submitButton;
    private Label resultLabel;
    private Map<String, ?> response;

    public PredictPage() {
        initUI();
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void initUI() {
        grade1Input = new TextField();
        grade2Input = new TextField();
        grade3Input = new TextField();

        grade1Input.setPromptText("Enter grade 1 (0-20)");
        grade2Input.setPromptText("Enter grade 2 (0-20)");
        grade3Input.setPromptText("Enter grade 3 (0-20)");
        grade1Input.getStyleClass().add("text-field");
        grade2Input.getStyleClass().add("text-field");
        grade3Input.getStyleClass().add("text-field");

        submitButton = new Button("Submit");
        submitButton.getStyleClass().add("button");
        submitButton.setOnAction(e -> submitGrades());

        resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        resultLabel.setVisible(false);

        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.getChildren().addAll(new Label("Enter Grades:"), grade1Input, grade2Input, grade3Input, submitButton, resultLabel);
    }

    private void submitGrades() {
        try {
            float grade1 = Float.parseFloat(grade1Input.getText());
            float grade2 = Float.parseFloat(grade2Input.getText());
            float grade3 = Float.parseFloat(grade3Input.getText());

            if (grade1 < 0 || grade1 > 20 || grade2 < 0 || grade2 > 20 || grade3 < 0 || grade3 > 20) {
                resultLabel.setText("Grades must be between 0 and 20.");
                resultLabel.setVisible(true);
                return;
            }

            Connection connection = new Connection(HOSTNAME, PORT);
            response = connection.predict(grade1, grade2, grade3);

            String predictedBAC = (String) response.get("BACMention");
            resultLabel.setText(String.format("Predicted BAC mention: %s", predictedBAC));
            resultLabel.setVisible(true);
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numeric grades.");
            resultLabel.setVisible(true);
        } catch (Exception e) {
            resultLabel.setText("Error connecting to server. Please try again.");
            resultLabel.setVisible(true);
            e.printStackTrace();
        }
    }
}
